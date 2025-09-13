package com.project.notification_type_1.services.notifications.consumers;

import com.project.notification_type_1.dtos.NotificationRequest;
import com.project.notification_type_1.services.WhatsappService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
@Configuration
@RequiredArgsConstructor
public class WhatsappConsumer {
    private final WhatsappService whatsappService;

    // WhatsApp consumer
    @Bean
    public Consumer<NotificationRequest> whatsappConsumer() {
        return whatsappService::sendWhatsapp;
    }
}
