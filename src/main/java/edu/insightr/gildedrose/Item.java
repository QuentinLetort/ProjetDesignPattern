package edu.insightr.gildedrose;

import java.time.LocalDate;
import java.util.Objects;

public class Item {
    private String name;
    private int sellIn;
    private int quality;
    private LocalDate creationdate;

    public Item(String name, int sellIn, int quality, LocalDate creationdate) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.creationdate = creationdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public LocalDate getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(LocalDate creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return sellIn == item.sellIn &&
                quality == item.quality &&
                Objects.equals(name, item.name) &&
                creationdate == item.creationdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality, creationdate);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                ", creationdate=" + creationdate +
                '}';
    }
}