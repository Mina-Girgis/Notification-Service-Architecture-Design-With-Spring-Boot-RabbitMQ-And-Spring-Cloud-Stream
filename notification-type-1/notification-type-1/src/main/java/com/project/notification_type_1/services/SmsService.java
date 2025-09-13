package com.project.notification_type_1.services;

import com.project.notification_type_1.dtos.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSms(NotificationRequest request) {
        System.out.println("📧 Processing SMS: " + request);
    }
}
