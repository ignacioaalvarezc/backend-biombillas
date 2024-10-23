package com.sys.bio.back.models.item;

import com.sys.bio.back.models.enums.StoreMovement;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Store.class)
public abstract class Store_ {

	public static volatile SingularAttribute<Store, LocalDate> date;
	public static volatile SingularAttribute<Store, Item> item;
	public static volatile SingularAttribute<Store, Integer> amount;
	public static volatile SingularAttribute<Store, LocalTime> hour;
	public static volatile SingularAttribute<Store, Long> storeId;
	public static volatile SingularAttribute<Store, StoreMovement> movement;

	public static final String DATE = "date";
	public static final String ITEM = "item";
	public static final String AMOUNT = "amount";
	public static final String HOUR = "hour";
	public static final String STORE_ID = "storeId";
	public static final String MOVEMENT = "movement";

}

