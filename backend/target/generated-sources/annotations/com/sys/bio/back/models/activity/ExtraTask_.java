package com.sys.bio.back.models.activity;

import com.sys.bio.back.models.user.Responsible;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExtraTask.class)
public abstract class ExtraTask_ {

	public static volatile SingularAttribute<ExtraTask, Long> extraTaskId;
	public static volatile SingularAttribute<ExtraTask, Date> date;
	public static volatile SingularAttribute<ExtraTask, LocalDate> filterDate;
	public static volatile SingularAttribute<ExtraTask, LocalTime> endTaskTime;
	public static volatile SingularAttribute<ExtraTask, Double> totalTaskHours;
	public static volatile SingularAttribute<ExtraTask, Activity> activity;
	public static volatile SingularAttribute<ExtraTask, LocalTime> startTaskTime;
	public static volatile SingularAttribute<ExtraTask, Responsible> responsible;

	public static final String EXTRA_TASK_ID = "extraTaskId";
	public static final String DATE = "date";
	public static final String FILTER_DATE = "filterDate";
	public static final String END_TASK_TIME = "endTaskTime";
	public static final String TOTAL_TASK_HOURS = "totalTaskHours";
	public static final String ACTIVITY = "activity";
	public static final String START_TASK_TIME = "startTaskTime";
	public static final String RESPONSIBLE = "responsible";

}

