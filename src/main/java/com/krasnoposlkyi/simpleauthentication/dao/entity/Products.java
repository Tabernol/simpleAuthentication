package com.krasnoposlkyi.simpleauthentication.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date entryDate;
    private String itemCode;
    private String itemName;
    private Integer itemQuantity;
    private String status; //should be Enum
}
