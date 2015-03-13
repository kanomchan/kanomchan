package org.kanomchan.core.openid.bean;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.kanomchan.core.openid.constant.AuthMethod;
import org.mitre.jose.JWEAlgorithmEmbed;
import org.mitre.jose.JWEEncryptionMethodEmbed;
import org.mitre.jose.JWSAlgorithmEmbed;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;

//import org.mitre.jose.JWEAlgorithmEmbed;
//import org.mitre.jose.JWEEncryptionMethodEmbed;
//import org.mitre.jose.JWSAlgorithmEmbed;
//import org.mitre.oauth2.model.ClientDetailsEntity.AppType;
//import org.mitre.oauth2.model.ClientDetailsEntity.AuthMethod;
//import org.mitre.oauth2.model.ClientDetailsEntity.SubjectType;

//import com.nimbusds.jose.EncryptionMethod;
//import com.nimbusds.jose.JWEAlgorithm;
//import com.nimbusds.jose.JWSAlgorithm;

public interface RegisteredClient {
	
//	private String registrationAccessToken;
//	private String registrationClientUri;
//	private Date clientSecretExpiresAt;
//	private Date clientIdIssuedAt;
	
	public String getRegistrationAccessToken();

	public void setRegistrationAccessToken(String registrationAccessToken);

	public String getRegistrationClientUri();

	public void setRegistrationClientUri(String registrationClientUri);

	public Date getClientSecretExpiresAt();

	public void setClientSecretExpiresAt(Date clientSecretExpiresAt);

	public Date getClientIdIssuedAt();

	public void setClientIdIssuedAt(Date clientIdIssuedAt);
	
	
//	private String clientId = null; // client_id
//	private String clientSecret = null; // client_secret
//	private Set<String> redirectUris = new HashSet<String>(); // redirect_uris
//	private String clientName; // client_name
//	private String clientUri; // client_uri
//	private String logoUri; // logo_uri
//	private Set<String> contacts; // contacts
//	private String tosUri; // tos_uri
//	private AuthMethod tokenEndpointAuthMethod = AuthMethod.SECRET_BASIC; // token_endpoint_auth_method
//	private Set<String> scope = new HashSet<String>(); // scope
//	private Set<String> grantTypes = new HashSet<String>(); // grant_types
//	private Set<String> responseTypes = new HashSet<String>(); // response_types
//	private String policyUri;
//	private String jwksUri;
//
//	/** Fields from OIDC Client Registration Specification **/
//	private AppType applicationType; // application_type
//	private String sectorIdentifierUri; // sector_identifier_uri
//	private SubjectType subjectType; // subject_type
//
//	private JWSAlgorithmEmbed requestObjectSigningAlg = null; // request_object_signing_alg
//
//	private JWSAlgorithmEmbed userInfoSignedResponseAlg = null; // user_info_signed_response_alg
//	private JWEAlgorithmEmbed userInfoEncryptedResponseAlg = null; // user_info_encrypted_response_alg
//	private JWEEncryptionMethodEmbed userInfoEncryptedResponseEnc = null; // user_info_encrypted_response_enc
//
//	private JWSAlgorithmEmbed idTokenSignedResponseAlg = null; // id_token_signed_response_alg
//	private JWEAlgorithmEmbed idTokenEncryptedResponseAlg = null; // id_token_encrypted_response_alg
//	private JWEEncryptionMethodEmbed idTokenEncryptedResponseEnc = null; // id_token_encrypted_response_enc
//
//	private JWSAlgorithmEmbed tokenEndpointAuthSigningAlg = null; // token_endpoint_auth_signing_alg
//
//	private Integer defaultMaxAge; // default_max_age
//	private Boolean requireAuthTime; // require_auth_time
//	private Set<String> defaultACRvalues; // default_acr_values
//
//	private String initiateLoginUri; // initiate_login_uri
//	private String postLogoutRedirectUri; // post_logout_redirect_uri
//
//	private Set<String> requestUris; // request_uris
//
//	/** Fields to support the ClientDetails interface **/
////	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//	private Integer accessTokenValiditySeconds = 0; // in seconds
//	private Integer refreshTokenValiditySeconds = 0; // in seconds
//	private Set<String> resourceIds = new HashSet<String>();
//	private Map<String, Object> additionalInformation = new HashMap<String, Object>();
//
//	/** Our own fields **/
//	private String clientDescription = ""; // human-readable description
//	private boolean reuseRefreshToken = true; // do we let someone reuse a refresh token?
//	private boolean dynamicallyRegistered = false; // was this client dynamically registered?
//	private boolean allowIntrospection = false; // do we let this client call the introspection endpoint?
//	private Integer idTokenValiditySeconds; //timeout for id tokens
//	private Date createdAt; // time the client was created

