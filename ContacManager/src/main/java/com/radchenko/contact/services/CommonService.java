package com.radchenko.contact.services;

import java.util.List;

import com.radchenko.contact.entities.Account;
import com.radchenko.contact.entities.Role;
import com.radchenko.contact.exceptions.InvalidUserInputException;
import com.radchenko.contact.forms.SignUpForm;


/**
 * @author radchenko.as
 * @version 1.0
 */
public interface CommonService {

	Account signUp (SignUpForm form) throws InvalidUserInputException;
	List<Role> listAllRoles();
}
