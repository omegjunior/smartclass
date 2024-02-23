package edu.ifri.smartclass.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifri.smartclass.converter.ClassroomConverter;
import edu.ifri.smartclass.converter.UserConverter;
import edu.ifri.smartclass.dto.ClassroomDTO;
import edu.ifri.smartclass.dto.UserDTO;
import edu.ifri.smartclass.enums.Role;
import edu.ifri.smartclass.enums.StatusMessage;
import edu.ifri.smartclass.exception.BadRequestException;
import edu.ifri.smartclass.model.User;
import edu.ifri.smartclass.payload.ApiResponse;
import edu.ifri.smartclass.payload.request.ForgetPasswordRequest;
import edu.ifri.smartclass.payload.request.UpdatePasswordRequest;
import edu.ifri.smartclass.payload.request.UserRegisterRequest;
import edu.ifri.smartclass.payload.request.UserUpdate;
import edu.ifri.smartclass.service.ClassroomService;
import edu.ifri.smartclass.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomConverter classroomConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping()
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserDTO user = userConverter.convert(userService.createUser(userRegisterRequest));
        return ResponseEntity.ok(new ApiResponse(true, "User create successfully", HttpStatus.CREATED, user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getUser() {
        return ResponseEntity.ok(new ApiResponse(true, "User List", HttpStatus.OK, userConverter.convert(userService.getUser())));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(new ApiResponse(true, "User", HttpStatus.OK, userConverter.convert(userService.getUserById(id))));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<ApiResponse> getuserWithClassrooms(@PathVariable UUID id) {
        UserDTO userWithClassrooms = userConverter.convert(userService.getUserById(id));

        if (userWithClassrooms.getRole() != Role.PROFESSOR) {
            throw new BadRequestException("User is not a professor");
        }

        List<ClassroomDTO> managedClassrooms = classroomConverter.convert(classroomService.getClassroomsByUser(userService.getUserById(id)));

        userWithClassrooms.setClassrooms(managedClassrooms);

        return ResponseEntity.ok(new ApiResponse(true, "Professor with classrooms", HttpStatus.OK, userWithClassrooms));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserUpdate userUpdate, @PathVariable UUID id) {
        UserDTO user = userConverter.convert(userService.updateUser(id, userUpdate));
        return ResponseEntity.ok(new ApiResponse(true, "User update successfully", HttpStatus.OK, user));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        UserDTO user = userConverter.convert(userService.deleteUser(id));
        return ResponseEntity.ok(new ApiResponse(true, "User delete successfully", HttpStatus.OK, user));
    }

    @PatchMapping(value = "update-password/{id}")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable UUID id, @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        UserDTO user = userConverter.convert(userService.updatePassword(id, updatePasswordRequest));
        return ResponseEntity.ok(new ApiResponse(true, "Password update successfully", HttpStatus.OK, user));
    }

    @PutMapping(value = "reset-password")
    public ResponseEntity<ApiResponse> updatePassForget(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        if (forgetPasswordRequest.newPassword().equals(forgetPasswordRequest.confirmNewPassword())) {
            User user = userService.validateUpdatePassword(forgetPasswordRequest);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponse(true, "Password updated successfully", HttpStatus.OK, user));
            } else {
                throw new BadRequestException("The token has expired or is incorrect.", StatusMessage.BAD_REQUEST_ERROR.getCode());
            }
        } else {
            throw new BadRequestException("The two passwords are not identical.", StatusMessage.BAD_REQUEST_ERROR.getCode());
        }
    }
}
