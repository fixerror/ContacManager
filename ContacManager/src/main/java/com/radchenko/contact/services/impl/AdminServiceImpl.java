package com.radchenko.contact.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radchenko.contact.dao.AccountDao;
import com.radchenko.contact.dao.AccountRoleDao;
import com.radchenko.contact.entities.Account;
import com.radchenko.contact.services.AdminService;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	@Qualifier("hiberanteAccountRoleDao")
	private AccountRoleDao accountRoleDao;
	
	@Transactional
	public void active(Long id) {
		Account acaunt1 =null;
		acaunt1 = accountDao.findById(id);
		acaunt1.setActive(true);
		accountDao.update(acaunt1);
	}	
	@Transactional
	public void deactivate(Long id) {
		Account acaunt2=null;
		acaunt2 = accountDao.findById(id);
		acaunt2.setActive(false);
		accountDao.update(acaunt2);
	}	
	@Transactional
	public List<Account> listContact() {
		return accountDao.findAll();
	}


	
}
