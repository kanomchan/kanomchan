package org.kanomchan.core.openid.servicecase;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.mitre.discovery.util.JsonUtils;
import org.mitre.openid.connect.config.ServerConfiguration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheLoader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenIDConnectServiceConfigurationFetcher extends CacheLoader<String, ServerConfiguration> {
	private static Logger logger = LoggerFactory.getLogger(OpenIDConnectServiceConfigurationFetcher.class);

	private HttpClient httpClient= HttpClientBuilder.create().build();
	private HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	private JsonParser parser = new JsonParser();
	
	@Override
	public ServerConfiguration load(String issuer) throws Exception {
		RestTemplate restTemplate = new RestTemplate(httpFactory);

		// data holder
		ServerConfiguration conf = new ServerConfiguration();

		// construct the well-known URI
		String url = issuer + "/.well-known/openid-configuration";

		// fetch the value
		String jsonString = restTemplate.getForObject(url, String.class);

		JsonElement parsed = parser.parse(jsonString);
		if (parsed.isJsonObject()) {

			JsonObject o = parsed.getAsJsonObject();

			// sanity checks
			if (!o.has("issuer")) {
				throw new IllegalStateException("Returned object did not have an 'issuer' field");
			}

			if (!issuer.equals(o.get("issuer").getAsString())) {
				logger.info("Issuer used for discover was " + issuer + " but final issuer is " + o.get("issuer").getAsString());
			}

			conf.setIssuer(o.get("issuer").getAsString());


			conf.setAuthorizationEndpointUri(JsonUtils.getAsString(o, "authorization_endpoint"));
			conf.setTokenEndpointUri(JsonUtils.getAsString(o, "token_endpoint"));
			conf.setJwksUri(JsonUtils.getAsString(o, "jwks_uri"));
			conf.setUserInfoUri(JsonUtils.getAsString(o, "userinfo_endpoint"));
			conf.setRegistrationEndpointUri(JsonUtils.getAsString(o, "registration_endpoint"));
			conf.setIntrospectionEndpointUri(JsonUtils.getAsString(o, "introspection_endpoint"));
			conf.setAcrValuesSupported(JsonUtils.getAsStringList(o, "acr_values_supported"));
			conf.setCheckSessionIframe(JsonUtils.getAsString(o, "check_session_iframe"));
			conf.setClaimsLocalesSupported(JsonUtils.getAsStringList(o, "claims_locales_supported"));
			conf.setClaimsParameterSupported(JsonUtils.getAsBoolean(o, "claims_parameter_supported"));
			conf.setClaimsSupported(JsonUtils.getAsStringList(o, "claims_supported"));
			conf.setDisplayValuesSupported(JsonUtils.getAsStringList(o, "display_values_supported"));
			conf.setEndSessionEndpoint(JsonUtils.getAsString(o, "end_session_endpoint"));
			conf.setGrantTypesSupported(JsonUtils.getAsStringList(o, "grant_types_supported"));
			conf.setIdTokenSigningAlgValuesSupported(JsonUtils.getAsJwsAlgorithmList(o, "id_token_signing_alg_values_supported"));
			conf.setIdTokenEncryptionAlgValuesSupported(JsonUtils.getAsJweAlgorithmList(o, "id_token_encryption_alg_values_supported"));
			conf.setIdTokenEncryptionEncValuesSupported(JsonUtils.getAsEncryptionMethodList(o, "id_token_encryption_enc_values_supported"));
			conf.setOpPolicyUri(JsonUtils.getAsString(o, "op_policy_uri"));
			conf.setOpTosUri(JsonUtils.getAsString(o, "op_tos_uri"));
			conf.setRequestObjectEncryptionAlgValuesSupported(JsonUtils.getAsJweAlgorithmList(o, "request_object_encryption_alg_values_supported"));
			conf.setRequestObjectEncryptionEncValuesSupported(JsonUtils.getAsEncryptionMethodList(o, "request_object_encryption_enc_values_supported"));
			conf.setRequestObjectSigningAlgValuesSupported(JsonUtils.getAsJwsAlgorithmList(o, "request_object_signing_alg_values_supported"));
			conf.setRequestParameterSupported(JsonUtils.getAsBoolean(o, "request_parameter_supported"));
			conf.setRequestUriParameterSupported(JsonUtils.getAsBoolean(o, "request_uri_parameter_supported"));
			conf.setResponseTypesSupported(JsonUtils.getAsStringList(o, "response_types_supported"));
			conf.setScopesSupported(JsonUtils.getAsStringList(o, "scopes_supported"));
			conf.setSubjectTypesSupported(JsonUtils.getAsStringList(o, "subject_types_supported"));
			conf.setServiceDocumentation(JsonUtils.getAsString(o, "service_documentation"));
			conf.setTokenEndpointAuthMethodsSupported(JsonUtils.getAsStringList(o, "token_endpoint_auth_methods"));
			conf.setTokenEndpointAuthSigningAlgValuesSupported(JsonUtils.getAsJwsAlgorithmList(o, "token_endpoint_auth_signing_alg_values_supported"));
			conf.setUiLocalesSupported(JsonUtils.getAsStringList(o, "ui_locales_supported"));
			conf.setUserinfoEncryptionAlgValuesSupported(JsonUtils.getAsJweAlgorithmList(o, "userinfo_encryption_alg_values_supported"));
			conf.setUserinfoEncryptionEncValuesSupported(JsonUtils.getAsEncryptionMethodList(o, "userinfo_encryption_enc_values_supported"));
			conf.setUserinfoSigningAlgValuesSupported(JsonUtils.getAsJwsAlgorithmList(o, "userinfo_signing_alg_values_supported"));

			return conf;
		} else {
			throw new IllegalStateException("Couldn't parse server discovery results for " + url);
		}
	}

}
