package com.krasnoposlkyi.simpleauthentication.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity()
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entryDate; // should be date

    private String itemCode;

    private String itemName;

    private String itemQuantity; // should be Integer

    private String status; // should be Enum
}
