package sib.calipho.spring.security.auth0;

public interface Auth0TokenHelper<T> {

	public String generateToken(T object, int expiration); 
	public T decodeToken(String token);

}
