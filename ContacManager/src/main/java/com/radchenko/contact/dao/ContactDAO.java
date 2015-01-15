package com.radchenko.contact.dao;


import java.util.List;

import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.Contact;






public interface ContactDAO extends IEntityDao<Contact>  {

	public List<Contact> listContact(Account account);


}
