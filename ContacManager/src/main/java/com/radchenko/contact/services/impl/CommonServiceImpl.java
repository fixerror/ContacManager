package com.radchenko.contact.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radchenko.contact.components.EntityBuilder;
import com.radchenko.contact.dao.AccountDao;
import com.radchenko.contact.dao.AccountRoleDao;
import com.radchenko.contact.dao.RoleDao;
import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.AccountRole;
import com.radchenko.contact.entities.Role;
import com.radchenko.contact.exceptions.InvalidUserInputException;
import com.radchenko.contact.forms.SignUpForm;
import com.radchenko.contact.services.CommonService;
import com.radchenko.contact.services.EmailService;
import com.radchenko.contact.utils.ReflectionUtils;



/**
 * @author radchenko.as
 * @version 1.0
 */
@Service("commonService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonServiceImpl implements CommonService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	@Qualifier("hibernateRoleDao")
	private RoleDao roleDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	@Autowired
	private EntityBuilder entityBuilder;
	
	@Autowired
	private EmailService emailService;
	
	public CommonServiceImpl() {
		super();
	}

	
	
	protected Account signUp(SignUpForm form, boolean sendVerificationEmail, boolean sendPasswordToEmail) throws InvalidUserInputException {
		Account a = entityBuilder.buildAccount();
		ReflectionUtils.copyByFields(a, form);
		accountDao.save(a);
		
		Role r = roleDao.getStudentRole();
		AccountRole ar = entityBuilder.buildAccountRole(a, r);
		accountRoleDao.save(ar);
		
		if(sendVerificationEmail) {
			emailService.sendVerificationEmail();
		}
		if(sendPasswordToEmail) {
			emailService.sendGeneratedPasswordToEmail();
		}
		return a;
	}
	
	@Override
	@Transactional(readOnly=false, rollbackFor={InvalidUserInputException.class, RuntimeException.class})
	public Account signUp(SignUpForm form) throws InvalidUserInputException {
		return signUp(form, true, false);
	}

	@Override
	public List<Role> listAllRoles() {
		return roleDao.findAll();
	}
	
	
	}

