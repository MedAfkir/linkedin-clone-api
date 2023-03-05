package com.linkedinclone.api.controllers.authentication;

import com.linkedinclone.api.config.JwtUtils;
import com.linkedinclone.api.dto.auth.LoginResponse;
import com.linkedinclone.api.dto.users.UserLoginRequest;
import com.linkedinclone.api.dto.users.UserRegistrationRequest;
import com.linkedinclone.api.exceptions.alreadyused.EmailAlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.UsernameAlreadyUsedException;
import com.linkedinclone.api.exceptions.notfound.AdminNotFoundException;
import com.linkedinclone.api.models.admins.Admin;
import com.linkedinclone.api.services.admins.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth/admin")
@RequiredArgsConstructor
public class AuthAdminController {

    private final AdminService adminService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegistrationRequest request
    ) throws EmailAlreadyUsedException, UsernameAlreadyUsedException {
        return ResponseEntity.ok(adminService.addAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginRequest request
    ) throws AdminNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Admin admin = adminService.findAdminByUsername(request.getUsername());
        final String accessToken = jwtUtils.generateAccessToken(admin);
        final String refreshToken = jwtUtils.generateRefreshToken(admin);

        LoginResponse response = LoginResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(response);
    }
}
