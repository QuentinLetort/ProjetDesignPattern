package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class InventoryController implements Initializable {


    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    private TableView<Item> itemTable;
    private Inventory inventory = null;
    @FXML
    PieChart pieChart;

    @FXML
    BarChart<String, Integer> barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
        AffichePieChart();

        afficheBarChartItemPerSellin();
    }

    public void onUpdate() {
        inventory.updateQuality();
        itemTable.refresh();
       afficheBarChartItemPerSellin();
    }

    private void AffichePieChart() {
        Map<String, Integer> itemsQuantity = inventory.quantityPerItem();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Dexterity", itemsQuantity.get(Inventory.DEXTERITY_VEST)),
                        new PieChart.Data("Backstage passes", itemsQuantity.get(Inventory.BACKSTAGE_PASSES_TO_CONCERT)),
                        new PieChart.Data("Conjured", itemsQuantity.get(Inventory.CONJURED_MANA_CAKE)),
                        new PieChart.Data("Aged Brie", itemsQuantity.get(Inventory.AGED_BRIE)),
                        new PieChart.Data("Sulfuras", itemsQuantity.get(Inventory.SULFURAS_HAND_OF_RAGNAROS)),
                        new PieChart.Data("Elixir", itemsQuantity.get(Inventory.ELIXIR_OF_THE_MONGOOSE)));
        pieChart.setData(pieChartData);
    }

    public void onLoad() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(getClass().getResource("/json").getFile()));
            fc.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                Item[] newItems = inventory.ListItemsFromJSON(selectedFile.getAbsolutePath());
                inventory.addItems(newItems);
                itemData.addAll(newItems);
                itemTable.getItems().addAll(newItems);
                AffichePieChart();
                afficheBarChartItemPerSellin();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afficheBarChartItemPerSellin(){
        barChart.getData().clear();

        barChart.setTitle("Number of items as a function of the sell in");
        barChart.getXAxis().setLabel("Sell In");
        barChart.getYAxis().setLabel("Number of items");
        Map<Integer, Integer> mapItem;
        mapItem = inventory.quantityPerSellIn();

        Set<Map.Entry<Integer,Integer>> setMapItem = mapItem.entrySet();
        Iterator<Map.Entry<Integer,Integer>> it = setMapItem.iterator();

        XYChart.Series bar = new XYChart.Series();

        bar.setName("items");

        while(it.hasNext()){
            Map.Entry<Integer, Integer> e = it.next();
            bar.getData().add(new XYChart.Data<>(String.valueOf(e.getKey()), e.getValue()));
        }
        System.out.println(bar);
        barChart.getData().add(bar);
    }

}
