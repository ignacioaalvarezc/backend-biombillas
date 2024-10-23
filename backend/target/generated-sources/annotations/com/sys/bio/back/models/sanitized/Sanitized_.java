package com.sys.bio.back.models.sanitized;

import com.sys.bio.back.models.user.Responsible;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sanitized.class)
public abstract class Sanitized_ {

	public static volatile SingularAttribute<Sanitized, Date> date;
	public static volatile SingularAttribute<Sanitized, Integer> totalHours;
	public static volatile SingularAttribute<Sanitized, Responsible> responsible;
	public static volatile SingularAttribute<Sanitized, String> observations;
	public static volatile SingularAttribute<Sanitized, LocalTime> startTime;
	public static volatile SingularAttribute<Sanitized, String> state;
	public static volatile SingularAttribute<Sanitized, LocalTime> endTime;
	public static volatile SingularAttribute<Sanitized, String> stateBoxes;
	public static volatile SingularAttribute<Sanitized, Long> sanitizedId;
	public static volatile SetAttribute<Sanitized, SanitizedBox> sanitizedBoxes;

	public static final String DATE = "date";
	public static final String TOTAL_HOURS = "totalHours";
	public static final String RESPONSIBLE = "responsible";
	public static final String OBSERVATIONS = "observations";
	public static final String START_TIME = "startTime";
	public static final String STATE = "state";
	public static final String END_TIME = "endTime";
	public static final String STATE_BOXES = "stateBoxes";
	public static final String SANITIZED_ID = "sanitizedId";
	public static final String SANITIZED_BOXES = "sanitizedBoxes";

}

