package sib.calipho.spring.security.auth0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implementation of UserDetails in compliance with the decoded object returned by the Auth0 JWT 
 * 
 * @author Daniel Teixeira
 *
 */
public class Auth0UserDetails implements UserDetails {

	private static final long serialVersionUID = 2058797193125711681L;

	private Map<String, Object> details;
	private String username;
	private boolean emailVerified = false;
	private Collection<GrantedAuthority> authorities = null;
	
	protected final Log logger = LogFactory.getLog(getClass());

	public Auth0UserDetails(Map<String, Object> map) {

		if (map.containsKey("email")) {
			this.username = map.get("email").toString();
		}else if (map.containsKey("user_id")) {
			this.username = map.get("user_id").toString();
		}else if (map.containsKey("user_id")) {
			this.username = "UNKOWNUN_USER";
		}
		
		if (map.containsKey("email")) {
			this.emailVerified = Boolean.valueOf(map.get("email_verified").toString());
		}

		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		this.details = map;

	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Will return UnsupportedOperationException 
	 */
	public String getPassword() {
		throw new UnsupportedOperationException("Password is protected");
	}

	/**
	 * Gets the email if it exists otherwise it returns the user_id
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Will return false
	 */
	public boolean isAccountNonExpired() {
		return false;
	}

	/**
	 * Will return false
	 */
	public boolean isAccountNonLocked() {
		return false;
	}

	/**
	 * Will return false
	 */
	public boolean isCredentialsNonExpired() {
		return false;
	}

	/**
	 * Will return true if the email is verified, otherwise it will return false
	 */
	public boolean isEnabled() {
		return emailVerified;
	}

	/**
	 * Will return the details of the attribute of JWT decoded token if it exists or null otherwise.
	 * Example getAuth0Attribute("email"), getAuth0Attribute("picture")....
	 * 
	 * @return return the details of the JWT decoded token if it exists  or null otherwise
	 */
	public Object getAuth0Attribute(String attributeName) {
		return details.get(attributeName);
	}


}
