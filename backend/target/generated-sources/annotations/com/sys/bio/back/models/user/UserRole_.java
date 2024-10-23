package com.sys.bio.back.models.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRole.class)
public abstract class UserRole_ {

	public static volatile SingularAttribute<UserRole, Role> role;
	public static volatile SingularAttribute<UserRole, Long> userRoleId;
	public static volatile SingularAttribute<UserRole, User> user;

	public static final String ROLE = "role";
	public static final String USER_ROLE_ID = "userRoleId";
	public static final String USER = "user";

}

