package org.kanomchan.core.openid.servicecase;

import static org.mitre.discovery.util.JsonUtils.getAsDate;
import static org.mitre.discovery.util.JsonUtils.getAsString;
import static org.mitre.discovery.util.JsonUtils.getAsStringSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.kanomchan.core.openid.bean.RegisteredClient;
import org.kanomchan.core.openid.bean.RegisteredClientDefault;
import org.mitre.openid.connect.config.ServerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Splitter;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DynamicClientRegistrationLoader extends CacheLoader<ServerConfiguration, RegisteredClient> {
	private static Logger logger = LoggerFactory.getLogger(DynamicClientRegistrationLoader.class);
	private Map<String, RegisteredClient> clients = new HashMap<String, RegisteredClient>();
//	private HttpClient httpClient = new HttpClient();
//	private RegisteredClient template;
	private HttpClient httpClient= HttpClientBuilder.create().build();
	private HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	
//	private ClientHttpRequestFactory httpFactory = new CommonsClientHttpRequestFactory(httpClient);
	private Gson gson = new Gson(); // note that this doesn't serialize nulls by default
	private Set<String> scopes;
//	scopes.add("openid");
//	scopes.add("email");
//	scopes.add("address");
//	scopes.add("profile");
//	scopes.add("phone");
	private Set<String> redirectUris;
	public DynamicClientRegistrationLoader(Set<String> scopes,Set<String> redirectUris) {
		this.scopes = scopes;
		this.redirectUris = redirectUris;
//		this.template = template;
	}
	@Override
	public RegisteredClient load(ServerConfiguration serverConfig) throws Exception {
		RestTemplate restTemplate = new RestTemplate(httpFactory);
		
		RegisteredClient knownClient = clients.get(serverConfig.getIssuer());
		
		if (knownClient == null) {
			RegisteredClient client = new RegisteredClientDefault();
			
			client.setClientId("710e35f7-df9f-48af-961b-b0016cab6998");
			client.setClientSecret("AJQp1NQvx8Gl_RL2wLDKGrE-nx8i9jmHCK7hAC4iMEXnM_uxBV0Zw8QoHynfe0zSjdW0Z0XJfwLQGpVdpssIXjY");
//			JsonObject jsonRequest = new JsonObject();
			
//			jsonRequest.addProperty("clientId", client.getClientId());
//			jsonRequest.addProperty("clientSecret", client.getClientSecret());
////			String json = gson.toJson(jsonRequest);

			MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
			form.add("clientId", client.getClientId());
			form.add("clientSecret", client.getClientSecret());
//			HttpHeaders headers = new HttpHeaders();
//			
//			headers.setContentType(MediaType.valueOf("application/json+json"));
//			headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));

//			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			String registered = restTemplate.postForObject("http://localhost:8080/OIWeb/action/client/auth", form, String.class);
//			// TODO: handle HTTP errors
	//
			client = parseRegistered(registered);
			
			clients.put(serverConfig.getIssuer(), client);
			
			return client;
		}

		return knownClient;

//		RegisteredClient knownClient = clients.get(serverConfig.getIssuer());
//		if (knownClient == null) {
//
//			// dynamically register this client
////			template = new RegisteredClient();
////			template.setClientName("Simple Web App");
////			template.setScope(scopes);
////			template.setTokenEndpointAuthMethod(AuthMethod.SECRET_POST);
////			template.setRedirectUris(redirectUris);
////			template.setRequestObjectSigningAlg(JWSAlgorithm.RS256);
////			template.setJwksUri("http://localhost:8080/simple-web-app/jwk");
////			JsonObject jsonRequest = ClientDetailsEntityJsonProcessor.serialize(template);
//			
//			JsonObject jsonRequest = new JsonObject();
////			
////			jsonRequest.addProperty("client_id", c.getClientId());
////			if (c.getClientSecret() != null) {
////				jsonRequest.addProperty("client_secret", serverConfig.get);
////
////				if (c.getClientSecretExpiresAt() == null) {
////					jsonRequest.addProperty("client_secret_expires_at", 0); // TODO: do we want to let secrets expire?
////				} else {
////					jsonRequest.addProperty("client_secret_expires_at", c.getClientSecretExpiresAt().getTime() / 1000L);
////				}
////			}
////
////			if (c.getClientIdIssuedAt() != null) {
////				jsonRequest.addProperty("client_id_issued_at", c.getClientIdIssuedAt().getTime() / 1000L);
////			} else if (c.getCreatedAt() != null) {
////				jsonRequest.addProperty("client_id_issued_at", c.getCreatedAt().getTime() / 1000L);
////			}
////			if (c.getRegistrationAccessToken() != null) {
////				jsonRequest.addProperty("registration_access_token", c.getRegistrationAccessToken());
////			}
////
////			if (c.getRegistrationClientUri() != null) {
////				jsonRequest.addProperty("registration_client_uri", c.getRegistrationClientUri());
////			}
////
////
////			// add in all other client properties
////
////			Set<String> scopes = new HashSet<String>();
////			scopes.add("openid");
////			scopes.add("email");
////			scopes.add("address");
////			scopes.add("profile");
////			scopes.add("phone");
////			Set<String> redirectUris = new HashSet<String>();
////			redirectUris.add("http://localhost:8080/simple-web-app/openid_connect_login");
////			
////			// OAuth DynReg
//			jsonRequest.add("redirect_uris", getAsArray(redirectUris));
//			jsonRequest.addProperty("client_name", "Simple Web App");
////			jsonRequest.addProperty("client_uri", c.getClientUri());
////			jsonRequest.addProperty("logo_uri", c.getLogoUri());
////			jsonRequest.add("contacts", getAsArray(c.getContacts()));
////			jsonRequest.addProperty("tos_uri", c.getTosUri());
//			jsonRequest.addProperty("token_endpoint_auth_method", "SECRET_BASIC");
//			jsonRequest.addProperty("scope", scopes != null ? Joiner.on(" ").join(scopes) : null);
////			jsonRequest.add("grant_types", getAsArray(c.getGrantTypes()));
////			jsonRequest.add("response_types", getAsArray(c.getResponseTypes()));
////			jsonRequest.addProperty("policy_uri", c.getPolicyUri());
//			jsonRequest.addProperty("jwks_uri", "http://localhost:8080/simple-web-app/jwk");
////
////			// OIDC Registration
////			jsonRequest.addProperty("application_type", c.getApplicationType() != null ? c.getApplicationType().getValue() : null);
////			jsonRequest.addProperty("sector_identifier_uri", c.getSectorIdentifierUri());
////			jsonRequest.addProperty("subject_type", c.getSubjectType() != null ? c.getSubjectType().getValue() : null);
//			jsonRequest.addProperty("request_object_signing_alg", "RS256");
////			jsonRequest.addProperty("userinfo_signed_response_alg", (String)null);
////			jsonRequest.addProperty("userinfo_encrypted_response_alg", (String)null);
////			jsonRequest.addProperty("userinfo_encrypted_response_enc", (String)null);
////			jsonRequest.addProperty("id_token_signed_response_alg", (String)null);
////			jsonRequest.addProperty("id_token_encrypted_response_alg", (String)null);
////			jsonRequest.addProperty("id_token_encrypted_response_enc", (String)null);
////			jsonRequest.addProperty("token_endpoint_auth_signing_alg", (String)null);
////			jsonRequest.addProperty("default_max_age", (String)null);
////			jsonRequest.addProperty("require_auth_time", (String)null);
////			jsonRequest.add("default_acr_values", getAsArray(c.getDefaultACRvalues()));
////			jsonRequest.addProperty("initiate_login_uri", c.getInitiateLoginUri());
////			jsonRequest.addProperty("post_logout_redirect_uri", c.getPostLogoutRedirectUri());
////			jsonRequest.add("request_uris", getAsArray(c.getRequestUris()));
////			return o;
//			String json = gson.toJson(jsonRequest);
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
//
//			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
////
//			String registered = restTemplate.postForObject(serverConfig.getRegistrationEndpointUri(), entity, String.class);
//			// TODO: handle HTTP errors
//
//			RegisteredClient client = parseRegistered(registered);
//
//			// save this client for later
//			clients.put(serverConfig.getIssuer(), client);
//
//			return client;
//		} else {
//
//			if (knownClient.getClientId() == null) {
//			
//				// load this client's information from the server
//				HttpHeaders headers = new HttpHeaders();
//				headers.set("Authorization", String.format("%s %s", "Bearer", knownClient.getRegistrationAccessToken()));
//				headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
//
//				HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//				String registered = restTemplate.exchange(knownClient.getRegistrationClientUri(), HttpMethod.GET, entity, String.class).getBody();
//				// TODO: handle HTTP errors
//
//				RegisteredClient client = parseRegistered(registered);
//
//				return client;
//			} else {
//				// it's got a client ID from the store, don't bother trying to load it
//				return knownClient;
//			}
//		}
	}
	private static JsonParser parser = new JsonParser();
	private RegisteredClient parseRegistered(String registeredString){
		
//		RegisteredClient client = new RegisteredClient();
		JsonElement jsonEl = parser.parse(registeredString);
		if (jsonEl.isJsonObject()) {

			JsonObject o = jsonEl.getAsJsonObject();
//			ClientDetailsEntity c = parse(jsonEl);

			RegisteredClient c = new RegisteredClientDefault();
			// get any fields from the registration
			c.setRegistrationAccessToken(getAsString(o, "registration_access_token"));
			c.setRegistrationClientUri(getAsString(o, "registration_client_uri"));
			c.setClientIdIssuedAt(getAsDate(o, "client_id_issued_at"));
			c.setClientSecretExpiresAt(getAsDate(o, "client_secret_expires_at"));


			// TODO: make these field names into constants

			// these two fields should only be sent in the update request, and MUST match existing values
			c.setClientId(getAsString(o, "client_id"));
			c.setClientSecret(getAsString(o, "client_secret"));

			// OAuth DynReg
			c.setRedirectUris(getAsStringSet(o, "redirect_uris"));
			c.setClientName(getAsString(o, "client_name"));
			c.setClientUri(getAsString(o, "client_uri"));
			c.setLogoUri(getAsString(o, "logo_uri"));
			c.setContacts(getAsStringSet(o, "contacts"));
			c.setTosUri(getAsString(o, "tos_uri"));

			String authMethod = getAsString(o, "token_endpoint_auth_method");
//			if (authMethod != null) {
//				c.setTokenEndpointAuthMethod(AuthMethod.getByValue(authMethod));
//			}

			// scope is a space-separated string
			String scope = getAsString(o, "scope");
			if (scope != null) {
				c.setScope(Sets.newHashSet(Splitter.on(" ").split(scope)));
			}

			c.setGrantTypes(getAsStringSet(o, "grant_types"));
			c.setResponseTypes(getAsStringSet(o, "response_types"));
			c.setPolicyUri(getAsString(o, "policy_uri"));
			c.setJwksUri(getAsString(o, "jwks_uri"));


			// OIDC Additions
//			String appType = getAsString(o, "application_type");
//			if (appType != null) {
//				c.setApplicationType(AppType.getByValue(appType));
//			}
//
//			c.setSectorIdentifierUri(getAsString(o, "sector_identifier_uri"));
//
//			String subjectType = getAsString(o, "subject_type");
//			if (subjectType != null) {
//				c.setSubjectType(SubjectType.getByValue(subjectType));
//			}
//
//			c.setRequestObjectSigningAlg(getAsJwsAlgorithm(o, "request_object_signing_alg"));
//
//			c.setUserInfoSignedResponseAlg(getAsJwsAlgorithm(o, "userinfo_signed_response_alg"));
//			c.setUserInfoEncryptedResponseAlg(getAsJweAlgorithm(o, "userinfo_encrypted_response_alg"));
//			c.setUserInfoEncryptedResponseEnc(getAsJweEncryptionMethod(o, "userinfo_encrypted_response_enc"));
//
//			c.setIdTokenSignedResponseAlg(getAsJwsAlgorithm(o, "id_token_signed_response_alg"));
//			c.setIdTokenEncryptedResponseAlg(getAsJweAlgorithm(o, "id_token_encrypted_response_alg"));
//			c.setIdTokenEncryptedResponseEnc(getAsJweEncryptionMethod(o, "id_token_encrypted_response_enc"));
//
//			c.setTokenEndpointAuthSigningAlg(getAsJwsAlgorithm(o, "token_endpoint_auth_signing_alg"));

			if (o.has("default_max_age")) {
				if (o.get("default_max_age").isJsonPrimitive()) {
					c.setDefaultMaxAge(o.get("default_max_age").getAsInt());
				}
			}

			if (o.has("require_auth_time")) {
				if (o.get("require_auth_time").isJsonPrimitive()) {
					c.setRequireAuthTime(o.get("require_auth_time").getAsBoolean());
				}
			}

			c.setDefaultACRvalues(getAsStringSet(o, "default_acr_values"));
			c.setInitiateLoginUri(getAsString(o, "initiate_login_uri"));
			c.setPostLogoutRedirectUri(getAsString(o, "post_logout_redirect_uri"));
			c.setRequestUris(getAsStringSet(o, "request_uris"));

			return c;
		} else {
			return null;
		}
//		return client;
	}
}
