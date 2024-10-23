package com.sys.bio.back.models.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> image;
	public static volatile SetAttribute<User, UserRole> userRoles;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> photo;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String IMAGE = "image";
	public static final String USER_ROLES = "userRoles";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String PHOTO = "photo";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

