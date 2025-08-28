package com.example.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.Notification;
import com.example.ecom.models.Product;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByProduct(Product product);
}
