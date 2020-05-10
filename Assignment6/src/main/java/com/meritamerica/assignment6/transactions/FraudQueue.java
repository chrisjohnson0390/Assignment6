package com.meritamerica.assignment6.transactions;

import java.util.LinkedList;

public class FraudQueue{
	private LinkedList<Transaction> transactions;
	
	public FraudQueue() {
		transactions = new LinkedList<>();
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.push(transaction);
	}
	
	public Transaction getTransaction() {
		return transactions.pop();
	}
	
	public int getSize() {
		return transactions.size();
	}
}