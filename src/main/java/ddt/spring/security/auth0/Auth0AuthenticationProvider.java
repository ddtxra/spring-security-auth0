package ddt.spring.security.auth0;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.auth0.jwt.JWTVerifier;

public class Auth0AuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private JWTVerifier jwtVerifier = null;
	private String clientSecret = null;
	private String clientId = null;

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

		System.err.println("Trying to authenticate with token: " + token);

		try {

			Map<String, Object> decoded = jwtVerifier.verify(token);
			
			System.err.println("Details " + decoded);
			((Auth0JWTToken) authentication).setAuthenticated(true);
			((Auth0JWTToken) authentication).setPrincipal(new Auth0UserDetails(decoded));
			((Auth0JWTToken) authentication).setDetails(decoded);
			
			return authentication;

		} catch (Exception e) {
			new AuthenticationServiceException("Unauthorized: Token validation failed");
		}

		return null;
	}

	public boolean supports(Class<?> authentication) {
		return Auth0JWTToken.class.isAssignableFrom(authentication);
	}

	public void afterPropertiesSet() throws Exception {
		jwtVerifier = new JWTVerifier(clientSecret, clientId);
	}

}
