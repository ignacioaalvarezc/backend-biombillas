package com.sys.bio.back.models.item;

import com.sys.bio.back.models.enums.StoreMovement;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inventory.class)
public abstract class Inventory_ {

	public static volatile SingularAttribute<Inventory, LocalDate> date;
	public static volatile SingularAttribute<Inventory, Product> product;
	public static volatile SingularAttribute<Inventory, Integer> amount;
	public static volatile SingularAttribute<Inventory, LocalTime> hour;
	public static volatile SingularAttribute<Inventory, Long> inventoryId;
	public static volatile SingularAttribute<Inventory, StoreMovement> movement;

	public static final String DATE = "date";
	public static final String PRODUCT = "product";
	public static final String AMOUNT = "amount";
	public static final String HOUR = "hour";
	public static final String INVENTORY_ID = "inventoryId";
	public static final String MOVEMENT = "movement";

}

