package com.linkedinclone.api.controllers.authentication;

import com.linkedinclone.api.config.JwtUtils;
import com.linkedinclone.api.dto.auth.LoginResponse;
import com.linkedinclone.api.dto.auth.TokenDTO;
import com.linkedinclone.api.dto.users.*;
import com.linkedinclone.api.exceptions.alreadyused.EmailAlreadyUsedException;
import com.linkedinclone.api.exceptions.alreadyused.UsernameAlreadyUsedException;
import com.linkedinclone.api.exceptions.notfound.ClientNotFoundException;
import com.linkedinclone.api.models.clients.Client;
import com.linkedinclone.api.services.clients.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthClientController {

    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegistrationRequest request
    ) throws EmailAlreadyUsedException, UsernameAlreadyUsedException {
        return ResponseEntity.ok(clientService.addClient(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginRequest request
    ) throws ClientNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        final Client client = clientService.findClientByUsername(request.getUsername());
        final String token = jwtUtils.generateAccessToken(client);
        final String refreshToken = jwtUtils.generateRefreshToken(client);

        final LoginResponse response = LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/access-token")
    public ResponseEntity<?> getAccessToken(
            @Valid @RequestBody TokenDTO tokenDTO
    ) throws ClientNotFoundException {
        String username = jwtUtils.extractUsername(tokenDTO.getToken());
        Client client = clientService.findClientByUsername(username);

        if (!jwtUtils.validateToken(tokenDTO.getToken(), client))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        TokenDTO response = TokenDTO.builder()
                .token(jwtUtils.generateAccessToken(client))
                .build();

        return ResponseEntity.ok(response);
    }
}
