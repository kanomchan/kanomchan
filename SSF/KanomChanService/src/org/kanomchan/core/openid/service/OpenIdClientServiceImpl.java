package org.kanomchan.core.openid.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.bean.UserBeanDefault;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.NonRollBackProcessException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.openid.bean.AuthRequestBean;
import org.kanomchan.core.openid.bean.RegisteredClient;
import org.kanomchan.core.openid.constant.AuthMethod;
import org.kanomchan.core.openid.constant.OIConstant;
import org.kanomchan.core.openid.constant.OIMessageCode;
import org.kanomchan.core.openid.servicecase.DynamicClientRegistrationLoader;
import org.kanomchan.core.openid.servicecase.IssuerFetcher;
import org.kanomchan.core.openid.servicecase.OpenIDConnectServiceConfigurationFetcher;
import org.mitre.jwt.signer.service.JwtSigningAndValidationService;
import org.mitre.openid.connect.client.model.IssuerServiceResponse;
import org.mitre.openid.connect.config.ServerConfiguration;
import org.mitre.openid.connect.config.ServerConfiguration.UserInfoTokenMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class OpenIdClientServiceImpl implements OpenIdClientService {
	protected final static String REDIRECT_URI_SESION_VARIABLE = "redirect_uri";
	protected final static String STATE_SESSION_VARIABLE = "state";
	protected final static String NONCE_SESSION_VARIABLE = "nonce";
	protected final static String ISSUER_SESSION_VARIABLE = "issuer";
	protected static final String TARGET_SESSION_VARIABLE = "target";
	protected final static int HTTP_SOCKET_TIMEOUT = 30000;

	protected final static String FILTER_PROCESSES_URL = "/openid_connect_login";
	private static Logger logger = LoggerFactory.getLogger(OpenIdClientServiceImpl.class);
	
	
	private JWKSetCacheService  jwkSetCacheService;
	@Autowired
	public void setJwkSetCacheService(JWKSetCacheService jwkSetCacheService) {
		this.jwkSetCacheService = jwkSetCacheService;
	}
	
	
	private SymmetricCacheService symmetricCacheService;
	@Autowired(required=false)
	public void setSymmetricCacheService(SymmetricCacheService symmetricCacheService) {
		this.symmetricCacheService = symmetricCacheService;
	}

	
	private JwtSigningAndValidationService authenticationSignerService;
	@Autowired(required=false)
	public void setAuthenticationSignerService(JwtSigningAndValidationService authenticationSignerService) {
		this.authenticationSignerService = authenticationSignerService;
	}
	
	private AuthRequestUrlBuilder authRequestUrlBuilder;
	@Autowired
	@Required
	public void setAuthRequestUrlBuilder(AuthRequestUrlBuilder authRequestUrlBuilder) {
		this.authRequestUrlBuilder = authRequestUrlBuilder;
	}
	private ConfigService configService;
	@Autowired
	@Required
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	private OpenIdClientService openIdClientService;
	private LoadingCache<String, String> issuers;
	private LoadingCache<String, ServerConfiguration> servers;
	private LoadingCache<ServerConfiguration, RegisteredClient> clients;
	public OpenIdClientServiceImpl() {
		issuers = CacheBuilder.newBuilder().build(new IssuerFetcher());
		servers = CacheBuilder.newBuilder().build(new OpenIDConnectServiceConfigurationFetcher());
		Set<String> scopes = new HashSet<String>();
		scopes.add("openid");
		scopes.add("email");
		scopes.add("address");
		scopes.add("profile");
		scopes.add("phone");
		Set<String> redirectUris = new HashSet<String>();
		redirectUris.add("http://localhost:8080/KanomChanWeb/main/login-loginOpenId.htm");
		clients = CacheBuilder.newBuilder().build(new DynamicClientRegistrationLoader(scopes,redirectUris));
		
	}
	private RegisteredClient template;
	protected int httpSocketTimeout = HTTP_SOCKET_TIMEOUT;
	// Allow for time sync issues by having a window of X seconds.
	private int timeSkewAllowance = 300;
	
	private Set<String> whitelist = new HashSet<String>();
	private Set<String> blacklist = new HashSet<String>();
	
	
	public void init() {
		configService.get(OIConstant.CONFIG.WHITE_LIST_KEY);
		configService.get(OIConstant.CONFIG.BACK_LIST_KEY);
		
		//TODO add config white and back 
	}
	
	@Override
	public ServiceResult<UserBean> loadUserBean(String subject, String issuer, ServerConfiguration serverConfiguration, String idTokenValue, String accessTokenValue, String refreshTokenValue) throws RollBackException, NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult<AuthRequestBean> handleAuthorizationRequest(String redirectUri, String identifier) throws RollBackException, NonRollBackException {
		AuthRequestBean authRequestBean = new AuthRequestBean();
		Map<String, Object> session = new HashMap<String, Object>();
		String issuerss = null;
		try {
			issuerss = issuers.get(identifier);
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			issuerss = identifier;
		}
		if (issuerss == null) {
			logger.error("Null issuer response returned from service.");
			throw new RollBackProcessException(OIMessageCode.OIC3003,"No issuer found.");
		}
		IssuerServiceResponse issResp = new IssuerServiceResponse(issuerss,null,null);
		if (issResp.shouldRedirect()) {
			authRequestBean.sendRedirect(issResp.getRedirectUrl());
		} else {
			String issuer = issResp.getIssuer();

			if (!Strings.isNullOrEmpty(issResp.getTargetLinkUri())) {
				// there's a target URL in the response, we should save this so we can forward to it later
				session.put(TARGET_SESSION_VARIABLE, issResp.getTargetLinkUri());
			}

			if (Strings.isNullOrEmpty(issuer)) {
				logger.error("No issuer found: " + issuer);
				throw new RollBackProcessException(OIMessageCode.OIC3003,"No issuer found: " + issuer);
			}

			ServerConfiguration serverConfig;
			try {
				serverConfig = servers.get(issuer);
			} catch (ExecutionException e) {
				throw new RollBackProcessException(OIMessageCode.OIC4999,e);
			}
			if (serverConfig == null) {
				logger.error("No server configuration found for issuer: " + issuer);
				throw new RollBackProcessException(OIMessageCode.OIC3004,"No server configuration found for issuer: " + issuer);
			}


			session.put(ISSUER_SESSION_VARIABLE, serverConfig.getIssuer());
//
//			String clientId = configService.get(OIConstant.CONFIG.CLIENT_ID_KEY);
//			String scopeStr = configService.get(OIConstant.CONFIG.SCOPE_KEY);
//			Gson gson = new Gson();
//			Set<String> scope = gson.fromJson(scopeStr, new TypeToken<Set<String>>(){}.getType());
//			if(scope==null)
//				scope = new HashSet<String>();
			
			RegisteredClient clientConfig;
			Map<String, String> options = new HashMap<String, String>();
			try {
				clientConfig = clients.get(serverConfig);
			} catch (ExecutionException e) {
				throw new RollBackProcessException();
			}
			if (clientConfig == null) {
				logger.error("No client configuration found for issuer: " + issuer);
				throw new RollBackProcessException(OIMessageCode.OIC3005,"No client configuration found for issuer: " + issuer);
			}

//			String redirectUri = null;
			if (clientConfig.getRegisteredRedirectUri() != null && clientConfig.getRegisteredRedirectUri().size() == 1) {
				// if there's a redirect uri configured (and only one), use that
				redirectUri = clientConfig.getRegisteredRedirectUri().toArray(new String[] {})[0];
			} 
			session.put(REDIRECT_URI_SESION_VARIABLE, redirectUri);

			// this value comes back in the id token and is checked there
			String nonce = createNonce(session);

			// this value comes back in the auth code response
			String state = createState(session);
			
			String authRequest = authRequestUrlBuilder.buildAuthRequestUrl(serverConfig, clientConfig.getClientId(),clientConfig.getScope(), redirectUri, nonce, state, options);

			logger.debug("Auth Request:  " + authRequest);

			authRequestBean.sendRedirect(authRequest);
			authRequestBean.setNonce(nonce);
			authRequestBean.setState(state);
			authRequestBean.setSession(session);
		}
		return new ServiceResult<AuthRequestBean>(authRequestBean );
	}
//	@Override
//	public ServiceResult<IssuerServiceResponse> getIssuer(String identifier) throws RollBackException, NonRollBackException {
//		IssuerServiceResponse issuerServiceResponse = null;
//		if (!Strings.isNullOrEmpty(identifier)) {
//			try {
//				String issuer = issuers.get(identifier);
//				if (!whitelist.isEmpty() && !whitelist.contains(issuer)) {
//					throw new RollBackProcessException(OIMessageCode.OIC3001,"Whitelist was nonempty, issuer was not in whitelist: " + issuer);
//				}
//
//				if (blacklist.contains(issuer)) {
//					throw new RollBackProcessException(OIMessageCode.OIC3002,"Issuer was in blacklist: " + issuer);
//				}
//
//				issuerServiceResponse =  new IssuerServiceResponse(issuer, null, null);
//			} catch (UncheckedExecutionException ue) {
//				logger.warn("Issue fetching issuer for user input: " + identifier, ue);
//				return null;
//			} catch (ExecutionException e) {
//				logger.warn("Issue fetching issuer for user input: " + identifier, e);
//				return null;
//			}
//
//		} else {
//			logger.warn("No user input given, directing to login page: " + identifier);
//			issuerServiceResponse = new IssuerServiceResponse(identifier);
//		}
//		return new ServiceResult<IssuerServiceResponse>(issuerServiceResponse);
//	}
	
	protected static String createNonce(Map<String, Object> session) {
		String nonce = new BigInteger(50, new SecureRandom()).toString(16);
		session.put(NONCE_SESSION_VARIABLE, nonce);

		return nonce;
	}

	/**
	 * Get the nonce we stored in the session
	 * @param session
	 * @return
	 */
	protected static String getStoredNonce(Map<String, Object> session) {
		return getStoredSessionString(session, NONCE_SESSION_VARIABLE);
	}

	/**
	 * Create a cryptographically random state and store it in the session
	 * @param session
	 * @return
	 */
	protected static String createState(Map<String, Object> session) {
		String state = new BigInteger(50, new SecureRandom()).toString(16);
		session.put(STATE_SESSION_VARIABLE, state);

		return state;
	}

	/**
	 * Get the state we stored in the session
	 * @param session
	 * @return
	 */
	protected static String getStoredState(Map<String, Object> session) {
		return getStoredSessionString(session, STATE_SESSION_VARIABLE);
	}
	private static String getStoredSessionString(Map<String, Object> session, String key) {
		Object o = session.get(key);
		if (o != null && o instanceof String) {
			return o.toString();
		} else {
			return null;
		}
	}

	public RegisteredClient getTemplate() {
		return template;
	}

	public void setTemplate(RegisteredClient template) {
		this.template = template;
	}

	@Override
	public ServiceResult<UserBean> handleAuthorizationCodeResponse(String authorizationCode,String state,Map<String, Object> session) throws RollBackException, NonRollBackException {

//		String authorizationCode = request.getParameter("code");

//		HttpSession session = request.getSession();

		// check for state, if it doesn't match we bail early
		String storedState = getStoredState(session);
		if (!Strings.isNullOrEmpty(storedState)) {
//			String state = request.getParameter("state");
			if (!storedState.equals(state)) {
				throw new NonRollBackProcessException(OIMessageCode.OIC3006,"State parameter mismatch on return. Expected " + storedState + " got " + state);
			}
		}

		// look up the issuer that we set out to talk to
		String issuer = getStoredSessionString(session, ISSUER_SESSION_VARIABLE);

		// pull the configurations based on that issuer
		ServerConfiguration serverConfig = null;
		try {
			serverConfig = servers.get(issuer);
		} catch (ExecutionException e1) {
			throw new NonRollBackProcessException(OIMessageCode.OIC4998,"Issuer get serverConfig");
		}
		if(serverConfig == null){
			throw new NonRollBackProcessException(OIMessageCode.OIC4998,"Issuer get serverConfig");
		}
		final RegisteredClient clientConfig;
		try {
			clientConfig = clients.get(serverConfig);
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			throw new NonRollBackProcessException(OIMessageCode.OIC4997,"ServerConfig get ClientConfig");
		}
//		if(clientConfig==null ){
//			new NonRollBackProcessException(OIMessageCode.OIC4997,"ServerConfig get ClientConfig");
//		}

		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("grant_type", "authorization_code");
		form.add("code", authorizationCode);

		String redirectUri = getStoredSessionString(session, REDIRECT_URI_SESION_VARIABLE);
		if (redirectUri != null) {
			form.add("redirect_uri", redirectUri);
		}

		// Handle Token Endpoint interaction
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//		httpClientBuilder.set
		HttpClient httpClient= httpClientBuilder.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

//		HttpClient httpClient = new HttpClient();
//
//		httpClient.getParams().setParameter("http.socket.timeout", new Integer(httpSocketTimeout));
//
//		ClientHttpRequestFactory factory = new CommonsClientHttpRequestFactory(httpClient);

		RestTemplate restTemplate;

		if (AuthMethod.SECRET_BASIC.equals(clientConfig.getTokenEndpointAuthMethod())){
			// use BASIC auth if configured to do so
			restTemplate = new RestTemplate(factory) {
				
				@Override
				protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
					ClientHttpRequest httpRequest = super.createRequest(url, method);
					httpRequest.getHeaders().add("Authorization",
							String.format("Basic %s", Base64.encode(String.format("%s:%s", clientConfig.getClientId(), clientConfig.getClientSecret())) ));
					return httpRequest;
				}

			};
		} else {
			// we're not doing basic auth, figure out what other flavor we have
			restTemplate = new RestTemplate(factory);

			if (AuthMethod.SECRET_JWT.equals(clientConfig.getTokenEndpointAuthMethod()) || AuthMethod.PRIVATE_KEY.equals(clientConfig.getTokenEndpointAuthMethod())) {
				// do a symmetric secret signed JWT for auth


				JwtSigningAndValidationService signer = null;
				JWSAlgorithm alg = clientConfig.getTokenEndpointAuthSigningAlg();

				if (AuthMethod.SECRET_JWT.equals(clientConfig.getTokenEndpointAuthMethod()) &&
						(alg.equals(JWSAlgorithm.HS256)
								|| alg.equals(JWSAlgorithm.HS384)
								|| alg.equals(JWSAlgorithm.HS512))) {

					// generate one based on client secret
					signer = symmetricCacheService.getSymmetricValidtor(clientConfig.getClientSecret());

				} else if (AuthMethod.PRIVATE_KEY.equals(clientConfig.getTokenEndpointAuthMethod())) {

					// needs to be wired in to the bean
					signer = authenticationSignerService;
					
					if (alg == null) {
						alg = authenticationSignerService.getDefaultSigningAlgorithm();
					}
				}

				if (signer == null) {
					throw new NonRollBackProcessException(OIMessageCode.OIC3007,"Couldn't find required signer service for use with private key auth.");
				}

				JWTClaimsSet claimsSet = new JWTClaimsSet();

				claimsSet.setIssuer(clientConfig.getClientId());
				claimsSet.setSubject(clientConfig.getClientId());
				claimsSet.setAudience(Lists.newArrayList(serverConfig.getTokenEndpointUri()));

				// TODO: make this configurable
				Date exp = new Date(System.currentTimeMillis() + (60 * 1000)); // auth good for 60 seconds
				claimsSet.setExpirationTime(exp);

				Date now = new Date(System.currentTimeMillis());
				claimsSet.setIssueTime(now);
				claimsSet.setNotBeforeTime(now);

				SignedJWT jwt = new SignedJWT(new JWSHeader(alg), claimsSet);

				signer.signJwt(jwt, alg);

				form.add("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
				form.add("client_assertion", jwt.serialize());
			} else {
				//Alternatively use form based auth
				form.add("client_id", clientConfig.getClientId());
				form.add("client_secret", clientConfig.getClientSecret());
			}

		}

		logger.debug("tokenEndpointURI = " + serverConfig.getTokenEndpointUri());
		logger.debug("form = " + form);

		String jsonString = null;

		try {
			jsonString = restTemplate.postForObject(serverConfig.getTokenEndpointUri(), form, String.class);
		} catch (HttpClientErrorException httpClientErrorException) {

			// Handle error

			logger.error("Token Endpoint error response:  "
					+ httpClientErrorException.getStatusText() + " : "
					+ httpClientErrorException.getMessage());

			throw new NonRollBackProcessException(OIMessageCode.OIC3008,"Unable to obtain Access Token: " + httpClientErrorException.getMessage());
		}

		logger.debug("from TokenEndpoint jsonString = " + jsonString);

		JsonElement jsonRoot = new JsonParser().parse(jsonString);
		if (!jsonRoot.isJsonObject()) {
			throw new NonRollBackProcessException(OIMessageCode.OIC3009,"Token Endpoint did not return a JSON object: " + jsonRoot);
		}

		JsonObject tokenResponse = jsonRoot.getAsJsonObject();

		if (tokenResponse.get("error") != null) {

			// Handle error

			String error = tokenResponse.get("error").getAsString();

			logger.error("Token Endpoint returned: " + error);

			throw new NonRollBackProcessException(OIMessageCode.OIC3010,"Unable to obtain Access Token.  Token Endpoint returned: " + error);

		} else {

			// Extract the id_token to insert into the
			// OIDCAuthenticationToken

			// get out all the token strings
			String accessTokenValue = null;
			String idTokenValue = null;
			String refreshTokenValue = null;

			if (tokenResponse.has("access_token")) {
				accessTokenValue = tokenResponse.get("access_token").getAsString();
			} else {
				throw new NonRollBackProcessException(OIMessageCode.OIC3011,"Token Endpoint did not return an access_token: " + jsonString);
			}

			if (tokenResponse.has("id_token")) {
				idTokenValue = tokenResponse.get("id_token").getAsString();
			} else {
				logger.error("Token Endpoint did not return an id_token");
				throw new NonRollBackProcessException(OIMessageCode.OIC3012,"Token Endpoint did not return an id_token");
			}

			if (tokenResponse.has("refresh_token")) {
				refreshTokenValue = tokenResponse.get("refresh_token").getAsString();
			}

//			try {
//				JWT idToken = JWTParser.parse(idTokenValue);
//
//				// validate our ID Token over a number of tests
//				ReadOnlyJWTClaimsSet idClaims = idToken.getJWTClaimsSet();
//
//				// check the signature
//				JwtSigningAndValidationService jwtValidator = null;
//
//				Algorithm tokenAlg = idToken.getHeader().getAlgorithm();
//				
//				Algorithm clientAlg = clientConfig.getIdTokenSignedResponseAlg();
//				
//				if (clientAlg != null) {
//					if (!clientAlg.equals(tokenAlg)) {
//						throw new NonRollBackProcessException(OIMessageCode.OIC3013,"Token algorithm " + tokenAlg + " does not match expected algorithm " + clientAlg);
//					}
//				}
//				
//				if (idToken instanceof PlainJWT) {
//					
//					if (clientAlg == null) {
//						throw new NonRollBackProcessException(OIMessageCode.OIC3014,"Unsigned ID tokens can only be used if explicitly configured in client.");
//					}
//					
//					if (tokenAlg != null && !tokenAlg.equals(JWSAlgorithm.NONE)) {
//						throw new NonRollBackProcessException(OIMessageCode.OIC3015,"Unsigned token received, expected signature with " + tokenAlg);
//					}
//				} else if (idToken instanceof SignedJWT) {
//				
//					SignedJWT signedIdToken = (SignedJWT)idToken;
//					
//					if (tokenAlg.equals(JWSAlgorithm.HS256)
//						|| tokenAlg.equals(JWSAlgorithm.HS384)
//						|| tokenAlg.equals(JWSAlgorithm.HS512)) {
//						
//						// generate one based on client secret
//						jwtValidator = symmetricCacheService.getSymmetricValidtor(clientConfig.getClientSecret());
//					} else {
//						// otherwise load from the server's public key
//						jwtValidator = jwkSetCacheService.getValidator(serverConfig.getJwksUri());
//					}
//					
//					if (jwtValidator != null) {
//						if(!jwtValidator.validateSignature(signedIdToken)) {
//							throw new NonRollBackProcessException(OIMessageCode.OIC3016,"Signature validation failed");
//						}
//					} else {
//						logger.error("No validation service found. Skipping signature validation");
//						throw new NonRollBackProcessException(OIMessageCode.OIC3017,"Unable to find an appropriate signature validator for ID Token.");
//					}
//				} // TODO: encrypted id tokens
//
//				// check the issuer
//				if (idClaims.getIssuer() == null) {
//					throw new NonRollBackProcessException(OIMessageCode.OIC3018,"Id Token Issuer is null");
//				} else if (!idClaims.getIssuer().equals(serverConfig.getIssuer())){
//					throw new NonRollBackProcessException(OIMessageCode.OIC3019,"Issuers do not match, expected " + serverConfig.getIssuer() + " got " + idClaims.getIssuer());
//				}
//
//				// check expiration
//				if (idClaims.getExpirationTime() == null) {
//					throw new NonRollBackProcessException(OIMessageCode.OIC3020,"Id Token does not have required expiration claim");
//				} else {
//					// it's not null, see if it's expired
//					Date now = new Date(System.currentTimeMillis() - (timeSkewAllowance * 1000));
//					if (now.after(idClaims.getExpirationTime())) {
//						throw new NonRollBackProcessException(OIMessageCode.OIC3021,"Id Token is expired: " + idClaims.getExpirationTime());
//					}
//				}
//
//				// check not before
//				if (idClaims.getNotBeforeTime() != null) {
//					Date now = new Date(System.currentTimeMillis() + (timeSkewAllowance * 1000));
//					if (now.before(idClaims.getNotBeforeTime())){
//						throw new NonRollBackProcessException(OIMessageCode.OIC3022,"Id Token not valid untill: " + idClaims.getNotBeforeTime());
//					}
//				}
//
//				// check issued at
//				if (idClaims.getIssueTime() == null) {
//					throw new NonRollBackProcessException(OIMessageCode.OIC3023,"Id Token does not have required issued-at claim");
//				} else {
//					// since it's not null, see if it was issued in the future
//					Date now = new Date(System.currentTimeMillis() + (timeSkewAllowance * 1000));
//					if (now.before(idClaims.getIssueTime())) {
//						throw new NonRollBackProcessException(OIMessageCode.OIC3024,"Id Token was issued in the future: " + idClaims.getIssueTime());
//					}
//				}
//
//				// check audience
//				if (idClaims.getAudience() == null) {
//					throw new NonRollBackProcessException(OIMessageCode.OIC3025,"Id token audience is null");
//				} else if (!idClaims.getAudience().contains(clientConfig.getClientId())) {
//					throw new NonRollBackProcessException(OIMessageCode.OIC3026,"Audience does not match, expected " + clientConfig.getClientId() + " got " + idClaims.getAudience());
//				}
//
//				// compare the nonce to our stored claim
//				String nonce = idClaims.getStringClaim("nonce");
//				if (Strings.isNullOrEmpty(nonce)) {
//
//					logger.error("ID token did not contain a nonce claim.");
//
//					throw new NonRollBackProcessException(OIMessageCode.OIC3027,"ID token did not contain a nonce claim.");
//				}
//
//				String storedNonce = getStoredNonce(session);
//				if (!nonce.equals(storedNonce)) {
//					logger.error("Possible replay attack detected! The comparison of the nonce in the returned "
//							+ "ID Token to the session " + NONCE_SESSION_VARIABLE + " failed. Expected " + storedNonce + " got " + nonce + ".");
//
//					throw new NonRollBackProcessException(OIMessageCode.OIC3028,
//							"Possible replay attack detected! The comparison of the nonce in the returned "
//									+ "ID Token to the session " + NONCE_SESSION_VARIABLE + " failed. Expected " + storedNonce + " got " + nonce + ".");
//				}
//
//				// pull the subject (user id) out as a claim on the id_token
//
//				String userId = idClaims.getSubject();
//
//				// construct an OIDCAuthenticationToken and return a Authentication object w/the userId and the idToken
//
////				OIDCAuthenticationToken token = new OIDCAuthenticationToken(userId, idClaims.getIssuer(), serverConfig, idTokenValue, accessTokenValue, refreshTokenValue);
////
////				Authentication authentication = this.getAuthenticationManager().authenticate(token);
//				
////				UserInfo userInfo = userInfoFetcher.loadUserInfo(token);
				UserBean userBean = loadUser(accessTokenValue,serverConfig);
				return new ServiceResult<UserBean>(userBean);
//			} catch (ParseException e) {
//				throw new NonRollBackProcessException(OIMessageCode.OIC3029,"Couldn't parse idToken: ", e);
//			}

		}
	}
	

	private UserBean loadUser(final String accessTokenValue,ServerConfiguration serverConfiguration){

//		ServerConfiguration serverConfiguration = token.getServerConfiguration();

		if (serverConfiguration == null) {
			logger.warn("No server configuration found.");
			return null;
		}

		if (Strings.isNullOrEmpty(serverConfiguration.getUserInfoUri())) {
			logger.warn("No userinfo endpoint, not fetching.");
			return null;
		}

		try {
		
			// if we got this far, try to actually get the userinfo
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//			httpClientBuilder.set
			HttpClient httpClient= httpClientBuilder.build();
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

//			HttpClient httpClient = new HttpClient();
//
//			httpClient.getParams().setParameter("http.socket.timeout", new Integer(httpSocketTimeout));
//
//			ClientHttpRequestFactory factory = new CommonsClientHttpRequestFactory(httpClient);

//			RestTemplate restTemplate;
			
			String userInfoString = null;
			
			if (serverConfiguration.getUserInfoTokenMethod() == null || serverConfiguration.getUserInfoTokenMethod().equals(UserInfoTokenMethod.HEADER)) {
				RestTemplate restTemplate = new RestTemplate(factory) {
					
					@Override
					protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
						ClientHttpRequest httpRequest = super.createRequest(url, method);
						httpRequest.getHeaders().add("Authorization", String.format("Bearer %s", accessTokenValue));
						return httpRequest;
					}
				};
				
				userInfoString = restTemplate.getForObject(serverConfiguration.getUserInfoUri(), String.class);
				
			} else if (serverConfiguration.getUserInfoTokenMethod().equals(UserInfoTokenMethod.FORM)) {
				MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
				form.add("access_token", accessTokenValue);
				
				RestTemplate restTemplate = new RestTemplate(factory);
				userInfoString = restTemplate.postForObject(serverConfiguration.getUserInfoUri(), form, String.class);
			} else if (serverConfiguration.getUserInfoTokenMethod().equals(UserInfoTokenMethod.QUERY)) {
				URIBuilder builder = new URIBuilder(serverConfiguration.getUserInfoUri());
				builder.setParameter("access_token",  accessTokenValue);
				
				RestTemplate restTemplate = new RestTemplate(factory);
				userInfoString = restTemplate.getForObject(builder.toString(), String.class);
			}


			if (!Strings.isNullOrEmpty(userInfoString)) {

				JsonObject obj = new JsonParser().parse(userInfoString).getAsJsonObject();
	
//				UserInfo userInfo = DefaultUserInfo.fromJson(userInfoJson);

				UserBeanDefault ui = new UserBeanDefault();
				
//				ui.setSub(obj.has("sub") ? obj.get("sub").getAsString() : null);

				ui.setFirstName(obj.has("name") ? obj.get("name").getAsString() : null);
//				ui.setPreferredUsername(obj.has("preferred_username") ? obj.get("preferred_username").getAsString() : null);
//				ui.setGivenName(obj.has("given_name") ? obj.get("given_name").getAsString() : null);
//				ui.setFamilyName(obj.has("family_name") ? obj.get("family_name").getAsString() : null);
//				ui.setMiddleName(obj.has("middle_name") ? obj.get("middle_name").getAsString() : null);
//				ui.setNickname(obj.has("nickname") ? obj.get("nickname").getAsString() : null);
//				ui.setProfile(obj.has("profile") ? obj.get("profile").getAsString() : null);
//				ui.setPicture(obj.has("picture") ? obj.get("picture").getAsString() : null);
//				ui.setWebsite(obj.has("website") ? obj.get("website").getAsString() : null);
//				ui.setGender(obj.has("gender") ? obj.get("gender").getAsString() : null);
//				ui.setZoneinfo(obj.has("zone_info") ? obj.get("zone_info").getAsString() : null);
//				ui.setLocale(obj.has("locale") ? obj.get("locale").getAsString() : null);
//				ui.setUpdatedTime(obj.has("updated_time") ? obj.get("updated_time").getAsString() : null);
//				ui.setBirthdate(obj.has("birthdate") ? obj.get("birthdate").getAsString() : null);

//				ui.setEmail(obj.has("email") ? obj.get("email").getAsString() : null);
//				ui.setEmailVerified(obj.has("email_verified") ? obj.get("email_verified").getAsBoolean() : null);

//				ui.setPhoneNumber(obj.has("phone_number") ? obj.get("phone_number").getAsString() : null);
//				ui.setPhoneNumberVerified(obj.has("phone_number_verified") ? obj.get("phone_number_verified").getAsBoolean() : null);

//				if (obj.has("address") && obj.get("address").isJsonObject()) {
//					JsonObject addr = obj.get("address").getAsJsonObject();
//					ui.setAddress(new Address());
//
//					ui.getAddress().setFormatted(addr.has("formatted") ? addr.get("formatted").getAsString() : null);
//					ui.getAddress().setStreetAddress(addr.has("street_address") ? addr.get("street_address").getAsString() : null);
//					ui.getAddress().setLocality(addr.has("locality") ? addr.get("locality").getAsString() : null);
//					ui.getAddress().setRegion(addr.has("region") ? addr.get("region").getAsString() : null);
//					ui.getAddress().setPostalCode(addr.has("postal_code") ? addr.get("postal_code").getAsString() : null);
//					ui.getAddress().setCountry(addr.has("country") ? addr.get("country").getAsString() : null);
//
//				}
				return ui;
			} else {
				// didn't get anything, return null
				return null;
			}
		} catch (Exception e) {
			logger.warn("Error fetching userinfo", e);
			return null;
		}

	}
	
}

	