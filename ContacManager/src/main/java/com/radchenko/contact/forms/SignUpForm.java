package com.radchenko.contact.forms;



/**
 * @author radchenko.as
 * @version 1.0
 */
public class SignUpForm extends AbstractLoginForm implements IForm {
	private static final long serialVersionUID = -3633827335080843887L;
	
	private String password2;
	
	private String email;
	
	private String surname;
	
	private String name;
	
	private String secondName;

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
}
