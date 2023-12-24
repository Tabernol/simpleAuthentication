package com.krasnoposlkyi.simpleauthentication.repository;

import com.krasnoposlkyi.simpleauthentication.entity.User;
import liquibase.change.core.LoadDataChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
