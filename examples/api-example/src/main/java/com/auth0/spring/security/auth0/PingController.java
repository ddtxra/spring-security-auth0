package com.auth0.spring.security.auth0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is the PingController. Will return a message on /ping
 * 
 * @author gonto
 *
 */
@Controller
public class PingController {

	@RequestMapping(value = "/ping")
	@ResponseBody
	public String ping() {
		return "All good. You don't need to be authenticated to call this";
	}


}
