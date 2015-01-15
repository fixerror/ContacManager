package com.radchenko.contact.services;

/**
 * @author radchenko.as
 * @version 1.0
 */
public interface EmailService {

	void sendVerificationEmail();
	
	void sendGeneratedPasswordToEmail();
}
