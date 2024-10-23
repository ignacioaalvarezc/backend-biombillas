package com.sys.bio.back.models.notifications;

import com.sys.bio.back.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String message;
    @Column(name = "is_read")
    private boolean isRead;
    private String icon;
    private String url;
    private LocalTime hour;


}


