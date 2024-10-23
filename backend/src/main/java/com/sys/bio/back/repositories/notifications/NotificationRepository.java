package com.sys.bio.back.repositories.notifications;

import com.sys.bio.back.models.notifications.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    int countByIsReadFalse();

}
