package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.krasnoposlkyi.simpleauthentication.dao.entity.Product;
import com.krasnoposlkyi.simpleauthentication.dao.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
