package edu.ifri.smartclass.service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ifri.smartclass.enums.Role;
import edu.ifri.smartclass.exception.BadRequestException;
import edu.ifri.smartclass.exception.NotFoundException;
import edu.ifri.smartclass.exception.ResourceNotFoundException;
import edu.ifri.smartclass.model.PasswordResetToken;
import edu.ifri.smartclass.model.User;
import edu.ifri.smartclass.payload.request.ForgetPasswordRequest;
import edu.ifri.smartclass.payload.request.UpdatePasswordRequest;
import edu.ifri.smartclass.payload.request.UserRegisterRequest;
import edu.ifri.smartclass.payload.request.UserUpdate;
import edu.ifri.smartclass.repository.PasswordResetTokenRepository;
import edu.ifri.smartclass.repository.UserRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public List<User> getUser() {
        return userRepository.findAllByIsDeletedIsFalse();
    }

    public User getUserById(UUID id){
        return userRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    public User createUser(UserRegisterRequest userRegisterRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByUserNameOrEmail(userRegisterRequest.getUserName(), userRegisterRequest.getEmail()))) {
            throw new BadRequestException("Email or Username already taken !");
        }

        User userSaved = new User();
        userSaved.setFirstName(userRegisterRequest.getFirstName());
        userSaved.setLastName(userRegisterRequest.getLastName());
        userSaved.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        userSaved.setUserName(userRegisterRequest.getUserName());
        userSaved.setEmail(userRegisterRequest.getEmail());
        userSaved.setRole(Role.PROFESSOR);
        return userRepository.save(userSaved);
    }

    public User updateUser(UUID id, UserUpdate userUpdate) {
        Optional<User> user = userRepository.findByIdAndIsDeletedIsFalse(id);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found !");
        }
        User updateUser = user.get();
        updateUser.setFirstName(userUpdate.firstName());
        updateUser.setLastName(userUpdate.lastName());
        return userRepository.save(updateUser);
    }

    public User deleteUser(UUID id) {
        Optional<User> user = userRepository.findByIdAndIsDeletedIsFalse(id);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found !");
        }
        User updateUser = user.get();
        updateUser.setDeleted(true);
        return userRepository.save(updateUser);
    }

    public User updatePassword(UUID id, UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(() -> new NotFoundException("User not found !"));
        if (Objects.equals(updatePasswordRequest.newPassword(), updatePasswordRequest.confirmPassword())) {
            if (passwordEncoder.matches(updatePasswordRequest.currentPassword(), user.getPassword())) {
                String newPassword = passwordEncoder.encode(updatePasswordRequest.newPassword());
                user.setPassword(newPassword);
                return userRepository.save(user);
            } else {
                throw new BadRequestException("Ancien mot de passe incorrect !");
            }
        }
        throw new BadRequestException("NewPassword et ConfirmPassword ne correspondent pas !");
    }

    public String createTokenForForgotPassword(String email) {
        User user = userRepository.findByEmailAndIsDeletedIsFalse(email).orElseThrow(() -> new NotFoundException("User not found !"));
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetToken.setExpiryDate();
        passwordResetTokenRepository.save(passwordResetToken);
        return token;
    }

    public User validateUpdatePassword(ForgetPasswordRequest forgetPasswordRequest) {

        Optional<PasswordResetToken> passwordResetTokenOptional =
                passwordResetTokenRepository.findByToken(forgetPasswordRequest.token());

        if (passwordResetTokenOptional.isPresent()) {

            Calendar cal = Calendar.getInstance();
            if ((passwordResetTokenOptional.get().getExpiryDate()
                    .getTime() - cal.getTime()
                    .getTime()) <= 0) {
                return null;
            } else {
                User user = passwordResetTokenOptional.get().getUser();
                user.setPassword(passwordEncoder.encode(forgetPasswordRequest.confirmNewPassword()));
                return userRepository.save(user);
            }

        } else {
            return null;
        }
    }

    public boolean hasRole(@NotNull User user, Role role) {
        return user.getRole().equals(role);
    }
}
