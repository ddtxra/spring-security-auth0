package sib.calipho.spring.security.auth0;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * A sample controller just for tests with 2 methods (one secured and the other one not secured)
 * 
 * @author dteixeira
 */
@Controller
public class HomeController {

	@Value("${auth0.clientSecret}")
	private String clientSecret;

	@Value("${auth0.clientId}")
	private String clientId;

	@Value("${auth0.domain}")
	private String clientDomain;

	@RequestMapping(value = "/")
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("id", clientId);
		modelAndView.addObject("secret", clientSecret);
		modelAndView.addObject("domain", clientDomain);
		
		return modelAndView;
	}

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
