package com.krasnoposlkyi.simpleauthentication.dao.repository;

import com.krasnoposlkyi.simpleauthentication.dao.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
