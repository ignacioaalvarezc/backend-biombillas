package com.sys.bio.back.models.cutting;

import com.sys.bio.back.models.user.Responsible;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CutBox.class)
public abstract class CutBox_ {

	public static volatile SingularAttribute<CutBox, Date> date;
	public static volatile SingularAttribute<CutBox, LocalDate> filterDate;
	public static volatile SingularAttribute<CutBox, Long> cutBoxId;
	public static volatile SingularAttribute<CutBox, Integer> amount;
	public static volatile SingularAttribute<CutBox, LocalTime> hour;
	public static volatile SingularAttribute<CutBox, CutType> cutType;
	public static volatile SingularAttribute<CutBox, Responsible> responsible;
	public static volatile SingularAttribute<CutBox, Cutting> cutting;
	public static volatile SingularAttribute<CutBox, Double> weight;
	public static volatile SingularAttribute<CutBox, String> state;

	public static final String DATE = "date";
	public static final String FILTER_DATE = "filterDate";
	public static final String CUT_BOX_ID = "cutBoxId";
	public static final String AMOUNT = "amount";
	public static final String HOUR = "hour";
	public static final String CUT_TYPE = "cutType";
	public static final String RESPONSIBLE = "responsible";
	public static final String CUTTING = "cutting";
	public static final String WEIGHT = "weight";
	public static final String STATE = "state";

}

