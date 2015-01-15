package com.radchenko.contact.services;

import java.util.List;

import com.radchenko.contact.entities.Contact;
import com.radchenko.contact.forms.ContactForm;





public interface ContactService {
	
	public void addContact(ContactForm contact);
	public List<Contact> listContact();
	public void removeContact(Long id);
}
