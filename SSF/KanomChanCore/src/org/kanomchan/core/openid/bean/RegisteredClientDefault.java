package org.kanomchan.core.openid.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kanomchan.core.openid.constant.AuthMethod;
import org.mitre.jose.JWEAlgorithmEmbed;
import org.mitre.jose.JWEEncryptionMethodEmbed;
import org.mitre.jose.JWSAlgorithmEmbed;
//import org.mitre.oauth2.model.ClientDetailsEntity.AppType;
//import org.mitre.oauth2.model.ClientDetailsEntity.AuthMethod;
//import org.mitre.oauth2.model.ClientDetailsEntity.SubjectType;


import org.mitre.oauth2.model.ClientDetailsEntity.AppType;
import org.mitre.oauth2.model.ClientDetailsEntity.SubjectType;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;

public class RegisteredClientDefault implements RegisteredClient {
	
	private String registrationAccessToken;
	private String registrationClientUri;
	private Date clientSecretExpiresAt;
	private Date clientIdIssuedAt;
	
	public String getRegistrationAccessToken() {
		return registrationAccessToken;
	}

	public void setRegistrationAccessToken(String registrationAccessToken) {
		this.registrationAccessToken = registrationAccessToken;
	}

	public String getRegistrationClientUri() {
		return registrationClientUri;
	}

	public void setRegistrationClientUri(String registrationClientUri) {
		this.registrationClientUri = registrationClientUri;
	}

	public Date getClientSecretExpiresAt() {
		return clientSecretExpiresAt;
	}

	public void setClientSecretExpiresAt(Date clientSecretExpiresAt) {
		this.clientSecretExpiresAt = clientSecretExpiresAt;
	}

	public Date getClientIdIssuedAt() {
		return clientIdIssuedAt;
	}

	public void setClientIdIssuedAt(Date clientIdIssuedAt) {
		this.clientIdIssuedAt = clientIdIssuedAt;
	}
	
	
	private String clientId = null; // client_id
	private String clientSecret = null; // client_secret
	private Set<String> redirectUris = new HashSet<String>(); // redirect_uris
	private String clientName; // client_name
	private String clientUri; // client_uri
	private String logoUri; // logo_uri
	private Set<String> contacts; // contacts
	private String tosUri; // tos_uri
	private AuthMethod tokenEndpointAuthMethod = AuthMethod.SECRET_BASIC; // token_endpoint_auth_method
	private Set<String> scope = new HashSet<String>(); // scope
	private Set<String> grantTypes = new HashSet<String>(); // grant_types
	private Set<String> responseTypes = new HashSet<String>(); // response_types
	private String policyUri;
	private String jwksUri;

	/** Fields from OIDC Client Registration Specification **/
	private AppType applicationType; // application_type
	private String sectorIdentifierUri; // sector_identifier_uri
	private SubjectType subjectType; // subject_type

	private JWSAlgorithmEmbed requestObjectSigningAlg = null; // request_object_signing_alg

	private JWSAlgorithmEmbed userInfoSignedResponseAlg = null; // user_info_signed_response_alg
	private JWEAlgorithmEmbed userInfoEncryptedResponseAlg = null; // user_info_encrypted_response_alg
	private JWEEncryptionMethodEmbed userInfoEncryptedResponseEnc = null; // user_info_encrypted_response_enc

	private JWSAlgorithmEmbed idTokenSignedResponseAlg = null; // id_token_signed_response_alg
	private JWEAlgorithmEmbed idTokenEncryptedResponseAlg = null; // id_token_encrypted_response_alg
	private JWEEncryptionMethodEmbed idTokenEncryptedResponseEnc = null; // id_token_encrypted_response_enc

	private JWSAlgorithmEmbed tokenEndpointAuthSigningAlg = null; // token_endpoint_auth_signing_alg

	private Integer defaultMaxAge; // default_max_age
	private Boolean requireAuthTime; // require_auth_time
	private Set<String> defaultACRvalues; // default_acr_values

	private String initiateLoginUri; // initiate_login_uri
	private String postLogoutRedirectUri; // post_logout_redirect_uri

	private Set<String> requestUris; // request_uris

	/** Fields to support the ClientDetails interface **/
//	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private Integer accessTokenValiditySeconds = 0; // in seconds
	private Integer refreshTokenValiditySeconds = 0; // in seconds
	private Set<String> resourceIds = new HashSet<String>();
	private Map<String, Object> additionalInformation = new HashMap<String, Object>();

