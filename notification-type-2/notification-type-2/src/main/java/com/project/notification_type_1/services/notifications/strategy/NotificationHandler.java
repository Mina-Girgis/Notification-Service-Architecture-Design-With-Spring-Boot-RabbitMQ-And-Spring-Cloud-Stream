package com.project.notification_type_1.services.notifications.strategy;

import com.project.notification_type_1.dtos.NotificationRequest;

public interface NotificationHandler {
    void handle(NotificationRequest request);
}