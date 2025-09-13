package com.project.notification_type_1.services.notifications;

import com.project.notification_type_1.dtos.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final StreamBridge streamBridge;

    public void send(NotificationRequest request) {
        // push to notification.exchange queue
        Message<NotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader("routingKey", request.getChannel()) // routing key
                .setHeader("priority", request.getPriority() != null ? request.getPriority() : 2)
                .build();
        streamBridge.send("notificationProducer-out-0", message);
    }
}
