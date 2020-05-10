package com.meritamerica.assignment6.misc;

import com.meritamerica.assignment6.accounts.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeritBank {

	private static List<AccountHolder> accountHolders = new ArrayList<>();
	private static List<CDOffering> cdOfferings = new ArrayList<>();
	private static long nextAccountNumber = 1;

	public static void addAccountHolder(AccountHolder accountHolder) {
		accountHolders.add(accountHolder);
	}

	public static List<AccountHolder> getAccountHolders() {
		return accountHolders;
	}

	public static List<CDOffering> getCDOfferings() {
		return cdOfferings;
	}

	public static BankAccount getBankAccountByAccountNumber(long id) {
		for (AccountHolder accountHolder : accountHolders) {
			for (BankAccount account : accountHolder.getCheckingAccounts()) {
				if (account.getAccountNumber() == id) {
					return account;
				}
			}
			for (BankAccount account : accountHolder.getSavingsAccounts()) {
				if (account.getAccountNumber() == id) {
					return account;
				}
			}
			for (BankAccount account : accountHolder.getCdAccounts()) {
				if (account.getAccountNumber() == id) {
					return account;
				}
			}
		}
		return null;

	}
	static CDOffering getSecondBestCDOffering(double depositAmount) {
		if(cdOfferings.size() <= 1) { 
			return null; 
		}
		CDOffering best = getBestCDOffering(depositAmount);
		CDOffering secondBest = null;
		double secondBestRate = 0;
		for(CDOffering cdOffering : cdOfferings) {
			if(cdOffering == best) { continue; }
			if(cdOffering.getInterestRate() > secondBestRate) {
				secondBest = cdOffering;
				secondBestRate = cdOffering.getInterestRate();
			}
			
		}
		return secondBest;
	}
	
	public static void setCDOfferings (List<CDOffering> cdOffers) {
		for(CDOffering offering : cdOffers) {
			cdOfferings.add(offering);
		}
	}
	
	public static void addCDOffering(CDOffering cdOffering) {
		cdOfferings.add(cdOffering);
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		if(cdOfferings.size() == 0) {
			return null;
		}
		double bestRate = 0;
		CDOffering best = null;
		for(CDOffering cdOffering : cdOfferings) {
			if(cdOffering.getInterestRate() > bestRate) {
				best = cdOffering;
				bestRate = cdOffering.getInterestRate();
			}
		}
		return best;
	}

	public static long getNextAccountNumber() {
		return nextAccountNumber;

	}

	public static void setNextAccountNumber(long accountNumber) {
		nextAccountNumber = accountNumber;
	}

	public static AccountHolder getAccountHolderById(long id) {
		for (AccountHolder accountHolder : accountHolders) {
			if (accountHolder.getId() == id) {
				return accountHolder;
			}
		}
		return null;
	}
	
	public static double totalBalances() {
		double sum = 0;
		for(AccountHolder accountHolder : accountHolders) {
			for(BankAccount account : accountHolder.getCheckingAccounts()) {
				sum += account.getBalance();
			}
			for(BankAccount account : accountHolder.getSavingsAccounts()) {
				sum += account.getBalance();
			}
			for(BankAccount account: accountHolder.getCdAccounts()) {
				sum += account.getBalance();
			}
		}
		return sum;
	}
	
	public static void sortAccountHolders() {
		Collections.sort(accountHolders);       
	}

}