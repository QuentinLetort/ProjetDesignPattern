package edu.insightr.gildedrose;

import java.time.LocalDate;

public class Transaction {
    public String getItem() {
        return item.getName();
    }

    private Item item;
    private LocalDate transactiondate;
    private Transactiontype transactiontype;



    public Transaction (Item item, LocalDate transactiondate, Transactiontype transactiontype)
    {
        this.item = item;
        this.transactiondate = transactiondate;
        this.transactiontype = transactiontype;
    }

    public void setTransactiontype(Transactiontype transactiontype) {
        this.transactiontype = transactiontype;
    }

    public LocalDate getTransactiondate() { return transactiondate;}

    public Transactiontype getTransactiontype() {return transactiontype;}

    public void setItem(Item item) {this.item = item;}

    public void setTransactiondate(LocalDate transactiondate) {this.transactiondate = transactiondate;}

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
