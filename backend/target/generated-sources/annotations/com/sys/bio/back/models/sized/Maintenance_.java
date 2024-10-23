package com.sys.bio.back.models.sized;

import java.time.LocalTime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Maintenance.class)
public abstract class Maintenance_ {

	public static volatile SingularAttribute<Maintenance, Long> maintenanceId;
	public static volatile SingularAttribute<Maintenance, String> reason;
	public static volatile SingularAttribute<Maintenance, String> state;
	public static volatile SingularAttribute<Maintenance, Date> dateAlert;
	public static volatile SingularAttribute<Maintenance, String> operator;
	public static volatile SingularAttribute<Maintenance, LocalTime> hourAlert;

	public static final String MAINTENANCE_ID = "maintenanceId";
	public static final String REASON = "reason";
	public static final String STATE = "state";
	public static final String DATE_ALERT = "dateAlert";
	public static final String OPERATOR = "operator";
	public static final String HOUR_ALERT = "hourAlert";

}

