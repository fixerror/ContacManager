package com.radchenko.contact.exceptions;

/**
 * @author radchenko.as
 * @version 1.0
 */
public class InvalidUserInputException extends Exception {
	private static final long serialVersionUID = 4286728050862639524L;

	public InvalidUserInputException(String message) {
		super(message);
	}

	public InvalidUserInputException(Throwable cause) {
		super(cause);
	}

	public InvalidUserInputException(String message, Throwable cause) {
		super(message, cause);
	}

}
