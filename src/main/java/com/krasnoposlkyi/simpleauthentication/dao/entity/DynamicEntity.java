package com.krasnoposlkyi.simpleauthentication.dao.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class DynamicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private Map<String, String> records = new HashMap<>();
}
