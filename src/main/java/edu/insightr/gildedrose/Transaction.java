package edu.insightr.gildedrose;

import java.time.LocalDate;

public class Transaction {
    private enum Transactiontype {
        sell, buy
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

    public Item getItem() { return item; }

    public LocalDate getTransactiondate() { return transactiondate;}

    public Transactiontype getTransactiontype() {return transactiontype;}

    public void setItem(Item item) {this.item = item;}

    public void setTransactiondate(LocalDate transactiondate) {this.transactiondate = transactiondate;}

    public void setTransactiontype(Transactiontype transactiontype) {this.transactiondate = transactiondate;}
}
