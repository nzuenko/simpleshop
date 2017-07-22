package com.nz.simpleshop.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
 
    @Override
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        boolean admin = false;
        
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
            	admin = true;
            }
        }
        if (admin){
        	response.sendRedirect("/admin/");
        } else{
        	response.sendRedirect("/");
        }
        response.setStatus(HttpServletResponse.SC_OK);

    }
}