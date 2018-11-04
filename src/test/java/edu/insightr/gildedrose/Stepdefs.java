package edu.insightr.gildedrose;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item[] items;
    private Item sulfuras;
    private Item conjured;

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
        inventory = new Inventory();
        items = inventory.getItems();
        conjured = items[5];
        sulfuras=items[3];
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        assertThat(conjured.getQuality(), is(conjuredQuality));
    }
    @Then("^the quality of the sulfuras item is (\\d+)$")
    public void theQualityOfTheSulfurasIs(int sulfurasQuality) throws Throwable {
        assertThat(sulfuras.getQuality(), is(sulfurasQuality));
    }

    @When("^I update the inventory$")
    public void iUpdateTheInventory() throws Throwable {
        inventory.updateQuality();
    }
    @When("^I update the inventory using factory and strategy Pattern$")
    public void iUpdateTheInventoryBis() throws Throwable {
        UpdateQualityFactory factory=new UpdateQualityFactory();
        for(int i=0;i<inventory.getItems().length;i++){
            Item item=inventory.getItems()[i];
            factory.getUpdateQualityStrategy(item).updateQuality(item);
        }

    }
}