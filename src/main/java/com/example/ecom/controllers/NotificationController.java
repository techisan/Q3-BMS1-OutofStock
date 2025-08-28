package com.example.ecom.controllers;

import com.example.ecom.dtos.DeregisterUserForNotificationRequestDto;
import com.example.ecom.dtos.DeregisterUserForNotificationResponseDto;
import com.example.ecom.dtos.RegisterUserForNotificationRequestDto;
import com.example.ecom.dtos.RegisterUserForNotificationResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.models.Notification;
import com.example.ecom.services.NotificationService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    public RegisterUserForNotificationResponseDto registerUser(RegisterUserForNotificationRequestDto requestDto) {
        RegisterUserForNotificationResponseDto responseDto = new RegisterUserForNotificationResponseDto();
        try {
            Notification notification = notificationService.registerUser(requestDto.getUserId(), requestDto.getProductId());
            responseDto.setNotification(notification);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setNotification(null);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public DeregisterUserForNotificationResponseDto deregisterUser(DeregisterUserForNotificationRequestDto requestDto) {
        DeregisterUserForNotificationResponseDto responseDto = new DeregisterUserForNotificationResponseDto();
        try {
            notificationService.deregisterUser(requestDto.getUserId(), requestDto.getNotificationId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}

