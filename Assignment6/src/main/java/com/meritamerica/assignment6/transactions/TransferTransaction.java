package com.meritamerica.assignment6.transactions;

import com.meritamerica.assignment6.accounts.*;
import java.util.Date;

public class TransferTransaction extends Transaction {

	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, Date date) {
		super(sourceAccount, targetAccount, amount, date);
	}
	
}
