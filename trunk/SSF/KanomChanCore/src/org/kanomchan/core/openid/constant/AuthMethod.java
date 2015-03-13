package org.kanomchan.core.openid.constant;

import java.util.HashMap;
import java.util.Map;

public enum AuthMethod {
	SECRET_POST("client_secret_post"),
	SECRET_BASIC("client_secret_basic"),
	SECRET_JWT("client_secret_jwt"),
	PRIVATE_KEY("private_key_jwt"),
	NONE("none");

	private final String value;

	// map to aid reverse lookup
	private static final Map<String, AuthMethod> lookup = new HashMap<String, AuthMethod>();
	static {
		for (AuthMethod a : AuthMethod.values()) {
			lookup.put(a.getValue(), a);
		}
	}

	AuthMethod(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static AuthMethod getByValue(String value) {
		return lookup.get(value);
	}
}
