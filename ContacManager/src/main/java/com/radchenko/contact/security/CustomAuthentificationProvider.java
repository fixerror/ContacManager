package com.radchenko.contact.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.radchenko.contact.ApplicationConstants;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Component("customAuthenticationProvider")
public class CustomAuthentificationProvider extends DaoAuthenticationProvider {

	@Override
	@Autowired
	@Qualifier("accountAuthentificationService")
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	@Autowired
	@Qualifier("pwdEncoder")
	public void setPasswordEncoder(Object passwordEncoder) {
		super.setPasswordEncoder(passwordEncoder);
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
		CurrentAccount a = (CurrentAccount) userDetails;
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		int role = Integer.parseInt(request.getParameter("idRole"));
		if(!ApplicationConstants.ROLES.contains(role)) {
			throw new AuthenticationException("Invalid role: "+role){
				private static final long serialVersionUID = 9141828180466015708L;
			};
		}
		a.setRole(role);
	}
}
