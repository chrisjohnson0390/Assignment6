package com.meritamerica.assignment6.misc;

import com.meritamerica.assignment6.accounts.*;
import com.meritamerica.assignment6.exceptions.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accountholders")
public class AccountHolder implements Comparable<AccountHolder> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;

	@NotBlank(message = "First Name is required")
	private String firstName;

	private String middleName;

	@NotBlank(message = "Last Name is required")
	private String lastName;

	@Size(min = 9)
	@NotBlank(message = "SSN is required")
	private String ssn;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private AccountHolderContactDetails accountHolderContactDetails;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CheckingAccount> checkingAccounts;

	@OneToMany(cascade = CascadeType.ALL)
	private List<SavingsAccount> savingsAccounts;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CDAccount> CDAccounts;

	public AccountHolder() {
		this.checkingAccounts = new ArrayList<>();
		this.savingsAccounts = new ArrayList<>();
		this.CDAccounts = new ArrayList<>();
	}

	public boolean addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
		if (checkingAccount == null) {
			return false;
		}
		if (getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable To Process");
		}
		checkingAccounts.add(checkingAccount);
		checkingAccount.setAccountHolder(this.id);
		return true;
	}

	public boolean addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
		if (savingsAccount == null) {
			return false;
		}
		if (getCheckingBalance() + getSavingsBalance() + savingsAccount.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Unable to Process");
		}
		savingsAccounts.add(savingsAccount);
		savingsAccount.setAccountHolder(this.id);
		return true;
	}

	public boolean addCDAccount(CDAccount CDAccount) {
		if (CDAccount == null) {
			return false;
		}
		CDAccounts.add(CDAccount);
		CDAccount.setAccountHolder(this.id);
		return true;
	}

	public double getCheckingBalance() {
		double sum = 0;
		for (BankAccount account : checkingAccounts) {
			sum += account.getBalance();
		}
		return sum;
	}

	public double getSavingsBalance() {
		double sum = 0;
		for (BankAccount account : savingsAccounts) {
			sum += account.getBalance();
		}
		return sum;
	}

	public double getCDBalance() {
		double sum = 0;
		for (BankAccount account : CDAccounts) {
			sum += account.getBalance();
		}
		return sum;
	}

	public double getCombinedBalance() {
		double sum = 0;
		sum += getCheckingBalance();
		sum += getSavingsBalance();
		sum += getCDBalance();
		return sum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String name) {
		this.middleName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(List<CheckingAccount>checkingAccount) {
		this.checkingAccounts = checkingAccount;
	}

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingsAccount> savingsAccount) {
		this.savingsAccounts = savingsAccount;
	}

	public List<CDAccount> getCdAccounts() {
		return CDAccounts;
	}

	public void setCdAccounts(List<CDAccount> CDAccount) {
		this.CDAccounts = CDAccount;
	}

	public AccountHolderContactDetails getAccountHolderContactDetails() {
		return accountHolderContactDetails;
	}

	public void setAccountHolderContactInfo(AccountHolderContactDetails accountHolderContactDetails) {
		this.accountHolderContactDetails = accountHolderContactDetails;
	}

	public int getNumberCheckingAccounts() {
		return this.checkingAccounts.size();
	}

	public int getNumberSavingsAccounts() {
		return this.savingsAccounts.size();
	}

	public int getNumberCDAccounts() {
		return this.CDAccounts.size();
	}

	@Override
	public int compareTo(AccountHolder other) {
		int mySum = (int) getCombinedBalance();
		int otherSum = (int) other.getCombinedBalance();
		return mySum - otherSum;
	}
}