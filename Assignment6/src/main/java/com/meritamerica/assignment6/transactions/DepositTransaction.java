package com.meritamerica.assignment6.transactions;

import com.meritamerica.assignment6.accounts.*;
import java.util.Date;

public class DepositTransaction extends Transaction {
	public DepositTransaction(BankAccount targetAccount, double amount, Date date) {
		super(targetAccount, amount, date);
	}	
}
