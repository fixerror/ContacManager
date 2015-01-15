package com.radchenko.contact.components.impl;



import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.radchenko.contact.components.EntityBuilder;
import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.AccountRole;
import com.radchenko.contact.entities.Contact;
import com.radchenko.contact.entities.Role;
import com.radchenko.contact.security.SecurityUtils;


/**
 * @author radchenko.as
 * @version 1.0
 */
@Component("entityBuilder")
public class EntityBuilderImpl implements EntityBuilder {
	
	@Override
	public Account buildAccount() {
		Account a = new Account();
		a.setCreated(new Timestamp(System.currentTimeMillis()));
		a.setActive(Boolean.FALSE);
		return a;
	}
	
	@Override
	public AccountRole buildAccountRole(Account account, Role role) {
		return new AccountRole(account, role);
	}

	@Override
	public Contact createContact() {
		long currentAccount = SecurityUtils.getCurrentIdAccount();
		Account b =new Account();
		b.setIdAccount(currentAccount);
		Contact con = new Contact();
		con.setAccount(b);
		con.setCreated(new Timestamp(System.currentTimeMillis()));
		return con;
	}

}
