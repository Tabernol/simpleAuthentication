package com.krasnoposlkyi.simpleauthentication.integration;

import com.krasnoposlkyi.simpleauthentication.dao.entity.Role;
import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import com.krasnoposlkyi.simpleauthentication.dao.repository.UserRepository;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignInRequest;
import com.krasnoposlkyi.simpleauthentication.dao.request.SignUpRequest;
import com.krasnoposlkyi.simpleauthentication.dao.response.JwtAuthenticationResponse;
import com.krasnoposlkyi.simpleauthentication.dao.response.ProductResponse;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerIntegrationTest extends IntegrationTestBase {
    @Autowired
    private UserService userService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    private final String TEST_JSON = "{\n" +
            "\"table\" : \"products\",\n" +
            "\"records\" : [\n" +
            "{\n" +
            "\"entryDate\": \"03-01-2023\",\n" +
            "\"itemCode\": \"11111\",\n" +
            "\"itemName\": \"Test Inventory 1\",\n" +
            "\"itemQuantity\": \"20\",\n" +
            "\"status\": \"Paid\"\n" +
            "},\n" +
            "{\n" +
            "\"entryDate\": \"03-01-2023\",\n" +
            "\"itemCode\": \"11111\",\n" +
            "\"itemName\": \"Test Inventory 2\",\n" +
            "\"itemQuantity\": \"20\",\n" +
            "\"status\": \"Paid\"\n" +
            "}] }";


    @Test
    void testProductsAdd() {
        //Arrange
        SignUpRequest signUpRequest = new SignUpRequest("newUser", "root");
        restTemplate.postForEntity("http://localhost:" + port + "/user/add",
                signUpRequest, JwtAuthenticationResponse.class);

        assertTrue(userRepository.findByUsername("newUser").isPresent());
        SignInRequest signInRequest = new SignInRequest("newUser", "root");

        ResponseEntity<JwtAuthenticationResponse> jwtResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/user/authenticate",
                        signInRequest, JwtAuthenticationResponse.class);
        String token = jwtResponse.getBody().getToken();

        //add token to header and create request
        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth("Bearer "+token);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(TEST_JSON, headers);
        //Act


        ResponseEntity<ProductResponse> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/products/add", entity, ProductResponse.class);

        assertTrue(userRepository.findByUsername("newUser").isPresent());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().getRowInserted());
        assertEquals("products", responseEntity.getBody().getTableName());
    }

}
