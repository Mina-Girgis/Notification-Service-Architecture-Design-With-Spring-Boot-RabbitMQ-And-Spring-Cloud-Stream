package com.project.notification_type_1.services.notifications.consumers;

import com.project.notification_type_1.dtos.NotificationRequest;
import com.project.notification_type_1.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EmailConsumer {
    private final EmailService emailService;

    // SMS consumer
    @Bean
    public Consumer<NotificationRequest> smsConsumer() {
        // call sms service here
        return this.emailService::sendEmail;
    }
}
