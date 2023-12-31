package com.krasnoposlkyi.simpleauthentication.anotation;

import com.krasnoposlkyi.simpleauthentication.SimpleAuthenticationApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(classes = SimpleAuthenticationApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public @interface IT {
}
