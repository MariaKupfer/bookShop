package com.adesso.notification_service.services;

import com.adesso.notification_service.domain.Notification;

public interface NotificationService {
    
    void sendNatificationToUser(Notification notification);

}
