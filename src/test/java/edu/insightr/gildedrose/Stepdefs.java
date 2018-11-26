package edu.insightr.gildedrose;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    private Item conjured;
    private Item sulfuras;
    private Item agedBrie;
    private Item backstagePass;
    private Item elixir;
    private Item vest;


    @Given("^I have a new inventory$")
    public void iHaveANewInventory() {
        inventory = new Inventory();
        items = inventory.getItems();
        vest = items[0];
        agedBrie = items[1];
        elixir = items[2];
        sulfuras = items[3];
        backstagePass = items[4];
        conjured = items[5];
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality){
        assertThat(conjured.getQuality(), is(conjuredQuality));
    }

    @Then("^the quality of the sulfuras item is (\\d+)$")
    public void theQualityOfTheSulfurasIs(int sulfurasQuality){
        assertThat(sulfuras.getQuality(), is(sulfurasQuality));
    }

    @Then("^the quality of the backstage pass item is (\\d+)$")
    public void theQualityOfTheBackstagePassIs(int backstagePassQuality){
        assertThat(backstagePass.getQuality(), is(backstagePassQuality));
    }

    @Then("^the quality of the elixir item is (\\d+)$")
    public void theQualityOfTheElixirIs(int elixirQuality){
        assertThat(elixir.getQuality(), is(elixirQuality));
    }

    @Then("^the quality of the vest item is (\\d+)$")
    public void theQualityOfTheVestIs(int vestQuality){
        assertThat(vest.getQuality(), is(vestQuality));
    }

    @Then("^the quality of the aged brie item is (\\d+)$")
    public void theQualityOfTheAgedBrieIs(int agedBrieQuality){
        assertThat(agedBrie.getQuality(), is(agedBrieQuality));
    }
    @Then("^the quality value of an item is over (\\d+) and under (\\d+)$")
    public void theQualityValueOfAnItemIsBetween(int minValue, int maxValue){
        for (Item item : items) {
            if (!Inventory.SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())) {
                assertTrue(item.getQuality() >= minValue && item.getQuality() <= maxValue);
            }
        }
    }
    @When("^I update the inventory (\\d+) times$")
    public void iUpdateTheInventoryNTimes(int nbTimes){
        for (int i = 0; i < nbTimes; i++) {
            inventory.updateQuality();
        }
    }
    @When("^I update the inventory$")
    public void iUpdateTheInventory(){
        inventory.updateQuality();
    }
}