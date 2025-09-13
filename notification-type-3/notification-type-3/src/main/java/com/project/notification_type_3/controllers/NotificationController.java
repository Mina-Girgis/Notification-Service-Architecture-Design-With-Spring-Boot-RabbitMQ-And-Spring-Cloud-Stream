package com.project.notification_type_3.controllers;

import com.project.notification_type_3.dtos.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private StreamBridge streamBridge;

    @PostMapping("/notify")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        String binding = request.getChannel().toLowerCase() + capitalize(request.getPriority()) + "Consumer-in-0";
        streamBridge.send(binding, MessageBuilder.withPayload(request).build());
        return ResponseEntity.ok("Notification sent to " + binding);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
