package com.example.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
