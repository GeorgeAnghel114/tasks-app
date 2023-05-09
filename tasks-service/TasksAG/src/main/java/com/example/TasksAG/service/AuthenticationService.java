package com.example.TasksAG.service;

import com.example.TasksAG.domain.Client;
import com.example.TasksAG.domain.dto.LoginResponse;
import com.example.TasksAG.security.AuthenticationRequest;
import com.example.TasksAG.security.JWTTokenHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JWTTokenHelper jwtTokenHelper;

    public AuthenticationService(AuthenticationManager authenticationManager, JWTTokenHelper jwtTokenHelper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    public LoginResponse authenticate(AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Client client = (Client) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(client);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setUserId(client.getId());
        response.setUsername(client.getUsername());
        return response;
    }
}
