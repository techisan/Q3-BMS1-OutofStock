package com.example.ecom.models;

import lombok.Data;

import jakarta.persistence.*;


@Entity
@Data
@Table(name = "users")
public class User extends BaseModel {
    private String name;
    private String email;
}
