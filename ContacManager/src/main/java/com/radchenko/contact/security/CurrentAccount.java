package com.radchenko.contact.security;

import org.springframework.security.core.userdetails.User;

import com.radchenko.contact.entities.Account;

/**
 * @author radchenko.as
 * @version 1.0
 */
public class CurrentAccount extends User{
	private static final long serialVersionUID = 8252540687900090175L;
	private final long idAccount;
	private int role;
	public CurrentAccount(Account a) {
		super(a.getEmail(), a.getPassword(), a.getActive(), true, true, true, 
				AuthentificationService.convert(a.getAccountRoles()));
		this.idAccount = a.getIdAccount();
	}
	public long getIdAccount() {
		return idAccount;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
}
