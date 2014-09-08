package sib.calipho.spring.security.auth0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A controller exposing secured and unsecured methods (defined on auth0-security-context.xml)
 * 
 * @author Daniel Teixeira
 */
@Controller
public class SecuredApiController {


	@RequestMapping(value = "secured")
	@ResponseBody
	public String secured() {
		return "Bravo you have accessed a secured url!";
	}

	@RequestMapping(value = "unsecured")
	@ResponseBody
	public String unsecured() {
		return "This is an unsecured url";
	}


}
