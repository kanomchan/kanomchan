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
	public static final String ENG = "ENG";
    
    public static interface SESSION {
	// public static final String USER_BEAN = "SESSION_USER_BEAN";
	public static final String LANG_KEY = "SESSION_LANG_KEY";
	public static final String LANG_LIST_KEY = "SESSION_LANG_LIST_KEY";
	public static final String LOCALE_KEY = "SESSION_LOCALE_KEY";
	public static final String USER_BEAN_KEY = "SESSION_USER_BEAN_KEY";
	public static final String NAVIGATION_BEAN_KEY = "SESSION_NAVIGATION_BEAN_KEY";
	public static final String NEXT_URL_KEY = "SESSION_NEXT_URL_KEY";
	public static final String MENU_BEAN_KEY = "SESSION_MENU_BEAN_KEY";
	public static final String MENU_BEAN_MAP_KEY = "SESSION_MENU_BEAN_MAP_KEY";
	public static final String LOCATION_BEAN_KEY = "SESSION_LOCATION_BEAN_KEY";
	public static final String ACT_CUR_KEY = "SESSION_ACT_CUR_KEY";
	
	public static final String URL_REDIRECT_FACEBOOK_KEY = "SESSION_URL_REDIRECT_FACEBOOK_KEY";
	public static final String USER_ID = "SESSION_USER_ID";
	public static final String USER_NAME = "SESSION_USER_NAME";
	
	public static final String PERCENT_KEY = "SESSION_PERCENT_KEY";
	
	public static final String USER_CURRENT_MODULE = "USER_CURRENT_MODULE";
	
	//ACTIVE_ACCOUNT_KEY is for UserDefualt Bean
	public static final String ACTIVE_ACCOUNT_KEY = "SESSION_ACTIVE_ACCOUNT_KEY";
	
	public static final String CART_ID = "SESSION_CART_ID";
	public static final String CART_MAP_LEVEL = "SESSION_CART_MAP_LEVEL";
	public static final String CART_RESULT = "SESSION_CART_RESULT";
	
	public static final String CART_ID_TEST_SET = "SESSION_CART_ID_TEST_SET";
	public static final String CART_MAP_LEVEL_TEST_SET = "SESSION_CART_MAP_LEVEL_TEST_SET";
	public static final String CART_RESULT_TEST_SET = "SESSION_CART_RESULT_TEST_SET";
	
    }
    
    public static interface PARAMETER {
	// public static final String USER_BEAN = "SESSION_USER_BEAN";
	public static final String LANG_KEY = "request_locale";
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
	public static final String USER_NAME = "USER_NAME";
	public static final String SERVER_NAME = "SERVER_NAME";
	public static final String SERVER_PORT = "SERVER_PORT";
	public static final String SERVER_INSTANCE_IP = "SERVER_INSTANCE_IP";
	public static final String SERVER_INSTANCE_NAME = "SERVER_INSTANCE_NAME";
	public static final String SERVER_INSTANCE_SERVER_NAME = "SERVER_INSTANCE_SERVER_NAME";
	
    }
    

	
    public static interface TEMPLATE {//Template engine
	public static final String INPUT_ENCODING = "UTF-8";
	public static final String OUTPUT_ENCODING = "UTF-8";
    }
    
    public static interface EXAMINE {
    	public static final String PARAM_SELECT_TEST = "PARAM_SELECT_TEST";
    }
}
