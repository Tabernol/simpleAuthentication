package com.krasnoposlkyi.simpleauthentication.integration;

import com.krasnoposlkyi.simpleauthentication.dao.entity.Role;
import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import com.krasnoposlkyi.simpleauthentication.dao.repository.UserRepository;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;
import com.krasnoposlkyi.simpleauthentication.dao.response.JwtAuthenticationResponse;
import com.krasnoposlkyi.simpleauthentication.service.JwtService;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


class AuthenticationControllerIntegrationTest extends IntegrationTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void testSignUpUser() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest("testUser", "testPassword");

        // Act
        ResponseEntity<JwtAuthenticationResponse> jwtResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/user/add", signUpRequest, JwtAuthenticationResponse.class);

        //Assert
        assertTrue(userRepository.findByUsername("testUser").isPresent());
        assertEquals(HttpStatus.CREATED, jwtResponse.getStatusCode());
    }

    @Test
    @Transactional
    void testAuthenticateUser() {
        //Arrange
        SignUpRequest signUpRequest = new SignUpRequest("user", "root");

        restTemplate.postForEntity("http://localhost:" + port + "/user/add",
                signUpRequest, JwtAuthenticationResponse.class);

        assertTrue(userRepository.findByUsername("user").isPresent());
        SignInRequest signInRequest = new SignInRequest("user", "root");
        System.out.println("Size " + userRepository.findAll().size());
        //Act
        ResponseEntity<JwtAuthenticationResponse> jwtResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/user/authenticate",
                        signInRequest, JwtAuthenticationResponse.class);

        //Assert
        assertEquals(HttpStatus.OK, jwtResponse.getStatusCode());
        assertNotNull(jwtResponse.getBody());
        assertNotNull(jwtResponse.getBody().getToken());
        assertTrue(jwtService.isTokenValid(jwtResponse.getBody().getToken(),
                userService.userDetailsService().loadUserByUsername("user")));

    }
}
