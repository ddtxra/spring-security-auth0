package sib.calipho.spring.security.auth0;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SecuredControllerTest extends MVCBaseSecurityTest {

	@Test
	public void shouldReturn403WithoutToken() throws Exception {
		callUrlWithoutToken("/secured").andExpect(status().isForbidden());
	}

	@Test
	public void shouldReturn401ForAnInvalidToken() throws Exception {
		callUrlWithToken("/secured", "a.b.c").andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldReturn401ForATokenThatHasExpired() throws Exception {
		callUrlWithToken("/secured", generateTokenWithExpirationDate(-1, TimeUnit.SECONDS)).andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldReturn200ForAValidToken() throws Exception {
		callUrlWithToken("/secured", generateTokenWithExpirationDate(1, TimeUnit.DAYS)).andExpect(status().isOk());
	}

	@Test
	public void shouldReturn200ForAnUnsecuredUrl() throws Exception {
		callUrlWithToken("/unsecured", generateTokenWithExpirationDate(1, TimeUnit.DAYS)).andExpect(status().isOk());
	}

}
