package com.radchenko.contact.entities;

import java.io.Serializable;

import javax.persistence.*;




/**
 * The persistent class for the account_role database table.
 * 
 */
@Entity
@Table(name="account_role")
@NamedQuery(name="AccountRole.findAll", query="SELECT a FROM AccountRole a")
public class AccountRole extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_ROLE_IDACCOUNTROLE_GENERATOR", sequenceName="ACCOUNT_ROLE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_ROLE_IDACCOUNTROLE_GENERATOR")
	@Column(name="id_account_role", unique=true, nullable=false)
	private Long idAccountRole;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="id_account", nullable=false)
	private Account account;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_role", nullable=false)
	private Role role;
	public AccountRole(){
		
	}
	public AccountRole(Account account2, Role role2) {
		super();
		this.account = account2;
		this.role = role2;
	}

	public Long getIdAccountRole() {
		return this.idAccountRole;
	}

	public void setIdAccountRole(Long idAccountRole) {
		this.idAccountRole = idAccountRole;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Transient
	@Override
	public Serializable getId() {
		return getIdAccountRole();
	}

}