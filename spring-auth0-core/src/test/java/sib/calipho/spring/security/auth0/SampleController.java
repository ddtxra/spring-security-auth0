package sib.calipho.spring.security.auth0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A sample controller just for tests with 2 methods (one secured and the other one not secured)
 * @author dteixeira
 *
 */
@Controller
public class SampleController {

	@RequestMapping(value = "secured")
	@ResponseBody
	public String secured() {
		return "Congratulations you have accessed a secured url!";
	}

	@RequestMapping(value = "unsecured")
	@ResponseBody
	public String unsecured() {
		return "This is an unsecured url";
	}
}
