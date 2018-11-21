package edu.insightr.gildedrose;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    private Item vest;
    private Item conjured;
    private Item sulfuras;
    private Item agedBrie;
    private Item backstagePass;
    private Item elixir;


    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
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
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        assertThat(conjured.getQuality(), is(conjuredQuality));
    }

    @Then("^the quality of the sulfuras item is (\\d+)$")
    public void theQualityOfTheSulfurasIs(int sulfurasQuality) throws Throwable {
        assertThat(sulfuras.getQuality(), is(sulfurasQuality));
    }

    @Then("^the quality of the backstage pass item is (\\d+)$")
    public void theQualityOfTheBackstagePassIs(int backstagePassQuality) throws Throwable {
        assertThat(backstagePass.getQuality(), is(backstagePassQuality));
    }

    @Then("^the quality of the elixir item is (\\d+)$")
    public void theQualityOfTheElixirIs(int elixirQuality) throws Throwable {
        assertThat(elixir.getQuality(), is(elixirQuality));
    }

    @Then("^the quality of the vest item is (\\d+)$")
    public void theQualityOfTheVestIs(int vestQuality) throws Throwable {
        assertThat(vest.getQuality(), is(vestQuality));
    }

    @Then("^the quality of the aged brie item is (\\d+)$")
    public void theQualityOfTheAgedBrieIs(int agedBrieQuality) throws Throwable {
        assertThat(agedBrie.getQuality(), is(agedBrieQuality));
    }

    @When("^I update the inventory (\\d+) times$")
    public void iUpdateTheInventory2() throws Throwable {
        inventory.updateQuality();
    }
}