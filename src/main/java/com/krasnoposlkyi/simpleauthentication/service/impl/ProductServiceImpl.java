package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Products;
import com.krasnoposlkyi.simpleauthentication.dao.repository.ProductRepository;
import com.krasnoposlkyi.simpleauthentication.dao.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final ParseJsonServiceImpl parseJsonService;
    private final CreatingTableService creatingTableService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ParseJsonServiceImpl parseJsonService,
                              CreatingTableService creatingTableService) {
        this.productRepository = productRepository;
        this.parseJsonService = parseJsonService;
        this.creatingTableService = creatingTableService;
    }

    public List<Products> getAll() {
        return productRepository.findAll();
    }

    public ProductResponse createAndInsert(String json)  {
        //creating table
        boolean isCreated = creatingTableService.create(json);
        //get Table name
        String tableName = parseJsonService.getTableName(json)
                .orElse("Field 'table' does not exist in JSON");
        //parsing JSON
        List<Products> products = parseJsonService.parseProductJson(json);

        //saving to database
        int rowInserted = save(products).size();
        return new ProductResponse(tableName, isCreated, rowInserted);
    }

    public Products save(Products products) {
        return productRepository.save(products);
    }

    public List<Products> save(List<Products> products) {
        return productRepository.saveAll(products);
    }
}
