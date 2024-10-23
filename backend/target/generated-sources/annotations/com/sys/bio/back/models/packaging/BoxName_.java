package com.sys.bio.back.models.packaging;

import com.sys.bio.back.models.item.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BoxName.class)
public abstract class BoxName_ {

	public static volatile SingularAttribute<BoxName, Long> boxNameId;
	public static volatile SingularAttribute<BoxName, Provider> provider;
	public static volatile SingularAttribute<BoxName, String> name;
	public static volatile SetAttribute<BoxName, Package> packages;
	public static volatile SingularAttribute<BoxName, Boolean> enabled;
	public static volatile SetAttribute<BoxName, BoxFormat> boxFormats;
	public static volatile SetAttribute<BoxName, Product> products;

	public static final String BOX_NAME_ID = "boxNameId";
	public static final String PROVIDER = "provider";
	public static final String NAME = "name";
	public static final String PACKAGES = "packages";
	public static final String ENABLED = "enabled";
	public static final String BOX_FORMATS = "boxFormats";
	public static final String PRODUCTS = "products";

}

