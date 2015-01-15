package com.radchenko.contact.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_IDACCOUNT_GENERATOR", sequenceName="ACCOUNT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_IDACCOUNT_GENERATOR")
	@Column(name="id_account", unique=true, nullable=false)
	private Long idAccount;

	@Column(nullable=false)
	private Boolean active;

	@Column(nullable=false)
	private Timestamp created;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=60)
	private String login;

	@Column(length=80)
	private String name;

	@Column(nullable=false, length=255)
	private String password;

	@Column(name="second_name", length=80)
	private String secondName;

	@Column(length=80)
	private String surname;

	private Timestamp updated;

	//bi-directional many-to-one association to AccountRole
	@OneToMany(mappedBy="account")
	private List<AccountRole> accountRoles;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="account")
	private List<Contact> contacts;

	public Account() {
	}

	public Long getIdAccount() {
		return this.idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<AccountRole> getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(List<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}

	public AccountRole addAccountRole(AccountRole accountRole) {
		getAccountRoles().add(accountRole);
		accountRole.setAccount(this);

		return accountRole;
	}

	public AccountRole removeAccountRole(AccountRole accountRole) {
		getAccountRoles().remove(accountRole);
		accountRole.setAccount(null);

		return accountRole;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setAccount(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setAccount(null);

		return contact;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdAccount();
	}

}