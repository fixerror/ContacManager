package com.radchenko.contact.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.radchenko.contact.entities.Account;
/**
 * @author radchenko.as
 * @version 1.0
 */

public class SecurityUtils{
	
	public static long getCurrentIdAccount () {
		CurrentAccount a = getCurrentAccount();
		return a != null ? a.getIdAccount() : -1;
	}
	
	public static CurrentAccount getCurrentAccount () {
		Object principal;
		try {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
		if (principal instanceof CurrentAccount) {
			return (CurrentAccount)principal;
		}
		return null;
	}
	
	public static void authentificate(Account account) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
                new CurrentAccount(account),
                account.getPassword(),
                AuthentificationService.convert(account.getAccountRoles()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
