package com.radchenko.contact.services;

import java.util.List;

import com.radchenko.contact.entities.Account;


/**
 * @author radchenko.as
 * @version 1.0
 */
public interface AdminService {
	public List<Account> listContact();
	public void active(Long id);
	public void deactivate(Long id);
}
