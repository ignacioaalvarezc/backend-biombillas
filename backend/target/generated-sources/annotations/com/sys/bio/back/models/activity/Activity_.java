package com.sys.bio.back.models.activity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Activity.class)
public abstract class Activity_ {

	public static volatile SingularAttribute<Activity, Long> activityId;
	public static volatile SingularAttribute<Activity, String> name;
	public static volatile SingularAttribute<Activity, String> description;
	public static volatile SingularAttribute<Activity, Boolean> enabled;
	public static volatile SetAttribute<Activity, ExtraTask> extraTasks;

	public static final String ACTIVITY_ID = "activityId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ENABLED = "enabled";
	public static final String EXTRA_TASKS = "extraTasks";

}

