package com.project.notification_type_1.dtos;
import lombok.Data;

@Data
public class NotificationRequest {
    private String recipient;
    private String subject;
    private String message;
    private String channel; // email | sms | whatsapp
    private Integer priority; // high | normal | low
}
