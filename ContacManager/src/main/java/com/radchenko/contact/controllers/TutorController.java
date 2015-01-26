package com.radchenko.contact.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class TutorController {
	
	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String listTest(Model ui) {
		ui.addAttribute("contact", new ContactForm());
		ui.addAttribute("contactList", contactService.listContact());
		return "tutor/home";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	ContactForm contact, Model ui, BindingResult result) {
		ui.addAttribute("contact", contact);
		contactService.addContact(contact);

		return "redirect:/tutor/home";
	}
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Long contactId) {
		contactService.removeContact(contactId);
		return "redirect:/tutor/home";
	}
}
