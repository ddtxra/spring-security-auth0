package sib.calipho.spring.security.auth0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Auth0UserDetails implements UserDetails {

	private static final long serialVersionUID = 2058797193125711681L;

	private String username;
	private boolean emailVerified;
	private Collection<GrantedAuthority> authorities = null;
	
	public Auth0UserDetails(Map<String, Object> map) {

		this.username = map.get("email").toString();
		if(username == null){
			this.username = map.get("user_id").toString();
		}
		this.emailVerified =  Boolean.valueOf(map.get("email_verified").toString());

		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER_ROLE"));

	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		throw new UnsupportedOperationException("Password is protected");
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return false;
	}

	public boolean isAccountNonLocked() {
		return false;
	}

	public boolean isCredentialsNonExpired() {
		return false;
	}

	public boolean isEnabled() {
		return emailVerified;
	}

}
