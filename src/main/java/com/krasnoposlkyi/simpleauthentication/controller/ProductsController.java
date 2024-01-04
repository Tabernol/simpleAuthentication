package com.krasnoposlkyi.simpleauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Products;
import com.krasnoposlkyi.simpleauthentication.dao.response.ProductResponse;
import com.krasnoposlkyi.simpleauthentication.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductServiceImpl productService;

    public ProductsController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

//    @PostMapping(value = "/add", consumes = "application/json")
//    public ResponseEntity<Integer> testPost(@RequestBody Map<String, Object> payload) {
//        return ResponseEntity.ok().body(creatingService.createTable(payload));
//    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<ProductResponse> testPost(@RequestBody String payload) {
        return ResponseEntity.ok().body(productService.createAndInsert(payload));
    }
}
