package com.krasnoposlkyi.simpleauthentication.dao.repository;

import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
