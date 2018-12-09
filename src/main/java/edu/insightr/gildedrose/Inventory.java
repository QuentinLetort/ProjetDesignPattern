package edu.insightr.gildedrose;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Inventory {

    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    private Item[] items;

    public Inventory() {
        super();
        items = ListItemsFromJSON(getClass().getResource("/json/initialInventory.json").getFile());

    }


    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Item[] ListItemsFromJSON(String path) {
        Item[] inv = null;
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray inventory = (JSONArray) jsonObject.get("inventory");
            inv = new Item[inventory.size()];
            for (int i = 0; i < inventory.size(); i++) {
                JSONObject item = (JSONObject) inventory.get(i);
                String name = (String) item.get("name");
                long sellIn = (long) item.get("sellIn");
                long quality = (long) item.get("quality");
                String creationdate = (String) item.get("creationdate");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale( Locale.FRENCH );
                LocalDate date = LocalDate.parse(creationdate, formatter);

                inv[i] = new Item(name, (int) sellIn, (int) quality, date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inv;
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

    public void addItems(Item[] items) {
        Item[] newItems = new Item[this.items.length + items.length];
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        System.arraycopy(items, 0, newItems, this.items.length, items.length);
        this.items = newItems;

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

    public Map<String, Integer> quantityPerItem() {
        Map<String, Integer> quantity = new HashMap<>();
        int[] compteurs = new int[6];

        for (Item item : items) {
            switch (item.getName()) {
                case DEXTERITY_VEST:
                    compteurs[0]++;
                    break;
                case AGED_BRIE:
                    compteurs[1]++;
                    break;
                case ELIXIR_OF_THE_MONGOOSE:
                    compteurs[2]++;
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    compteurs[3]++;
                    break;
                case BACKSTAGE_PASSES_TO_CONCERT:
                    compteurs[4]++;
                    break;
                case CONJURED_MANA_CAKE:
                    compteurs[5]++;
                    break;
            }
        }
        quantity.put(DEXTERITY_VEST, compteurs[0]);
        quantity.put(AGED_BRIE, compteurs[1]);
        quantity.put(ELIXIR_OF_THE_MONGOOSE, compteurs[2]);
        quantity.put(SULFURAS_HAND_OF_RAGNAROS, compteurs[3]);
        quantity.put(BACKSTAGE_PASSES_TO_CONCERT, compteurs[4]);
        quantity.put(CONJURED_MANA_CAKE, compteurs[5]);

        return quantity;

    }

    public void buyItem(String nameItem, int sellinItem, int qualityItem, LocalDate dateItem) {
        Item itemBought = new Item(nameItem,sellinItem,qualityItem,dateItem);
        Item[] inv = new Item[this.items.length + 1];
        System.arraycopy(this.items, 0, inv, 0, this.items.length);
        System.arraycopy(itemBought, 0, inv, this.items.length, 1);
        this.items = inv;
    }

    public void sellItem(Item itemName) {
        Item[] inv = new Item[this.items.length - 1];
        int index = 0;
        try {
            while (!itemName.equals(this.items[index]) || index > this.items.length) {
                    inv[index] = this.items[index];
                    index++;
            }
            System.arraycopy(this.items, index + 1, inv, index, this.items.length - (index +1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
