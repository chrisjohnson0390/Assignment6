package com.meritamerica.assignment6.misc;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Table(name = "account_holder_contact")
public class AccountHolderContactDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	private String phoneNumber;
	private String email;
	private String address;

	@OneToOne(cascade = CascadeType.ALL)
	private AccountHolder accountHolder;
	
	public AccountHolderContactDetails() {
		
	}

	public long getId() {
		return id; 
	}
	
	public void setId(long id) {
		this.id = id; 
	}
	
	public String getEmail() { 
		return email; 
	}
	
	public void setEmail(String email) { 
		this.email = email; 
	}
	
	public String getPhoneNum() {
		return phoneNumber;
	}

	public void setPhoneNum(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() { 
		return address; 
	}
	
	public void setAddress(String address) { 
		this.address = address; 
	}
	
	public AccountHolder getAccountHolder() { 
		return accountHolder; 
	}
	
	public void setAccountHolder(AccountHolder accountHolder) { 
		this.accountHolder = accountHolder; 
	}
}