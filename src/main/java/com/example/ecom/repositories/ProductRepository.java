package com.example.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

