package com.radchenko.contact.dao.impl.hibernate;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.radchenko.contact.dao.ContactDAO;
import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.Contact;





@Repository("hiberanteContactDao")
public class ContactDaoImpl extends AbstractEntityDao<Contact> implements ContactDAO {

	@Override
	protected Class<Contact> getEntityClass() {
		return Contact.class;
	}
	@Override
	public List<Contact> listContact(Account account){
		return getSession().createCriteria(getEntityClass()).add(Restrictions.eq("id_account", account.getId())).list();	
	}
}
