package com.project.notification_type_1.services.notifications.consumers;

import com.project.notification_type_1.dtos.NotificationRequest;
import com.project.notification_type_1.services.notifications.strategy.NotificationHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class NotificationConsumers {

    private final NotificationHandlerFactory notificationHandlerFactory;

    @Bean
    public Consumer<NotificationRequest> highConsumer() {
        return request -> notificationHandlerFactory.getHandler(request.getChannel()).handle(request);
    }

    @Bean
    public Consumer<NotificationRequest> normalConsumer() {
        return request -> notificationHandlerFactory.getHandler(request.getChannel()).handle(request);
    }

    @Bean
    public Consumer<NotificationRequest> lowConsumer() {
        return request -> notificationHandlerFactory.getHandler(request.getChannel()).handle(request);
    }
}