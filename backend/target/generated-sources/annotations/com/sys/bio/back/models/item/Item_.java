package com.sys.bio.back.models.item;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SingularAttribute<Item, Long> itemId;
	public static volatile SetAttribute<Item, Store> stores;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, String> format;
	public static volatile SingularAttribute<Item, String> description;
	public static volatile SingularAttribute<Item, Integer> currentAmount;
	public static volatile SingularAttribute<Item, Category> category;

	public static final String ITEM_ID = "itemId";
	public static final String STORES = "stores";
	public static final String NAME = "name";
	public static final String FORMAT = "format";
	public static final String DESCRIPTION = "description";
	public static final String CURRENT_AMOUNT = "currentAmount";
	public static final String CATEGORY = "category";

}