	public String getClientId();

	public void setClientId(String clientId);

	public String getClientSecret();

	public void setClientSecret(String clientSecret);

	public Set<String> getRedirectUris();

	public void setRedirectUris(Set<String> redirectUris);

	public String getClientName();

	public void setClientName(String clientName);

	public String getClientUri();

	public void setClientUri(String clientUri);

	public String getLogoUri();

	public void setLogoUri(String logoUri);

	public Set<String> getContacts();

	public void setContacts(Set<String> contacts);

	public String getTosUri();

	public void setTosUri(String tosUri);

	public AuthMethod getTokenEndpointAuthMethod();

	public void setTokenEndpointAuthMethod(AuthMethod tokenEndpointAuthMethod);

	public Set<String> getScope();

	public void setScope(Set<String> scope);

	public Set<String> getGrantTypes();

	public void setGrantTypes(Set<String> grantTypes);

	public Set<String> getResponseTypes();

	public void setResponseTypes(Set<String> responseTypes);

	public String getPolicyUri();

	public void setPolicyUri(String policyUri);

	public String getJwksUri();

	public void setJwksUri(String jwksUri);

//	public AppType getApplicationType();

//	public void setApplicationType(AppType applicationType);

	public String getSectorIdentifierUri();

	public void setSectorIdentifierUri(String sectorIdentifierUri);

//	public SubjectType getSubjectType();

//	public void setSubjectType(SubjectType subjectType);

	public JWSAlgorithmEmbed getRequestObjectSigningAlg();

	public void setRequestObjectSigningAlg(JWSAlgorithm requestObjectSigningAlg);

	public JWSAlgorithmEmbed getUserInfoSignedResponseAlg();

	public void setUserInfoSignedResponseAlg(JWSAlgorithm userInfoSignedResponseAlg);

	public JWEAlgorithmEmbed getUserInfoEncryptedResponseAlg();

	public void setUserInfoEncryptedResponseAlg(JWEAlgorithm userInfoEncryptedResponseAlg);

	public JWEEncryptionMethodEmbed getUserInfoEncryptedResponseEnc();

	public void setUserInfoEncryptedResponseEnc(EncryptionMethod userInfoEncryptedResponseEnc);

	public JWSAlgorithm getIdTokenSignedResponseAlg();

	public void setIdTokenSignedResponseAlg(JWSAlgorithm idTokenSignedResponseAlg);

	public JWEAlgorithmEmbed getIdTokenEncryptedResponseAlg();

	public void setIdTokenEncryptedResponseAlg(JWEAlgorithm idTokenEncryptedResponseAlg);

	public JWEEncryptionMethodEmbed getIdTokenEncryptedResponseEnc();

	public void setIdTokenEncryptedResponseEnc(EncryptionMethod idTokenEncryptedResponseEnc);

	public JWSAlgorithm getTokenEndpointAuthSigningAlg();

	public void setTokenEndpointAuthSigningAlg(JWSAlgorithm tokenEndpointAuthSigningAlg);

	public Integer getDefaultMaxAge();

	public void setDefaultMaxAge(Integer defaultMaxAge);

	public Boolean getRequireAuthTime();

	public void setRequireAuthTime(Boolean requireAuthTime);

	public Set<String> getDefaultACRvalues();

	public void setDefaultACRvalues(Set<String> defaultACRvalues);

	public String getInitiateLoginUri();

	public void setInitiateLoginUri(String initiateLoginUri);

	public String getPostLogoutRedirectUri();

	public void setPostLogoutRedirectUri(String postLogoutRedirectUri);

	public Set<String> getRequestUris();

	public void setRequestUris(Set<String> requestUris);

	public Integer getAccessTokenValiditySeconds();

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds);

	public Integer getRefreshTokenValiditySeconds();

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds);

	public Set<String> getResourceIds();

	public void setResourceIds(Set<String> resourceIds);

	public Map<String, Object> getAdditionalInformation();

	public void setAdditionalInformation(Map<String, Object> additionalInformation);

	public String getClientDescription();

	public void setClientDescription(String clientDescription);

	public boolean isReuseRefreshToken();

	public void setReuseRefreshToken(boolean reuseRefreshToken);

	public boolean isDynamicallyRegistered();

	public void setDynamicallyRegistered(boolean dynamicallyRegistered);

	public boolean isAllowIntrospection();

	public void setAllowIntrospection(boolean allowIntrospection);

	public Integer getIdTokenValiditySeconds();

	public void setIdTokenValiditySeconds(Integer idTokenValiditySeconds);

	public Date getCreatedAt();

	public void setCreatedAt(Date createdAt);
	
	public Set<String> getRegisteredRedirectUri();
	

}
