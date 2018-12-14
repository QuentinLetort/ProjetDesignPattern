package edu.insightr.gildedrose;

import java.time.LocalDate;

public class Transaction {

    private Item item;
    private LocalDate transactiondate;
    private Transactiontype transactiontype;


    public Transaction(Item item, LocalDate transactiondate, Transactiontype transactiontype) {
        this.item = item;
        this.transactiondate = transactiondate;
        this.transactiontype = transactiontype;
    }

    public String getItem() {
        return item.getName();
    }

    public LocalDate getTransactiondate() {
        return transactiondate;
    }

    public Transactiontype getTransactiontype() {
        return transactiontype;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "item=" + item.getName() +
                ", transactiondate=" + transactiondate +
                ", transactiontype=" + transactiontype +
                '}';
    }

    public enum Transactiontype {
        sell, buy
    }
}
