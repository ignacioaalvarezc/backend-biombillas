package com.sys.bio.back.models.order;

import com.sys.bio.back.models.item.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProduct.class)
public abstract class OrderProduct_ {

	public static volatile SingularAttribute<OrderProduct, Integer> totalValue;
	public static volatile SingularAttribute<OrderProduct, Product> product;
	public static volatile SingularAttribute<OrderProduct, Integer> quantity;
	public static volatile SingularAttribute<OrderProduct, Integer> unityValue;
	public static volatile SingularAttribute<OrderProduct, Long> orderProductId;
	public static volatile SingularAttribute<OrderProduct, Order> order;

	public static final String TOTAL_VALUE = "totalValue";
	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String UNITY_VALUE = "unityValue";
	public static final String ORDER_PRODUCT_ID = "orderProductId";
	public static final String ORDER = "order";

}

