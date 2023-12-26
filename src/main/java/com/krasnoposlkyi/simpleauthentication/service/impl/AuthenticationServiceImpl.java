package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.krasnoposlkyi.simpleauthentication.dao.JwtAuthenticationResponse;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Role;
import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import com.krasnoposlkyi.simpleauthentication.dao.repository.UserRepository;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;
import com.krasnoposlkyi.simpleauthentication.service.AuthenticationService;
import com.krasnoposlkyi.simpleauthentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        log.info("Auth service signUp");
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        log.info("Auth service signIn");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
