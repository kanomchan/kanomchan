package org.kanomchan.core.openid.service;

import java.util.Map;
import java.util.Set;

import org.mitre.openid.connect.config.ServerConfiguration;

public interface AuthRequestUrlBuilder {
	public String buildAuthRequestUrl(ServerConfiguration serverConfig, String clientId, Set<String> Scope, String redirectUri, String nonce, String state, Map<String, String> options);
}
