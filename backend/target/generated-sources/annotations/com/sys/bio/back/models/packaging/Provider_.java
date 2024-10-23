package com.sys.bio.back.models.packaging;

import com.sys.bio.back.models.item.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provider.class)
public abstract class Provider_ {

	public static volatile SingularAttribute<Provider, Long> providerId;
	public static volatile SingularAttribute<Provider, String> name;
	public static volatile SetAttribute<Provider, BoxName> boxNames;
	public static volatile SetAttribute<Provider, Package> packages;
	public static volatile SingularAttribute<Provider, Boolean> enabled;
	public static volatile SetAttribute<Provider, Product> products;

	public static final String PROVIDER_ID = "providerId";
	public static final String NAME = "name";
	public static final String BOX_NAMES = "boxNames";
	public static final String PACKAGES = "packages";
	public static final String ENABLED = "enabled";
	public static final String PRODUCTS = "products";

}

