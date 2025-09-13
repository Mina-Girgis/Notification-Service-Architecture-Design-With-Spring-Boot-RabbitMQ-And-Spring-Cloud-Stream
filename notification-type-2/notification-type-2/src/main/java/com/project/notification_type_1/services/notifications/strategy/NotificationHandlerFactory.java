package com.project.notification_type_1.services.notifications.strategy;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationHandlerFactory {
    private final Map<String, NotificationHandler> handlers;

    public NotificationHandler getHandler(String type) {
        NotificationHandler handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for type: " + type);
        }
        return handler;
    }
}