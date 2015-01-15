package com.radchenko.contact.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radchenko.contact.components.EntityBuilder;
import com.radchenko.contact.dao.ContactDAO;
import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.Contact;
import com.radchenko.contact.forms.ContactForm;
import com.radchenko.contact.security.SecurityUtils;
import com.radchenko.contact.services.ContactService;
import com.radchenko.contact.utils.ReflectionUtils;


@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private EntityBuilder entityBuilder;

	@Transactional
	public void addContact(ContactForm contact) {
		
		Contact a= entityBuilder.createContact();
		ReflectionUtils.copyByFields(a, contact);
		contactDAO.save(a);

	}

	@Transactional
	public List<Contact> listContact() {
	long currentAccount = SecurityUtils.getCurrentIdAccount();
	Account account=new Account();
	account.setIdAccount(currentAccount);
	return  contactDAO.listContact(account);                           

	}

	@Transactional
	public void removeContact(Long id) {
		contactDAO.remove(contactDAO.findById(id));
	}

}
