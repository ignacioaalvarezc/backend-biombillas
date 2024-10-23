package com.sys.bio.back.models.item;

import com.sys.bio.back.models.order.OrderProduct;
import com.sys.bio.back.models.packaging.BoxFormat;
import com.sys.bio.back.models.packaging.BoxName;
import com.sys.bio.back.models.packaging.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, Provider> provider;
	public static volatile SetAttribute<Product, Inventory> inventories;
	public static volatile SingularAttribute<Product, Integer> price;
	public static volatile SingularAttribute<Product, BoxFormat> boxFormat;
	public static volatile SetAttribute<Product, OrderProduct> orderProducts;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, BoxName> boxName;
	public static volatile SingularAttribute<Product, Integer> currentAmount;
	public static volatile SingularAttribute<Product, Integer> strawAmount;

	public static final String PRODUCT_ID = "productId";
	public static final String PROVIDER = "provider";
	public static final String INVENTORIES = "inventories";
	public static final String PRICE = "price";
	public static final String BOX_FORMAT = "boxFormat";
	public static final String ORDER_PRODUCTS = "orderProducts";
	public static final String NAME = "name";
	public static final String BOX_NAME = "boxName";
	public static final String CURRENT_AMOUNT = "currentAmount";
	public static final String STRAW_AMOUNT = "strawAmount";

}

