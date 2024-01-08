package com.krasnoposlkyi.simpleauthentication.integration;

import com.krasnoposlkyi.simpleauthentication.anotation.IT;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Role;
import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
public class IntegrationTestBase {
    private static final MySQLContainer<?> container =
            new MySQLContainer<>("mysql:8.0");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        System.out.println("JDBC URL ====== " + container.getJdbcUrl());
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
