package com.project.notification_type_1.controllers;

import com.project.notification_type_1.dtos.NotificationRequest;
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
        String binding = "notification." + request.getPriority();
        streamBridge.send(binding, MessageBuilder.withPayload(request).setHeader("type", request.getChannel()).build());
        return ResponseEntity.ok("Message sent to " + request.getPriority() + " queue");
    }
}
