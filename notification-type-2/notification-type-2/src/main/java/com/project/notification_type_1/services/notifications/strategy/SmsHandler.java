package com.project.notification_type_1.services.notifications.strategy;

import com.project.notification_type_1.dtos.NotificationRequest;
import org.springframework.stereotype.Service;

@Service("sms")
class SmsHandler implements NotificationHandler {
    @Override
    public void handle(NotificationRequest request) {
        System.out.println("📱 Sending SMS to " + request.getRecipient());
    }
}