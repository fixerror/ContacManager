package com.radchenko.contact.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.radchenko.contact.services.AdminService;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminControler extends AbstractController {

	@Autowired
	protected AdminService adminService;
	
	
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String listAccount(Map<String, Object> map) {

		map.put("admintList", adminService.listContact());
		return "admin/home";
	}
	
	@RequestMapping("/active/{idAccount}")
	public String activateAccount(@PathVariable("idAccount")
	Long idAccount) {
		adminService.active(idAccount);
		return "redirect:/admin/home";
	}
	@RequestMapping("/deactivate/{idAccount}")
	public String deactivateAccount(@PathVariable("idAccount")
	Long idAccount) {
		adminService.deactivate(idAccount);
		return "redirect:/admin/home";
	}
}
