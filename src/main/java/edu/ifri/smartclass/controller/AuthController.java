package edu.ifri.smartclass.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifri.smartclass.dto.AuthenticateDTO;
import edu.ifri.smartclass.payload.ApiResponse;
import edu.ifri.smartclass.payload.request.LoginRequest;
import edu.ifri.smartclass.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> signin(@Valid @RequestBody LoginRequest loginRequest){

        AuthenticateDTO authenticateDTO = authService.authenticateUser(loginRequest);

        return ResponseEntity.ok(new ApiResponse(true, "User successfully authenticated", HttpStatus.OK, authenticateDTO));

    }
}
