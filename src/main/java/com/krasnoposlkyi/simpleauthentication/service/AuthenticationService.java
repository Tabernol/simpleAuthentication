package com.krasnoposlkyi.simpleauthentication.service;

import com.krasnoposlkyi.simpleauthentication.dao.JwtAuthenticationResponse;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
