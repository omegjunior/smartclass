package edu.ifri.smartclass.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.ifri.smartclass.dto.AuthenticateDTO;
import edu.ifri.smartclass.exception.AppException;
import edu.ifri.smartclass.jwt.JwtTokenProvider;
import edu.ifri.smartclass.model.User;
import edu.ifri.smartclass.payload.request.LoginRequest;
import edu.ifri.smartclass.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public AuthenticateDTO authenticateUser(LoginRequest loginRequest) {
        try {
            String username = loginRequest.usernameOrEmail();
            User user = userRepository.findByUserNameOrEmailAndIsDeletedIsFalse(username, username)
                    .orElseThrow(() -> new AppException("User Not found", HttpStatus.NOT_FOUND.toString()));

            username = user.getUserName();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            loginRequest.password()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = tokenProvider.generateToken(authentication);

            return new AuthenticateDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), jwtToken);

        } catch (AuthenticationException e) {
            throw new AppException("Authentification impossible", HttpStatus.UNAUTHORIZED.toString());
        }
    }
}