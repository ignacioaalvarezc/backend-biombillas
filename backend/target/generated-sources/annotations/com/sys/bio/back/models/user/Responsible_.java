package com.sys.bio.back.models.user;

import com.sys.bio.back.models.activity.ExtraTask;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.cutting.Cutting;
import com.sys.bio.back.models.packaging.Packaging;
import com.sys.bio.back.models.reception.Reception;
import com.sys.bio.back.models.sized.SizedBox;
import com.sys.bio.back.models.sized.Sizing;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Responsible.class)
public abstract class Responsible_ {

	public static volatile SetAttribute<Responsible, Cutting> cuttings;
	public static volatile SetAttribute<Responsible, Packaging> packagings;
	public static volatile SetAttribute<Responsible, CutBox> cutBoxes;
	public static volatile SetAttribute<Responsible, Reception> receptions;
	public static volatile SetAttribute<Responsible, Sizing> sizings;
	public static volatile SingularAttribute<Responsible, String> name;
	public static volatile SetAttribute<Responsible, SizedBox> sizedBoxes;
	public static volatile SingularAttribute<Responsible, Long> responsibleId;
	public static volatile SingularAttribute<Responsible, String> email;
	public static volatile SingularAttribute<Responsible, Boolean> enabled;
	public static volatile SetAttribute<Responsible, ExtraTask> extraTasks;

	public static final String CUTTINGS = "cuttings";
	public static final String PACKAGINGS = "packagings";
	public static final String CUT_BOXES = "cutBoxes";
	public static final String RECEPTIONS = "receptions";
	public static final String SIZINGS = "sizings";
	public static final String NAME = "name";
	public static final String SIZED_BOXES = "sizedBoxes";
	public static final String RESPONSIBLE_ID = "responsibleId";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";
	public static final String EXTRA_TASKS = "extraTasks";

}