	/** Our own fields **/
	private String clientDescription = ""; // human-readable description
	private boolean reuseRefreshToken = true; // do we let someone reuse a refresh token?
	private boolean dynamicallyRegistered = false; // was this client dynamically registered?
	private boolean allowIntrospection = false; // do we let this client call the introspection endpoint?
	private Integer idTokenValiditySeconds; //timeout for id tokens
	private Date createdAt; // time the client was created

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Set<String> getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(Set<String> redirectUris) {
		this.redirectUris = redirectUris;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientUri() {
		return clientUri;
	}

	public void setClientUri(String clientUri) {
		this.clientUri = clientUri;
	}

	public String getLogoUri() {
		return logoUri;
	}

	public void setLogoUri(String logoUri) {
		this.logoUri = logoUri;
	}

	public Set<String> getContacts() {
		return contacts;
	}

	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}

	public String getTosUri() {
		return tosUri;
	}

	public void setTosUri(String tosUri) {
		this.tosUri = tosUri;
	}

	public AuthMethod getTokenEndpointAuthMethod() {
		return tokenEndpointAuthMethod;
	}

	public void setTokenEndpointAuthMethod(AuthMethod tokenEndpointAuthMethod) {
		this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public Set<String> getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(Set<String> grantTypes) {
		this.grantTypes = grantTypes;
	}

	public Set<String> getResponseTypes() {
		return responseTypes;
	}

	public void setResponseTypes(Set<String> responseTypes) {
		this.responseTypes = responseTypes;
	}

	public String getPolicyUri() {
		return policyUri;
	}

	public void setPolicyUri(String policyUri) {
		this.policyUri = policyUri;
	}

	public String getJwksUri() {
		return jwksUri;
	}

	public void setJwksUri(String jwksUri) {
		this.jwksUri = jwksUri;
	}

	public AppType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(AppType applicationType) {
		this.applicationType = applicationType;
	}

	public String getSectorIdentifierUri() {
		return sectorIdentifierUri;
	}

	public void setSectorIdentifierUri(String sectorIdentifierUri) {
		this.sectorIdentifierUri = sectorIdentifierUri;
	}

	public SubjectType getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}

	public JWSAlgorithmEmbed getRequestObjectSigningAlg() {
		return requestObjectSigningAlg;
	}

	public void setRequestObjectSigningAlg(JWSAlgorithm requestObjectSigningAlg) {
		this.requestObjectSigningAlg = new JWSAlgorithmEmbed(requestObjectSigningAlg);
	}

	public JWSAlgorithmEmbed getUserInfoSignedResponseAlg() {
		return userInfoSignedResponseAlg;
	}

	public void setUserInfoSignedResponseAlg(JWSAlgorithm userInfoSignedResponseAlg) {
		this.userInfoSignedResponseAlg = new JWSAlgorithmEmbed(userInfoSignedResponseAlg);
	}

	public JWEAlgorithmEmbed getUserInfoEncryptedResponseAlg() {
		return userInfoEncryptedResponseAlg;
	}

	public void setUserInfoEncryptedResponseAlg(JWEAlgorithm userInfoEncryptedResponseAlg) {
		this.userInfoEncryptedResponseAlg = new JWEAlgorithmEmbed(userInfoEncryptedResponseAlg);
	}

	public JWEEncryptionMethodEmbed getUserInfoEncryptedResponseEnc() {
		return userInfoEncryptedResponseEnc;
	}

	public void setUserInfoEncryptedResponseEnc(EncryptionMethod userInfoEncryptedResponseEnc) {
		this.userInfoEncryptedResponseEnc = new JWEEncryptionMethodEmbed(userInfoEncryptedResponseEnc);
	}

	public JWSAlgorithm getIdTokenSignedResponseAlg() {
		if (idTokenSignedResponseAlg != null) {
			return idTokenSignedResponseAlg.getAlgorithm();
		} else {
			return null;
		}
	}

	public void setIdTokenSignedResponseAlg(JWSAlgorithm idTokenSignedResponseAlg) {
		this.idTokenSignedResponseAlg = new JWSAlgorithmEmbed(idTokenSignedResponseAlg);
	}

	public JWEAlgorithmEmbed getIdTokenEncryptedResponseAlg() {
		return idTokenEncryptedResponseAlg;
	}

	public void setIdTokenEncryptedResponseAlg(JWEAlgorithm idTokenEncryptedResponseAlg) {
		this.idTokenEncryptedResponseAlg = new JWEAlgorithmEmbed(idTokenEncryptedResponseAlg);
	}

	public JWEEncryptionMethodEmbed getIdTokenEncryptedResponseEnc() {
		return idTokenEncryptedResponseEnc;
	}

	public void setIdTokenEncryptedResponseEnc(EncryptionMethod idTokenEncryptedResponseEnc) {
		this.idTokenEncryptedResponseEnc = new JWEEncryptionMethodEmbed(idTokenEncryptedResponseEnc);
	}

