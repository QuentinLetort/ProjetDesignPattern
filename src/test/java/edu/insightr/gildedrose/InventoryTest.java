package edu.insightr.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InventoryTest {
    // TODO (PBZ) : unit testing is about testing a method not a behaviour. It's not tes case of the tests below. Instead of JUnit use Cucumber to perform behaviour tests.

    //Test sur updateQuality pour vérifier que la qualité du "Sulfuras" n'a pas été modifié
    @Test
    public void updateQualitySulfuras() {
        Inventory inventory = new Inventory(new Item[]{new Item(Inventory.SULFURAS_HAND_OF_RAGNAROS, 0, 80, null)});
        for (int i = 0; i < 50; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(80, inventory.getItems()[0].getQuality());
    }

    //Test sur updateQuality pour vérifier la qualité du "Aged Brie" à moyen et long terme
    @Test
    public void updateQualityBrie() {
        Inventory inventory = new Inventory(new Item[]{new Item(Inventory.AGED_BRIE, 2, 0,null)});
        int initialQuality = inventory.getItems()[0].getQuality();
        for (int i = 0; i < 15; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality + 15, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 40; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(50, inventory.getItems()[0].getQuality());
    }

    //Test sur updateQuality pour vérifier la qualité d'un objet basique avant la date SellIn puis après
    @Test
    public void updateQualityBasicObject() {
        Inventory inventory = new Inventory(new Item[]{new Item(Inventory.DEXTERITY_VEST, 10, 20, null)});
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(10, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 3; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(4, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 2; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(0, inventory.getItems()[0].getQuality());

    }

    @Test
    public void updateQualityConjured() {
        Inventory inventory = new Inventory(new Item[]{new Item(Inventory.CONJURED_MANA_CAKE, 3, 18, null)});
        for (int i = 0; i < 3; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(12, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 3; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(0, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(0, inventory.getItems()[0].getQuality());
    }

    @Test
    public void updateQualityBackstagePasses() {
        Inventory inventory = new Inventory(new Item[]{new Item(Inventory.BACKSTAGE_PASSES_TO_CONCERT, 13, 5, null)});
        for (int i = 0; i < 3; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(8, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 5; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(18, inventory.getItems()[0].getQuality());
        for (int i = 0; i < 5; i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(33, inventory.getItems()[0].getQuality());
        inventory.updateQuality();
        Assert.assertEquals(0, inventory.getItems()[0].getQuality());
    }


}