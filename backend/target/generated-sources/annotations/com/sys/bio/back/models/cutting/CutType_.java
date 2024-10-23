package com.sys.bio.back.models.cutting;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CutType.class)
public abstract class CutType_ {

	public static volatile SingularAttribute<CutType, Long> cutTypeId;
	public static volatile SetAttribute<CutType, CutBox> cutBoxes;
	public static volatile SingularAttribute<CutType, String> name;
	public static volatile SingularAttribute<CutType, String> description;
	public static volatile SingularAttribute<CutType, Double> factor;
	public static volatile SingularAttribute<CutType, Boolean> enabled;

	public static final String CUT_TYPE_ID = "cutTypeId";
	public static final String CUT_BOXES = "cutBoxes";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String FACTOR = "factor";
	public static final String ENABLED = "enabled";

}

