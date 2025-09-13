package com.project.notification_type_1.services.notifications.consumers;

import com.project.notification_type_1.dtos.NotificationRequest;
import com.project.notification_type_1.services.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class SmsConsumer {
    private final SmsService smsService;  // it can be multiple email providers
    // SMS consumer
    @Bean
    public Consumer<NotificationRequest> smsConsumer() {
        // call sms service here
        return this.smsService::sendSms;
    }
}
