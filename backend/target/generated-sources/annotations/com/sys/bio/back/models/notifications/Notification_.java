package com.sys.bio.back.models.notifications;

import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notification.class)
public abstract class Notification_ {

	public static volatile SingularAttribute<Notification, LocalTime> hour;
	public static volatile SingularAttribute<Notification, Boolean> isRead;
	public static volatile SingularAttribute<Notification, String> icon;
	public static volatile SingularAttribute<Notification, Long> notificationId;
	public static volatile SingularAttribute<Notification, String> message;
	public static volatile SingularAttribute<Notification, String> url;

	public static final String HOUR = "hour";
	public static final String IS_READ = "isRead";
	public static final String ICON = "icon";
	public static final String NOTIFICATION_ID = "notificationId";
	public static final String MESSAGE = "message";
	public static final String URL = "url";

}

