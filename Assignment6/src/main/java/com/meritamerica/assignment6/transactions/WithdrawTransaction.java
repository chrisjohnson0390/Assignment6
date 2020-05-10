package com.meritamerica.assignment6.transactions;

import com.meritamerica.assignment6.accounts.*;
import java.util.Date;

public class WithdrawTransaction extends Transaction {
	public WithdrawTransaction(BankAccount targetAccount, double amount, Date date) {
		super(targetAccount, amount, date);
	}
}
