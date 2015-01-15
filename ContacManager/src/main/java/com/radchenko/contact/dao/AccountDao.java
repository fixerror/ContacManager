package com.radchenko.contact.dao;

import java.util.List;

import com.radchenko.contact.entities.Account;

/**
 * @author radchenko.as
 * @version 1.0
 */
public interface AccountDao extends IEntityDao<Account> {

	List<Account> listAccounts (int offset, int count);
	
	Account findByLogin(String email);
	
}