	public JWSAlgorithm getTokenEndpointAuthSigningAlg() {
		if (tokenEndpointAuthSigningAlg != null) {
			return tokenEndpointAuthSigningAlg.getAlgorithm();
		} else {
			return null;
		}
	}

	public void setTokenEndpointAuthSigningAlg(JWSAlgorithm tokenEndpointAuthSigningAlg) {
		this.tokenEndpointAuthSigningAlg = new JWSAlgorithmEmbed(tokenEndpointAuthSigningAlg);
	}

	public Integer getDefaultMaxAge() {
		return defaultMaxAge;
	}

	public void setDefaultMaxAge(Integer defaultMaxAge) {
		this.defaultMaxAge = defaultMaxAge;
	}

	public Boolean getRequireAuthTime() {
		return requireAuthTime;
	}

	public void setRequireAuthTime(Boolean requireAuthTime) {
		this.requireAuthTime = requireAuthTime;
	}

	public Set<String> getDefaultACRvalues() {
		return defaultACRvalues;
	}

	public void setDefaultACRvalues(Set<String> defaultACRvalues) {
		this.defaultACRvalues = defaultACRvalues;
	}

	public String getInitiateLoginUri() {
		return initiateLoginUri;
	}

	public void setInitiateLoginUri(String initiateLoginUri) {
		this.initiateLoginUri = initiateLoginUri;
	}

	public String getPostLogoutRedirectUri() {
		return postLogoutRedirectUri;
	}

	public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
		this.postLogoutRedirectUri = postLogoutRedirectUri;
	}

	public Set<String> getRequestUris() {
		return requestUris;
	}

	public void setRequestUris(Set<String> requestUris) {
		this.requestUris = requestUris;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getClientDescription() {
		return clientDescription;
	}

	public void setClientDescription(String clientDescription) {
		this.clientDescription = clientDescription;
	}

	public boolean isReuseRefreshToken() {
		return reuseRefreshToken;
	}

	public void setReuseRefreshToken(boolean reuseRefreshToken) {
		this.reuseRefreshToken = reuseRefreshToken;
	}

	public boolean isDynamicallyRegistered() {
		return dynamicallyRegistered;
	}

	public void setDynamicallyRegistered(boolean dynamicallyRegistered) {
		this.dynamicallyRegistered = dynamicallyRegistered;
	}

	public boolean isAllowIntrospection() {
		return allowIntrospection;
	}

	public void setAllowIntrospection(boolean allowIntrospection) {
		this.allowIntrospection = allowIntrospection;
	}

	public Integer getIdTokenValiditySeconds() {
		return idTokenValiditySeconds;
	}

	public void setIdTokenValiditySeconds(Integer idTokenValiditySeconds) {
		this.idTokenValiditySeconds = idTokenValiditySeconds;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Set<String> getRegisteredRedirectUri() {
		return getRedirectUris();
	}

	public void setRequestObjectSigningAlg(JWSAlgorithmEmbed requestObjectSigningAlg) {
		this.requestObjectSigningAlg = requestObjectSigningAlg;
	}

	public void setUserInfoSignedResponseAlg(JWSAlgorithmEmbed userInfoSignedResponseAlg) {
		this.userInfoSignedResponseAlg = userInfoSignedResponseAlg;
	}

	public void setUserInfoEncryptedResponseAlg(JWEAlgorithmEmbed userInfoEncryptedResponseAlg) {
		this.userInfoEncryptedResponseAlg = userInfoEncryptedResponseAlg;
	}

	public void setUserInfoEncryptedResponseEnc(JWEEncryptionMethodEmbed userInfoEncryptedResponseEnc) {
		this.userInfoEncryptedResponseEnc = userInfoEncryptedResponseEnc;
	}

	public void setIdTokenSignedResponseAlg(JWSAlgorithmEmbed idTokenSignedResponseAlg) {
		this.idTokenSignedResponseAlg = idTokenSignedResponseAlg;
	}

	public void setIdTokenEncryptedResponseAlg(JWEAlgorithmEmbed idTokenEncryptedResponseAlg) {
		this.idTokenEncryptedResponseAlg = idTokenEncryptedResponseAlg;
	}

	public void setIdTokenEncryptedResponseEnc(JWEEncryptionMethodEmbed idTokenEncryptedResponseEnc) {
		this.idTokenEncryptedResponseEnc = idTokenEncryptedResponseEnc;
	}

	public void setTokenEndpointAuthSigningAlg(JWSAlgorithmEmbed tokenEndpointAuthSigningAlg) {
		this.tokenEndpointAuthSigningAlg = tokenEndpointAuthSigningAlg;
	}
	

}
