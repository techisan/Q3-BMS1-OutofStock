package com.example.ecom.services;

import com.example.ecom.adapters.EmailAdapter;
import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.models.Inventory;
import com.example.ecom.models.Notification;
import com.example.ecom.models.NotificationStatus;
import com.example.ecom.models.Product;
import com.example.ecom.repositories.InventoryRepository;
import com.example.ecom.repositories.NotificationRepository;
import com.example.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final NotificationRepository notificationRepository;

    private final EmailAdapter emailAdapter;

    @Autowired
    public InventoryServiceImpl(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            NotificationRepository notificationRepository,
            EmailAdapter emailAdapter
    ) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.notificationRepository = notificationRepository;
        this.emailAdapter = emailAdapter;
    }

    @Override
    public Inventory updateInventory(int productId, int quantity) throws ProductNotFoundException {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Optional<Inventory> inventoryOptional = this.inventoryRepository.findByProduct(product);
        Inventory inventory;

        if (inventoryOptional.isEmpty()) {
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(quantity);
        } else {
            inventory = inventoryOptional.get();
            inventory.setQuantity(inventory.getQuantity() + quantity);
        }

        Inventory savedInventory = this.inventoryRepository.save(inventory);

        // Notify users if product is back in stock
        if (quantity > 0) {
            List<Notification> notifications = notificationRepository.findByProduct(product);
            for (Notification notification : notifications) {
                emailAdapter.sendEmail(
                        notification.getUser().getEmail(),
                        product.getName() + " is back in stock!",
                        "Hi " + notification.getUser().getName() + ", " + product.getName() + " is now available."
                );
                notification.setStatus(NotificationStatus.SENT);
                notificationRepository.save(notification);
            }
        }

        return savedInventory;
    }
}
