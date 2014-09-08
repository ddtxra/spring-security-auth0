package sib.calipho.spring.security.auth0;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.auth0.jwt.JWTVerifier;

/**
 * Class that verifies the JWT token and in case of beeing valid, it will set the userdetails in the authentication object
 * 
 * @author Daniel Teixeira
 */
public class Auth0AuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private JWTVerifier jwtVerifier = null;
	private String clientSecret = null;
	private String clientId = null;
	private final Log logger = LogFactory.getLog(getClass());
	private static final AuthenticationException AUTH_ERROR = new Auth0TokenException("Authentication error occured");

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

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String token = ((Auth0JWTToken) authentication).getJwt();

		logger.info("Trying to authenticate with token: " + token);

		Map<String, Object> decoded;
		try {

			decoded = jwtVerifier.verify(token);
			logger.debug("Decoded JWT token" + decoded);
			((Auth0JWTToken) authentication).setAuthenticated(true);
			((Auth0JWTToken) authentication).setPrincipal(new Auth0UserDetails(decoded));
			((Auth0JWTToken) authentication).setDetails(decoded);
			return authentication;

		} catch (InvalidKeyException e) {
			logger.debug("InvalidKeyException thrown while decoding JWT token " + e.getLocalizedMessage());
			throw AUTH_ERROR;
		} catch (NoSuchAlgorithmException e) {
			logger.debug("NoSuchAlgorithmException thrown while decoding JWT token " + e.getLocalizedMessage());
			throw AUTH_ERROR;
		} catch (IllegalStateException e) {
			logger.debug("IllegalStateException thrown while decoding JWT token " + e.getLocalizedMessage());
			throw AUTH_ERROR;
		} catch (SignatureException e) {
			logger.debug("SignatureException thrown while decoding JWT token " + e.getLocalizedMessage());
			throw AUTH_ERROR;
		} catch (IOException e) {
			logger.debug("IOException thrown while decoding JWT token " + e.getLocalizedMessage());
			throw AUTH_ERROR;
		}
	}

	public boolean supports(Class<?> authentication) {
		return Auth0JWTToken.class.isAssignableFrom(authentication);
	}

	public void afterPropertiesSet() throws Exception {
		if ((clientSecret == null) || (clientId == null)) {
			throw new RuntimeException("client secret and client id are not set for Auth0AuthenticationProvider");
		}
		jwtVerifier = new JWTVerifier(clientSecret, clientId);
	}

}
