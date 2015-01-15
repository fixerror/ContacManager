package com.radchenko.contact.services.impl;

import org.springframework.stereotype.Service;

import com.radchenko.contact.services.EmailService;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Service("emailService")
public class EmailServiceStub implements EmailService {

	@Override
	public void sendVerificationEmail() {
		//Do nothing
	}

	@Override
	public void sendGeneratedPasswordToEmail() {
		//Do nothing
	}
}
