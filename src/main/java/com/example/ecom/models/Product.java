package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;
}
