package com.adesso.notification_service.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.adesso.notification_service.domain.Notification;
import com.adesso.notification_service.repositories.NotificationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Override
    public void sendNatificationToUser(Notification notification) {
/*         emailService.sendEmail(notification.getEmail(), "Bestätigung deiner Bestellung", 
                notification.getOrderId().toString()); */
        notificationRepository.save(notification);
        System.out.println("Email was send to " + notification.getEmail());
    }

    @KafkaListener(topics = "order.created", groupId = "notification-service-group")
    public void handleOrderCreated(String message) {
        Notification notification = new Notification();
        notification.setOrderId(Long.valueOf(message));
        notification.setEmail("bsp@bsp.de");
        sendNatificationToUser(notification);
    }
}
