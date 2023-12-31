package com.krasnoposlkyi.simpleauthentication.controller;

import com.krasnoposlkyi.simpleauthentication.dao.response.JwtAuthenticationResponse;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;
import com.krasnoposlkyi.simpleauthentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/add")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
