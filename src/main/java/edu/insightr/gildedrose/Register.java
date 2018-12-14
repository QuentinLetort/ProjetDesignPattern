package edu.insightr.gildedrose;

import java.time.LocalDate;
import java.util.*;

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


    // Thanks to getTransactions() function, look over this List<>
    // For each date corresponding to a transaction
    // into 2 mapping() functions :
    // create a counter for number of sold items associate to the date of transaction
    // create an other counter for number of purchased items associate to the date of transaction

    public Map<LocalDate, Integer> mapOfItemsInFunctionOfTransaction(Transaction.Transactiontype transactiontype) {
        Map<LocalDate, Integer> mapOfSoldItems = new HashMap<>();

        for (Transaction currentTrans : transactions) {
            if (currentTrans.getTransactiontype() == transactiontype) {
                LocalDate date = currentTrans.getTransactiondate();
                if (mapOfSoldItems.get(date) == null) {
                    mapOfSoldItems.put(date, 1);
                } else {
                    int compteur = mapOfSoldItems.get(currentTrans.getTransactiondate());
                    mapOfSoldItems.put(date, compteur + 1);
                }
            }

        }
        return mapOfSoldItems;
    }


}
