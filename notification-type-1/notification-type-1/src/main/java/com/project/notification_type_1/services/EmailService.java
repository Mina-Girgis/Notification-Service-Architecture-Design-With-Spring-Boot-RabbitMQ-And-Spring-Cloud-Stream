package com.project.notification_type_1.services;

import com.project.notification_type_1.dtos.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(NotificationRequest request) {
        System.out.println("📧 Processing Email: " + request);
    }
}
