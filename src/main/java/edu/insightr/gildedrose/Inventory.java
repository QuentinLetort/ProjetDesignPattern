package edu.insightr.gildedrose;

import java.util.Arrays;

public class Inventory {

    private Item[] items;

    public Inventory (Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

    }

    public Item[] getItems() {
        return items;
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateItems(int i){

        if( items[i].getQuality() > 0){
            items[i].setQuality(items[i].getQuality() - 1);
        }
        if ( items[i].getQuality() > 1
                && items[i].getSellIn() <= 0){
            items[i].setQuality(items[i].getQuality() -1);
        }
        items[i].setSellIn(items[i].getSellIn() -1);

    }

    public void updateAgedBrie(int i){
        if( items[i].getQuality() < 50){
            items[i].setQuality(items[i].getQuality() +1);
        }
        if(items[i].getSellIn()<=0 && items[i].getQuality() < 50 ){
            items[i].setQuality(items[i].getQuality() +1);
        }
         items[i].setSellIn((items[i].getSellIn() -1));

    }

    public void updateConjured(int i){
        if( items[i].getQuality() > 1) {
            items[i].setQuality(items[i].getQuality() - 2);
        }

        if ( items[i].getQuality() > 3
                && items[i].getSellIn() < 0){
            items[i].setQuality(items[i].getQuality() -4);
        }
        items[i].setSellIn(items[i].getSellIn() -1);

    }

    public void updateBackstage(int i){

        if (items[i].getSellIn() < 0){
            items[i].setQuality(items[i].getQuality() - items[i].getQuality());
        }
        else{
            if(items[i].getQuality() <50){
                items[i].setQuality(items[i].getQuality()+1);
            }

            else{
                if(items[i].getSellIn() < 11 && items[i].getQuality() < 50){
                    items[i].setQuality(items[i].getQuality()+1);
                }
                if(items[i].getSellIn() < 5 && items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);
                }
            }
        }
        items[i].setSellIn((items[i].getSellIn() -1));

    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() != "Aged Brie" && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);
                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }
            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }
            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }
    public void updateQualityBis(){

        for (int i = 0; i < items.length ; i ++){

            if(items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Sulfuras, Hand of Ragnaros"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert"
                    && items[i].getName() != "Conjured Mana Cake"){

                updateItems(i);
            }

            if(items[i].getName() == "Aged Brie"){
                updateAgedBrie(i);
            }

            if(items[i].getName() == "Conjured Mana Cake"){
                updateConjured(i);
            }

            if(items[i].getName() == "Backstage passes to a TAFKAL80ETC concert"){
                updateBackstage(i);
            }

            if(items[i].getName() == "Sulfuras, Hand of Ragnaros"){
                items[i].setSellIn(items[i].getSellIn() -1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Arrays.equals(items, inventory.items);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(items);
    }

    public static void main(String[] args){
        Inventory inventory = new Inventory();
        Inventory inventory1=new Inventory();
        for(int i = 0; i < 50 ; i ++){
            inventory.updateQuality();
            inventory1.updateQualityBis();
            inventory.printInventory();
            inventory1.printInventory();
        }
    }

}
