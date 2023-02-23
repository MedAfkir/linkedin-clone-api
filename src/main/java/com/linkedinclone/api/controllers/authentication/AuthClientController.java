package com.linkedinclone.api.controllers.authentication;

import com.linkedinclone.api.config.JwtUtils;
import com.linkedinclone.api.dto.auth.LoginResponse;
import com.linkedinclone.api.dto.users.*;
import com.linkedinclone.api.exceptions.alreadyused.EmailAlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.UsernameAlreadyUsedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.services.clients.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthClientController {

    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request)
            throws EmailAlreadyUsedException, UsernameAlreadyUsedException {
        return ResponseEntity.ok(clientService.addClient(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) throws ClientNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        final Client client = clientService.findClientByUsername(request.username());
        final String token = jwtUtils.generateAccessToken(client);
        final String refreshToken = jwtUtils.generateRefreshToken(client);

        final LoginResponse response = LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(response);
    }
}
