package com.krasnoposlkyi.simpleauthentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Product;
import com.krasnoposlkyi.simpleauthentication.dao.request.RequestPayload;
import com.krasnoposlkyi.simpleauthentication.service.impl.CreatingService;
import com.krasnoposlkyi.simpleauthentication.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductServiceImpl productService;
    private final CreatingService creatingService;

    public ProductsController(ProductServiceImpl productService,
                              CreatingService creatingService) {
        this.productService = productService;
        this.creatingService = creatingService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Integer> testPost(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok().body(creatingService.createTable(payload));
    }
}
