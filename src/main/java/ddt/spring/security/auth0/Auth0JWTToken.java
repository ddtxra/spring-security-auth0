package ddt.spring.security.auth0;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class Auth0JWTToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 2371882820082543721L;
	private final String jwt;
	private Auth0UserDetails principal;

	public Auth0JWTToken(String jwt) {
		super(null);
		this.jwt = jwt;
		setAuthenticated(false);
	}

	public String getJwt() {
		return jwt;
	}

	public Object getCredentials() {
		return null;
	}

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipal(Auth0UserDetails principal) {
		this.principal = principal;
	}

}
