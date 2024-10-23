package com.sys.bio.back.models.sized;

import com.sys.bio.back.models.sanitized.SanitizedBox;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StrawType.class)
public abstract class StrawType_ {

	public static volatile SingularAttribute<StrawType, Long> strawTypeId;
	public static volatile SingularAttribute<StrawType, String> name;
	public static volatile SingularAttribute<StrawType, String> description;
	public static volatile SetAttribute<StrawType, SizedBox> sizedBoxes;
	public static volatile SingularAttribute<StrawType, Double> factor;
	public static volatile SingularAttribute<StrawType, Boolean> enabled;
	public static volatile SetAttribute<StrawType, SanitizedBox> sanitizedBoxes;

	public static final String STRAW_TYPE_ID = "strawTypeId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SIZED_BOXES = "sizedBoxes";
	public static final String FACTOR = "factor";
	public static final String ENABLED = "enabled";
	public static final String SANITIZED_BOXES = "sanitizedBoxes";

}

