package com.radchenko.contact.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.radchenko.contact.forms.ContactForm;
import com.radchenko.contact.services.ContactService;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Controller
@RequestMapping("/tutor")
public class TutorController extends AbstractTutorController {
	
	@Autowired
	private ContactService contactService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String listContacts(Map<String, Object> map) {

		map.put("contact", new ContactForm());
		map.put("contactList", contactService.listContact());

		return "tutor/home";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	ContactForm contact, BindingResult result) {

		contactService.addContact(contact);

		return "redirect:/tutor/home";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Long contactId) {

		contactService.removeContact(contactId);

		return "redirect:/tutor/home";
	}
}
