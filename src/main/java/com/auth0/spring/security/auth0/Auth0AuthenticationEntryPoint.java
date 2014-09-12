package com.auth0.spring.security.auth0;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class Auth0AuthenticationEntryPoint implements AuthenticationEntryPoint  {

	//TODO response.sendError is not handled the same way by jetty
	@SuppressWarnings("deprecation")
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		PrintWriter writer = response.getWriter();

		 if(isPreflight(request)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		 } else if(authException instanceof Auth0TokenException){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
			writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage());
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
            writer.println("HTTP Status " + HttpServletResponse.SC_FORBIDDEN + " - " + authException.getMessage());
		}
	}
	

    /**
     * Checks if this is a X-domain pre-flight request.
     * @param request
     * @return
     */
    private boolean isPreflight(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
    
}