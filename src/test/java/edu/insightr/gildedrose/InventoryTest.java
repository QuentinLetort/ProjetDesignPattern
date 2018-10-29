package edu.insightr.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    //Test sur updateQuality pour vérifier que la qualité ne descend jamais en dessous de 0
    // ou ne monte au dessus de 50 (excepté pour le "Sulfuras")
    @Test
    void updateQualityLimit(){
        boolean verify=true;
        Inventory inventory=new Inventory();
        for(int i=0;i<50;i++){
            inventory.updateQuality();
        }
        for(int i=0;i<inventory.getItems().length;i++){
            if(inventory.getItems()[i].getName()!="Sulfuras, Hand of Ragnaros"){
                if(inventory.getItems()[i].getQuality()>50 || inventory.getItems()[i].getQuality()<0 ){
                    verify=false;
                }
            }
        }
        Assertions.assertTrue(verify);
    }

    //Test sur updateQuality pour vérifier que la qualité du "Sulfuras" n'a pas été modifié
    @Test
    void updateQualitySulfuras(){
        Inventory inventory=new Inventory(new Item[]{ new Item("Sulfuras, Hand of Ragnaros", 0, 80)});
        for(int i=0;i<50;i++) {
            inventory.updateQuality();
        }

        Assertions.assertEquals(80,inventory.getItems()[0].getQuality());
    }

    //Test qui compare la methode initiale updateQuality() avec la nouvelle methode updateQualityBis()
    // sur des objets basiques (sans contrainte particulière) et identiques
    // pour verifier qu'ils suivent bien la même progression sur du court terme (
    @Test
    void updateQualityBasicItemsShortTerm() {
        Item itemTest1[] = new Item[]{  new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Item itemTest2[] = new Item[]{    new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Inventory inventory1=new Inventory(itemTest1);
        Inventory inventory2=new Inventory(itemTest2);
        for(int i=0;i<3;i++)
        {
            inventory1.updateQualityBis();
            inventory2.updateQuality();
        }
        Assertions.assertEquals(inventory2,inventory1);
    }
    //Même test que précedemment mais sur une durée plus longue
    @Test
    void updateQualityBasicItemsLongTerm() {
        Item itemTest1[] = new Item[]{  new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Item itemTest2[] = new Item[]{    new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Inventory inventory1=new Inventory(itemTest1);
        Inventory inventory2=new Inventory(itemTest2);
        for(int i=0;i<12;i++)
        {
            inventory1.updateQualityBis();
            inventory2.updateQuality();
        }
        Assertions.assertEquals(inventory2,inventory1);
    }
    @Test
    void updateQualityBrieCourtTerm() {
        Item itemTest1[] = new Item[]{
                new Item("Aged Brie", 2, 0)};
        Item itemTest2[] = new Item[]{
                new Item("Aged Brie", 2, 0)};
        Inventory inventory1=new Inventory(itemTest1);
        Inventory inventory2=new Inventory(itemTest2);
        for(int i=0;i<10;i++)
        {
            inventory1.updateQualityBis();
            inventory2.updateQuality();
        }
        Assertions.assertEquals(inventory2,inventory1);
    }
    @Test
    void updateQualityBrieLongTerm() {
        Item itemTest1[] = new Item[]{  new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Item itemTest2[] = new Item[]{    new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)};
        Inventory inventory1=new Inventory(itemTest1);
        Inventory inventory2=new Inventory(itemTest2);
        for(int i=0;i<12;i++)
        {
            inventory1.updateQualityBis();
            inventory2.updateQuality();
        }
        Assertions.assertEquals(inventory2,inventory1);
    }


    @Test
    void updateQualityAllItemsExceptConjured() {
        Item itemTest1[] = new Item[]{  /*new Item("+5 Dexterity Vest", 10, 20),*/
                new Item("Aged Brie", 2, 0)/*,
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)*/};
        Item itemTest2[] = new Item[]{    /*new Item("+5 Dexterity Vest", 10, 20),*/
                        new Item("Aged Brie", 2, 0)/*,
                        new Item("Elixir of the Mongoose", 5, 7),
                        new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)*/};
        Inventory inventory1=new Inventory(itemTest1);
        Inventory inventory2=new Inventory(itemTest2);
        for(int i=0;i<10;i++)
        {
            inventory1.updateQualityBis();
            inventory2.updateQuality();
        }
        Assertions.assertEquals(inventory2,inventory1);
    }
}