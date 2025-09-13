package com.project.notification_type_1.controllers;

import com.project.notification_type_1.dtos.NotificationRequest;
import com.project.notification_type_1.services.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @PostMapping("/notify")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        service.send(request);
        return ResponseEntity.ok("Notification sent to channel: " + request.getChannel());
    }
}
