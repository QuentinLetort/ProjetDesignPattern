package edu.insightr.gildedrose;

import java.util.ArrayList;

public class Register {
    private ArrayList<Transaction> transactions;

    public Register() {this.transactions = new ArrayList<Transaction>();}

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void addTransactions(Transaction transaction)
    {
        this.transactions.add(transaction);
    }
}
