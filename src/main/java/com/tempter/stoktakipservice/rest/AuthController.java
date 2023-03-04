package com.tempter.stoktakipservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.tempter.stoktakipservice.configuration.jwt.JwtUtils;
import com.tempter.stoktakipservice.entity.User;
import com.tempter.stoktakipservice.rest.dto.LoginRequest;
import com.tempter.stoktakipservice.rest.dto.MessageResponse;
import com.tempter.stoktakipservice.rest.dto.RegisterRequest;
import com.tempter.stoktakipservice.rest.dto.TokenResponse;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.tempter.stoktakipservice.repository.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok().body(new TokenResponse(loginRequest.getEmail(),jwt));
    }


    @PostMapping("/register")
    ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest) {
        
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse("Registered Successfully"));
    }

}
