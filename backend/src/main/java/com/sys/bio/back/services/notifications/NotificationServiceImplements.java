package com.sys.bio.back.services.notifications;

import com.sys.bio.back.models.notifications.Notification;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.repositories.notifications.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class NotificationServiceImplements implements NotificationService{
    
    @Autowired
    private NotificationRepository notificationRepo;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

    @Override
    public Set<Notification> getNotifications() {
        return new LinkedHashSet<>(notificationRepo.findAll());
    }

    @Override
    public Notification getNotification(Long notificationId) {
        return notificationRepo.findById(notificationId).get();
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = new Notification();
        notification.setNotificationId(notificationId);
        notificationRepo.delete(notification);
    }

    @Override
    public int getUnreadNotification() {
        return notificationRepo.countByIsReadFalse();
    }


}
