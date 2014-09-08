package sib.calipho.spring.security.auth0;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * A sample controller that serves the client home page. 
 * The client home page can be hosted in any server (no need to be spring)
 * 
 * @author Daniel Teixeira 
 */
@Controller
public class HomeController {

	@Value("${auth0.clientId}")
	private String clientId;

	@Value("${auth0.domain}")
	private String clientDomain;

	@RequestMapping(value = "/")
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("id", clientId);
		modelAndView.addObject("domain", clientDomain);
		
		return modelAndView;
	}


}
