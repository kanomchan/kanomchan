package org.kanomchan.core.openid.service;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.utils.URIBuilder;
import org.mitre.openid.connect.config.ServerConfiguration;

import com.google.common.base.Joiner;

public class AuthRequestUrlBuilderImpl implements AuthRequestUrlBuilder {

	@Override
	public String buildAuthRequestUrl(ServerConfiguration serverConfig, String clientId, Set<String> Scope, String redirectUri, String nonce, String state, Map<String, String> options) {
		try {

			URIBuilder uriBuilder = new URIBuilder(serverConfig.getAuthorizationEndpointUri());
			uriBuilder.addParameter("response_type", "code");
			uriBuilder.addParameter("client_id", clientId);
			uriBuilder.addParameter("scope", Joiner.on(" ").join(Scope));

			uriBuilder.addParameter("redirect_uri", redirectUri);

			uriBuilder.addParameter("nonce", nonce);

			uriBuilder.addParameter("state", state);

			// Optional parameters:
			for (Entry<String, String> option : options.entrySet()) {
				uriBuilder.addParameter(option.getKey(), option.getValue());
			}

			return uriBuilder.build().toString();

		} catch (URISyntaxException e) {
//			throw new Exception("Malformed Authorization Endpoint Uri", e);

		}
		return null;
	}

}
