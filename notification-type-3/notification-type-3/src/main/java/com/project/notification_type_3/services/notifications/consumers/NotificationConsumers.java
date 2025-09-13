package com.project.notification_type_3.services.notifications.consumers;

import com.project.notification_type_3.dtos.NotificationRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class NotificationConsumers {

    // --- Email Consumers ---
    @Bean
    public Consumer<NotificationRequest> emailHighConsumer() {
        return request -> handleEmail(request, "HIGH");
    }

    @Bean
    public Consumer<NotificationRequest> emailNormalConsumer() {
        return request -> handleEmail(request, "NORMAL");
    }

    @Bean
    public Consumer<NotificationRequest> emailLowConsumer() {
        return request -> handleEmail(request, "LOW");
    }

    // --- SMS Consumers ---
    @Bean
    public Consumer<NotificationRequest> smsHighConsumer() {
        return request -> handleSms(request, "HIGH");
    }

    @Bean
    public Consumer<NotificationRequest> smsNormalConsumer() {
        return request -> handleSms(request, "NORMAL");
    }

    @Bean
    public Consumer<NotificationRequest> smsLowConsumer() {
        return request -> handleSms(request, "LOW");
    }

    // --- WhatsApp Consumers ---
    @Bean
    public Consumer<NotificationRequest> whatsappHighConsumer() {
        return request -> handleWhatsapp(request, "HIGH");
    }

    @Bean
    public Consumer<NotificationRequest> whatsappNormalConsumer() {
        return request -> handleWhatsapp(request, "NORMAL");
    }

    @Bean
    public Consumer<NotificationRequest> whatsappLowConsumer() {
        return request -> handleWhatsapp(request, "LOW");
    }

    // --- Handlers ---
    private void handleEmail(NotificationRequest request, String priority) {
        System.out.println("Sending EMAIL [" + priority + "] to " + request.getRecipient() + ": " + request.getMessage());
        // Add actual email sending logic here
    }

    private void handleSms(NotificationRequest request, String priority) {
        System.out.println("Sending SMS [" + priority + "] to " + request.getRecipient() + ": " + request.getMessage());
        // Add actual SMS sending logic here
    }

    private void handleWhatsapp(NotificationRequest request, String priority) {
        System.out.println("Sending WHATSAPP [" + priority + "] to " + request.getRecipient() + ": " + request.getMessage());
        // Add actual WhatsApp sending logic here
    }
}
