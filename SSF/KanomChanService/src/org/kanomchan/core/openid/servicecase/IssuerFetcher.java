package org.kanomchan.core.openid.servicecase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.cache.CacheLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IssuerFetcher extends CacheLoader<String, String> {
//	private HttpClient httpClient = new HttpClient();
//	private HttpClient httpClient = new SystemDefaultHttpClient();
//	private ClientHttpRequestFactory httpFactory = new CommonsClientHttpRequestFactory(httpClient);
	private HttpClient httpClient= HttpClientBuilder.create().build();
	private HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	
	private JsonParser parser = new JsonParser();
	
	private static final Pattern pattern = Pattern.compile("^" +
			"((https|acct|http|mailto|tel|device):(//)?)?" + // scheme
			"(" +
			"(([^@]+)@)?" + // userinfo
			"(([^\\?#:/]+)" + // host
			"(:(\\d*))?)" + // port
			")" +
			"([^\\?#]+)?" + // path
			"(\\?([^#]+))?" + // query
			"(#(.*))?" +  // fragment
			"$"
			);
	@Override
	public String load(String arg0) throws Exception {
			RestTemplate restTemplate = new RestTemplate(httpFactory);
			Matcher m = pattern.matcher(arg0);
			String scheme = null;
			String userInfo = null;
			String host = null;
			String port = null;
			String path = null;
			String query = null;
			String fragment = null;
			if (m.matches()) {
				 scheme = m.group(2);
				 userInfo = m.group(6);
				 host = m.group(8);
				 port = m.group(10);
				 path = m.group(11);
				 query = m.group(13);
				 fragment = m.group(15);
//				builder.scheme(m.group(2));
//				builder.userInfo(m.group(6));
//				builder.host(m.group(8));
//				String port = m.group(10);
//				if (!Strings.isNullOrEmpty(port)) {
//					builder.port(Integer.parseInt(port));
//				}
//				builder.path(m.group(11));
//				builder.query(m.group(13));
//				builder.fragment(m.group(15)); // we throw away the hash, but this is the group it would be if we kept it
			} else {
				// doesn't match the pattern, throw it out
//				logger.warn("Parser couldn't match input: " + identifier);
				return null;
			}
//			String scheme = m.group(2);
//			String userInfo = m.group(6);
//			String host = m.group(8);
//			String port = m.group(10);
//			String path = m.group(11);
//			String query = m.group(13);
//			String fragment = m.group(15);
			
			
			// construct the URL to go to
//			UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
			// preserving http scheme is strictly for demo system use only.
//			String scheme = key.getScheme();
			if (!Strings.isNullOrEmpty(scheme) && scheme.equals("http")) {
				scheme = "http://"; // add on colon and slashes.
//				logger.warn("Webfinger endpoint MUST use the https URI scheme.");
			} else {
				scheme = "https://";
			}
	
			// do a webfinger lookup
			URIBuilder builder = new URIBuilder(scheme
					+ host
					+ (port != null&&!"".equals(port) ? ":" + port : "")
					+ Strings.nullToEmpty(path)
					+ "/.well-known/webfinger"
					+ (Strings.isNullOrEmpty(query) ? "" : "?" + query)
					);
			builder.addParameter("resource", arg0);
			builder.addParameter("rel", "http://openid.net/specs/connect/1.0/issuer");
	
			// do the fetch
//			logger.info("Loading: " + builder.toString());
			String webfingerResponse = restTemplate.getForObject(builder.build(), String.class);
	
			// TODO: catch and handle HTTP errors
	
			JsonElement json = parser.parse(webfingerResponse);
	
			// TODO: catch and handle JSON errors
	
			if (json != null && json.isJsonObject()) {
				// find the issuer
				JsonArray links = json.getAsJsonObject().get("links").getAsJsonArray();
				for (JsonElement link : links) {
					if (link.isJsonObject()) {
						JsonObject linkObj = link.getAsJsonObject();
						if (linkObj.has("href")
								&& linkObj.has("rel")
								&& linkObj.get("rel").getAsString().equals("http://openid.net/specs/connect/1.0/issuer")) {
	
							// we found the issuer, return it
							return linkObj.get("href").getAsString();
						}
					}
				}
			}
	
			// we couldn't find it
	
			if (scheme.equals("http") || scheme.equals("https")) {
				// if it looks like HTTP then punt and return the input
//				logger.warn("Returning normalized input string as issuer, hoping for the best: " + key.toString());
				return arg0;
			} else {
				// if it's not HTTP, give up
//				logger.warn("Couldn't find issuer: " + key.toString());
				return null;
			}
	
		}

}

