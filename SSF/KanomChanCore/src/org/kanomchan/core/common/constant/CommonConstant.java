package org.kanomchan.core.common.constant;

import java.util.Locale;

public interface CommonConstant {
	public static final String SERVICE_STATUS_SUCCESS = "S";
    public static final String SERVICE_STATUS_FAIL = "F";
    public static final String FORCE_TO_NEXT_URL = "FORCE_TO_NEXT_URL";
    public static final String FORCE_TO_LOGGEDIN_WELCOME_PAGE = "FORCE_TO_LOGGEDIN_WELCOME_PAGE";
    public static final String FORCE_TO_LOGIN_PAGE = "FORCE_TO_LOGIN_PAGE";
	public static final String ACTIVE = "A";
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    public static final String DEFAULT_LANG_KEY = "ENG";
    public static final String DEFAULT_LANG_CONFIG = "DEFAULT_LANG_CONFIG";
    
    public static interface SESSION {
	// public static final String USER_BEAN = "SESSION_USER_BEAN";
	public static final String LANG_KEY = "SESSION_LANG_KEY";
	public static final String LOCALE_KEY = "SESSION_LOCALE_KEY";
	public static final String USER_BEAN_KEY = "SESSION_USER_BEAN_KEY";
	public static final String NAVIGATION_BEAN_KEY = "SESSION_NAVIGATION_BEAN_KEY";
	public static final String NEXT_URL_KEY = "SESSION_NEXT_URL_KEY";
	public static final String MENU_BEAN_KEY = "SESSION_MENU_BEAN_KEY";
	public static final String LOCATION_BEAN_KEY = "SESSION_LOCATION_BEAN_KEY";
    }
    
    public static interface PARAMETER {
	// public static final String USER_BEAN = "SESSION_USER_BEAN";
	public static final String LANG_KEY = "lang";
	public static final String LOCALE_KEY = "PARAMETER_LOCALE_KEY";
	public static final String USER_BEAN_KEY = "PARAMETER_USER_BEAN_KEY";
	public static final String NAVIGATION_BEAN_KEY = "PARAMETER_NAVIGATION_BEAN_KEY";
	public static final String NEXT_URL_KEY = "PARAMETER_NEXT_URL_KEY";
	public static final String MENU_BEAN_KEY = "PARAMETER_MENU_BEAN_KEY";
    }

    public static interface LOG {
	public static final String SESSION_ID = "SESSION_ID";
	public static final String CONTEXT_PATH = "CONTEXT_PATH";
	public static final String USER_ID = "USER_ID";
    }
    

	
    public static interface TEMPLATE {//Template engine
	public static final String INPUT_ENCODING = "UTF-8";
	public static final String OUTPUT_ENCODING = "UTF-8";
    }
}
