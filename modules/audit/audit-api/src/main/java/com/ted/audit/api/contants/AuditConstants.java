package com.ted.audit.api.contants;

public interface AuditConstants {
	/**
	 * Las acciones sobre el usuario van a ir del 1 al 1000
	 */
	public static final int USER_CREATE = 1;
	public static final int USER_UPDATE = 2;
	public static final int USER_REMOVE = 3;
	public static final int USER_ADD_ORGANIZATION = 4;
	public static final int USER_REMOVE_ORGANIZATION = 5;
	public static final int USER_ADD_GROUP = 6;
	public static final int USER_REMOVE_GROUP = 7;
	public static final int USER_ADD_ROLE = 8;
	public static final int USER_REMOVE_ROLE = 9;
	public static final int USER_ADD_TEAM = 10;
	public static final int USER_REMOVE_TEAM = 11;
	public static final int USER_ADD_USER_GROUP = 12;
	public static final int USER_REMOVE_USER_GROUP = 13;
	public static final int USER_UPDATE_PASSWORD = 14;
	public static final int USER_DO_AS_USER = 15;
	public static final int USER_LOCKOUT = 16;
	public static final int USER_AGREED_TO_TERMS_OF_USE = 17;
	public static final int USER_EMAIL_ADDRESS_VERIFIED = 18;
	public static final int USER_IMPERSONATED = 19;
	
	public static int getUSERIMPERSONATED(){
		return USER_IMPERSONATED;
	}
	
	/**
	 * Las acciones sobre contact van a ir del 1001 al 1500
	 */
	
	public static final int CONTACT_CREATE = 1001;
	public static final int CONTACT_UPDATE = 1002;
	public static final int CONTACT_REMOVE = 1003;
	
	/**
	 * Las acciones sobre address van a ir del 1501 al 2000
	 */
	
	public static final int ADDRESS_CREATE = 1501;
	public static final int ADDRESS_UPDATE = 1502;
	public static final int ADDRESS_REMOVE = 1503;
	
	/**
	 * Las acciones sobre expandovalue van a ir del 2001 al 2500
	 */
	
	public static final int EXPANDO_VALUE_CREATE = 2001;
	public static final int EXPANDO_VALUE_UPDATE = 2002;
	public static final int EXPANDO_VALUE_REMOVE = 2003;
	
	/**
	 * Las acciones genéricas van a ir del 5000 al 5500
	 */
	public static final int GET = 5000;
	public static final int ADD = 5001;
}
