package com.radchenko.contact.components;

import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.AccountRole;
import com.radchenko.contact.entities.Contact;
import com.radchenko.contact.entities.Role;


/**
 * @author radchenko.as
 * @version 1.0
 */
public interface EntityBuilder {

	Account buildAccount();
	
	AccountRole buildAccountRole(Account account, Role role);

	Contact createContact();
}
