package com.sys.bio.back.models.sized;

import com.sys.bio.back.models.user.Responsible;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sizing.class)
public abstract class Sizing_ {

	public static volatile SingularAttribute<Sizing, Date> date;
	public static volatile SingularAttribute<Sizing, LocalDate> filterDate;
	public static volatile SingularAttribute<Sizing, Integer> totalHours;
	public static volatile SingularAttribute<Sizing, Long> sizingId;
	public static volatile SingularAttribute<Sizing, Integer> totalAmount;
	public static volatile SingularAttribute<Sizing, LocalTime> hour;
	public static volatile SingularAttribute<Sizing, Responsible> responsible;
	public static volatile SingularAttribute<Sizing, String> observations;
	public static volatile SingularAttribute<Sizing, Integer> totalWeight;
	public static volatile SetAttribute<Sizing, SizedBox> sizedBoxes;
	public static volatile SingularAttribute<Sizing, LocalTime> startTime;
	public static volatile SingularAttribute<Sizing, String> state;
	public static volatile SingularAttribute<Sizing, LocalTime> endTime;

	public static final String DATE = "date";
	public static final String FILTER_DATE = "filterDate";
	public static final String TOTAL_HOURS = "totalHours";
	public static final String SIZING_ID = "sizingId";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String HOUR = "hour";
	public static final String RESPONSIBLE = "responsible";
	public static final String OBSERVATIONS = "observations";
	public static final String TOTAL_WEIGHT = "totalWeight";
	public static final String SIZED_BOXES = "sizedBoxes";
	public static final String START_TIME = "startTime";
	public static final String STATE = "state";
	public static final String END_TIME = "endTime";

}

