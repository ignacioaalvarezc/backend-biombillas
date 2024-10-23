package com.sys.bio.back.models.packaging;

import com.sys.bio.back.models.item.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BoxFormat.class)
public abstract class BoxFormat_ {

	public static volatile SingularAttribute<BoxFormat, Integer> amount;
	public static volatile SingularAttribute<BoxFormat, String> name;
	public static volatile SingularAttribute<BoxFormat, Long> boxFormatId;
	public static volatile SingularAttribute<BoxFormat, BoxName> boxName;
	public static volatile SetAttribute<BoxFormat, Package> packages;
	public static volatile SingularAttribute<BoxFormat, Boolean> enabled;
	public static volatile SetAttribute<BoxFormat, Product> products;

	public static final String AMOUNT = "amount";
	public static final String NAME = "name";
	public static final String BOX_FORMAT_ID = "boxFormatId";
	public static final String BOX_NAME = "boxName";
	public static final String PACKAGES = "packages";
	public static final String ENABLED = "enabled";
	public static final String PRODUCTS = "products";

}

