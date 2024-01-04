package com.krasnoposlkyi.simpleauthentication.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity()
//@Table(name = "products")
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "entry_date")
    private String entryDate; // should be date

//    @Column(name = "item_code")
    private String itemCode;

//    @Column(name = "item_name")
    private String itemName;

//    @Column(name = "item_quantity")
    private String itemQuantity; // should be Integer

    private String status; // should be Enum
}
