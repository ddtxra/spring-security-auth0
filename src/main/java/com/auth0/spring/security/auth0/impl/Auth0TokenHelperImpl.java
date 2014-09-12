package com.auth0.spring.security.auth0.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.ClaimSet;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JwtSigner;
import com.auth0.spring.security.auth0.Auth0TokenHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Auth0TokenHelperImpl implements Auth0TokenHelper<Object>, InitializingBean {
	
	private static final Log Logger = LogFactory.getLog(Auth0TokenHelperImpl.class);
	
	private String clientSecret = null;
	private String clientId = null;

	@Override
	public String generateToken(Object object, int expiration) {

		String payload, token;
		try {
		
			JwtSigner jwtSigner = new JwtSigner();
			payload = new  ObjectMapper().writeValueAsString(object);

		    ClaimSet claimSet = new ClaimSet();
		    claimSet.setExp(expiration); // expire in 1 year
		    
		    token = jwtSigner.encode(Algorithm.HS256, payload, "payload", new String(Base64.decodeBase64(clientSecret)), claimSet);
		
		} catch (JsonProcessingException e) {
			throw new Auth0RuntimeException(e);
		} catch (Exception e) {
			throw new Auth0RuntimeException(e);
		}
		
		return token;
		
	}

	@Override
	public Object decodeToken(String token) {

		JWTVerifier jwtVerifier = new JWTVerifier(clientSecret, clientId);

		
		Map<String, Object> verify;
		try {

			verify = jwtVerifier.verify(token);
			String payload = (String) verify.get("$");
			@SuppressWarnings("unchecked")
			Map<String, String> map = new ObjectMapper().readValue(payload, Map.class);
			return map;

		} catch (InvalidKeyException e) {
			throw new Auth0RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new Auth0RuntimeException(e);
		} catch (IllegalStateException e) {
			throw new Auth0RuntimeException(e);
		} catch (SignatureException e) {
			throw new Auth0RuntimeException(e);
		} catch (IOException e) {
			throw new Auth0RuntimeException(e);
		}
		
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(clientSecret, "The client secret is not set for " + this.getClass());
		Assert.notNull(clientId, "The client id is not set for " + this.getClass());

	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
