package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Notification extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private NotificationStatus status;
}
