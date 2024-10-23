package com.sys.bio.back.models.order;

import java.time.LocalDate;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, LocalDate> date;
	public static volatile SingularAttribute<Order, Date> pickUpDate;
	public static volatile SingularAttribute<Order, String> indications;
	public static volatile SingularAttribute<Order, String> orderNumber;
	public static volatile SingularAttribute<Order, Long> orderId;
	public static volatile SetAttribute<Order, OrderProduct> orderProducts;
	public static volatile SingularAttribute<Order, String> state;

	public static final String DATE = "date";
	public static final String PICK_UP_DATE = "pickUpDate";
	public static final String INDICATIONS = "indications";
	public static final String ORDER_NUMBER = "orderNumber";
	public static final String ORDER_ID = "orderId";
	public static final String ORDER_PRODUCTS = "orderProducts";
	public static final String STATE = "state";

}

