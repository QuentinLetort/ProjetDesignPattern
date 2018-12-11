package edu.insightr.gildedrose;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Register {
    private ArrayList<Transaction> transactions;

    public Register(ArrayList<Transaction> transactions) {this.transactions = transactions;}

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void addTransactions(Transaction transaction)
    {
        this.transactions.add(transaction);
    }
}
