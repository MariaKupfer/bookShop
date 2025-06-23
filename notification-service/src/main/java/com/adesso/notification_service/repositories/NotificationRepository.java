package com.adesso.notification_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.notification_service.domain.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    
}
