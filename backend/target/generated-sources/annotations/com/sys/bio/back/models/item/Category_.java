package com.sys.bio.back.models.item;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> name;
	public static volatile SetAttribute<Category, Item> items;
	public static volatile SingularAttribute<Category, Long> categoryId;

	public static final String NAME = "name";
	public static final String ITEMS = "items";
	public static final String CATEGORY_ID = "categoryId";

}

