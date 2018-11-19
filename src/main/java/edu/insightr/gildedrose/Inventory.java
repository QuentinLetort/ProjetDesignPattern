package edu.insightr.gildedrose;

import java.util.Arrays;

public class Inventory {

    // TODO (PBZ) : you have a lot of warnings that could be resolved
    // TODO (PBZ) : your code is not indented

    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item(DEXTERITY_VEST, 10, 20),
                new Item(AGED_BRIE, 2, 0),
                new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7),
                new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80),
                new Item(BACKSTAGE_PASSES_TO_CONCERT, 15, 20),
                new Item(CONJURED_MANA_CAKE, 3, 6)
        };

    }

    public Item[] getItems() {
        return items;
    }

    private void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    private void updateItems(int i) {

        if (items[i].getQuality() > 0) {
            items[i].setQuality(items[i].getQuality() - 1);
        }
        if (items[i].getQuality() > 0
                && items[i].getSellIn() <= 0) {
            items[i].setQuality(items[i].getQuality() - 1);
        }
        items[i].setSellIn(items[i].getSellIn() - 1);

    }

    private void updateAgedBrie(int i) {
        if (items[i].getQuality() < 50) {
            items[i].setQuality(items[i].getQuality() + 1);
        }
        items[i].setSellIn((items[i].getSellIn() - 1));

    }

    private void updateConjured(int i) {
        if (items[i].getQuality() > 0) {
            items[i].setQuality(items[i].getQuality() - 2);
        }

        if (items[i].getQuality() > 0
                && items[i].getSellIn() <= 0) {
            items[i].setQuality(items[i].getQuality() - 2);
        }
        items[i].setSellIn(items[i].getSellIn() - 1);
        if (items[i].getQuality() < 0) {
            items[i].setQuality(0);
        }
    }

    private void updateBackstage(int i) {

        if (items[i].getSellIn() <= 0) {
            items[i].setQuality(0);
        } else {
            if (items[i].getQuality() < 50) {
                items[i].setQuality(items[i].getQuality() + 1);
            }
            if (items[i].getSellIn() < 11 && items[i].getQuality() < 50) {
                items[i].setQuality(items[i].getQuality() + 1);
            }
            if (items[i].getSellIn() < 6 && items[i].getQuality() < 50) {
                items[i].setQuality(items[i].getQuality() + 1);
            }
        }

        items[i].setSellIn((items[i].getSellIn() - 1));

    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {

            if (!AGED_BRIE.equals(items[i].getName())
                    && !SULFURAS_HAND_OF_RAGNAROS.equals(items[i].getName())
                    && !BACKSTAGE_PASSES_TO_CONCERT.equals(items[i].getName())
                    && !CONJURED_MANA_CAKE.equals(items[i].getName())) {

                updateItems(i);
            }

            if (AGED_BRIE.equals(items[i].getName())) {
                updateAgedBrie(i);
            }

            if (CONJURED_MANA_CAKE.equals(items[i].getName())) {
                updateConjured(i);
            }

            if (BACKSTAGE_PASSES_TO_CONCERT.equals(items[i].getName())) {
                updateBackstage(i);
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

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 15; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }

}
