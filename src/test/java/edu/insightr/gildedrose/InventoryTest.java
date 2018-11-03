package edu.insightr.gildedrose;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

    //Test sur updateQuality pour vérifier que la qualité ne descend jamais en dessous de 0
    // ou ne monte au dessus de 50 (excepté pour le "Sulfuras")
    @Test
    public void updateQualityLimit(){
        boolean verify=true;
        Inventory inventory=new Inventory();
        for(int i=0;i<60;i++){
            inventory.updateQuality();
        }
        for(int i=0;i<inventory.getItems().length;i++){
            if(inventory.getItems()[i].getName()!=Inventory.SULFURAS_HAND_OF_RAGNAROS){
                if(inventory.getItems()[i].getQuality()>50 || inventory.getItems()[i].getQuality()<0 ){
                    verify=false;
                }
            }
        }
        Assert.assertTrue(verify);
    }

    //Test sur updateQuality pour vérifier que la qualité du "Sulfuras" n'a pas été modifié
    @Test
    public void updateQualitySulfuras(){
        Inventory inventory=new Inventory(new Item[]{ new Item(Inventory.SULFURAS_HAND_OF_RAGNAROS, 0, 80)});
        for(int i=0;i<50;i++) {
            inventory.updateQuality();
        }
        Assert.assertEquals(80,inventory.getItems()[0].getQuality());
    }

    //Test sur updateQuality pour vérifier la qualité du "Aged Brie" à moyen et long terme
    @Test
    public void updateQualityBrie(){
        Inventory inventory=new Inventory(new Item[]{ new Item(Inventory.AGED_BRIE, 2, 0)});
        int initialQuality=inventory.getItems()[0].getQuality();
        for(int i=0;i<15;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality+15,inventory.getItems()[0].getQuality());
        for(int i=0;i<40;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(50,inventory.getItems()[0].getQuality());
    }
    //Test sur updateQuality pour vérifier la qualité d'un objet basique avant la date SellIn puis après
    @Test
    public void updateQualityBasicObject(){
        Inventory inventory=new Inventory(new Item[]{new Item(Inventory.DEXTERITY_VEST, 10, 20)});
        int initialQuality=inventory.getItems()[0].getQuality();
        for(int i=0;i<10;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality-10,inventory.getItems()[0].getQuality());
        for(int i=0;i<3;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality-10-3*2,inventory.getItems()[0].getQuality());
        for(int i=0;i<2;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(0,inventory.getItems()[0].getQuality());

    }
    @Test
    public void updateQualityConjured(){
        Inventory inventory=new Inventory(new Item[]{new Item(Inventory.CONJURED_MANA_CAKE, 3, 18)});
        int initialQuality=inventory.getItems()[0].getQuality();
        for(int i=0;i<3;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality-3*2,inventory.getItems()[0].getQuality());
        for(int i=0;i<3;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality-3*2-3*4,inventory.getItems()[0].getQuality());
        for(int i=0;i<10;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(0,inventory.getItems()[0].getQuality());
    }
    @Test
    public void updateQualityBackstagePasses(){
        Inventory inventory=new Inventory(new Item[]{new Item(Inventory.BACKSTAGE_PASSES_TO_CONCERT, 13, 5)});
        int initialQuality=inventory.getItems()[0].getQuality();
        for(int i=0;i<3;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality+3,inventory.getItems()[0].getQuality());
        for(int i=0;i<5;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality+3+5*2,inventory.getItems()[0].getQuality());
        for(int i=0;i<5;i++){
            inventory.updateQuality();
        }
        Assert.assertEquals(initialQuality+3+5*2+5*3,inventory.getItems()[0].getQuality());
        inventory.updateQuality();
        Assert.assertEquals(0,inventory.getItems()[0].getQuality());
    }




}