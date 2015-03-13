package org.kanomchan.core.openid.constant;

import org.kanomchan.core.common.constant.MessageCode;

/**
 * 
 * Message Code
 * <p>
 * SUCCESS XXXYZZZ
 * </p>
 * <p>
 *  XXX = module  <br>
 *  Ex. :OIC = open id client<br>
 *  Y = Type Message<br>
 *  0 = SUCCESS<br>
 *  1 = INFO<br>
 *  2 = WARNING<br>
 *  3 = PRO_ERROR<br>
 *  4 = TECH_ERROR<br>
 *  ZZZ = Running number
 * </p>
 * 
 * @author kwan
 * 
 */
public enum OIMessageCode implements MessageCode {
	OIC0000("Transaction Complete."),
	OIC4999(""), 
	OIC4998("Issuer get serverConfig"), 
	OIC4997("ServerConfig get ClientConfig"), 
	OIC3001("Whitelist was nonempty, issuer was not in whitelist"),
	OIC3002("Issuer was in blacklist"), 
	OIC3003("No issuer found."), 
	OIC3004("No server configuration found for issuer"),
	OIC3005("No client configuration found for issuer"),
	OIC3006("State parameter mismatch on return."),
	OIC3007("Couldn't find required signer service for use with private key auth."), 
	OIC3008("Unable to obtain Access Token"), 
	OIC3009("Token Endpoint did not return a JSON object"), 
	OIC3010("Unable to obtain Access Token."), 
	OIC3011("Token Endpoint did not return an access_token"), 
	OIC3012("Token Endpoint did not return an id_token"), 
	OIC3013("Token algorithm XXX does not match expected algorithm XXXX"), 
	OIC3014("Unsigned ID tokens can only be used if explicitly configured in client."), 
	OIC3015("Unsigned token received, expected signature with "), 
	OIC3016("Signature validation failed"), 
	OIC3017("Unable to find an appropriate signature validator for ID Token."), 
	OIC3018("Id Token Issuer is null"), 
	OIC3019("Issuers do not match, expected XXX got XXX"), 
	OIC3020("Id Token does not have required expiration claim"), 
	OIC3021("Id Token is expired: XXXX"), 
	OIC3022("Id Token not valid untill: XXXX"), 
	OIC3023("Id Token does not have required issued-at claim"), 
	OIC3024("Id Token was issued in the future: XXX"), 
	OIC3025("Id token audience is null"), 
	OIC3026("Audience does not match, expected XXXX got XXX"), 
	OIC3027("ID token did not contain a nonce claim."), 
	OIC3028("Possible replay attack detected! The comparison of the nonce in the returned ID Token to the session nonce failed. Expected XXX got XX."),
	OIC3029("Couldn't parse idToken: ");
	
	
	private OIMessageCode(String dec){
		this.dec = dec;
	}
	private String dec;
	
	public String getCode() {
		return name();
	}
	
	@Override
	public String toString() {
		return "code : "+name()+" dec : "+dec;
	}
}
