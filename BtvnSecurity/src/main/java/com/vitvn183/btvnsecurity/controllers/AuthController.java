package com.vitvn183.btvnsecurity.controllers;

import com.vitvn183.btvnsecurity.dao.User;
import com.vitvn183.btvnsecurity.models.AuthenticationRequest;
import com.vitvn183.btvnsecurity.models.AuthenticationResponse;
import com.vitvn183.btvnsecurity.services.impl.UserDetailsServiceImpl;
import com.vitvn183.btvnsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping ("/auth/login")
    private ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            )));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(new AuthenticationResponse(token, userDetails.getUsername()));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
