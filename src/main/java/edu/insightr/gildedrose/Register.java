package edu.insightr.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Transaction> transactions;

    public Register() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void AfficheTransaction() {
        System.out.println("***************");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("***************");
        System.out.println("\n");
    }
}
