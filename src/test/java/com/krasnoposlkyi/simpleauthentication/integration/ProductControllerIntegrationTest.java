package com.krasnoposlkyi.simpleauthentication.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductControllerIntegrationTest extends IntegrationTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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

//    @Test
//    void testProductsAdd() {
//        // Arrange
//
//        ResponseEntity<String> responseEntity = this.restTemplate
//                .postForEntity("http://localhost:" + port + "/products/add",TEST_JSON, String.class);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    }

}
