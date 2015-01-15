package com.radchenko.contact.entities;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the contacts database table.
 * 
 */
@Entity
@Table(name = "contacts")
@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
public class Contact extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONTACTS_IDCONTACT_GENERATOR", sequenceName = "CONTACT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACTS_IDCONTACT_GENERATOR")
	@Column(name = "id_contact", unique = true, nullable = false)
	private Long idContact;

	private Timestamp created;

	@Column(length = 100)
	private String email;

	@Column(length = 100)
	private String firstname;

	@Column(length = 100)
	private String lastname;

	@Column(length = 100)
	private String telephone;

	@Column(name = "id_account", insertable = false, updatable = false)
	private long id_account;

	public long getId_account() {
		return id_account;
	}

	public void setId_account(long id_account) {
		this.id_account = id_account;
	}

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "id_account", nullable = false)
	private Account account;

	public Contact() {
	}

	public Long getIdContact() {
		return this.idContact;
	}

	public void setIdContact(Long idContact) {
		this.idContact = idContact;
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

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public Serializable getId() {
		return getIdContact();
	}
}