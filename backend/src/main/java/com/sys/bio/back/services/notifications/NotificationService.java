package com.sys.bio.back.services.notifications;

import com.sys.bio.back.models.notifications.Notification;
import com.sys.bio.back.models.packaging.BoxName;

import java.util.Set;

public interface NotificationService {

    Notification addNotification(Notification notification);
    Notification updateNotification(Notification notification);
    Set<Notification> getNotifications();
    Notification getNotification(Long notificationId);

    void deleteNotification(Long notificationId);

    int getUnreadNotification();
}
