package com.sys.bio.back.controllers.notification;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.notifications.Notification;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.services.notifications.NotificationService;
import com.sys.bio.back.services.notifications.NotificationServiceImplements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.addNotification(notification));
    }

    @PutMapping("/update/{notificationId}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("notificationId") Long notificationId,
                                                 @Valid @RequestBody Notification notification) {
        try {
            notification.setNotificationId(notificationId);
            Notification updatedNotification = notificationService.updateNotification(notification);
            return ResponseEntity.ok(updatedNotification);
        } catch (Exception e) {
            log.error("Error al actualizar la notificacion con ID: " + notificationId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listNotifications() {
        return ResponseEntity.ok(notificationService.getNotifications());
    }

    @GetMapping("/id/{notificationId}")
    public Notification listNotification(@PathVariable("notificationId") Long notificationId) {
        return notificationService.getNotification(notificationId);
    }


    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable("notificationId") Long notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @GetMapping("/count")
    public int getUnreadNotification() {
        return notificationService.getUnreadNotification();
    }



}
