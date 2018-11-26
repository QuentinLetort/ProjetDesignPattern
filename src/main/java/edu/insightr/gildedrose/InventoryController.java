package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {


    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    private TableView<Item> itemTable;
    private Inventory inventory = null;
    @FXML
    private AnchorPane anchorPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);

        Map<String, Integer> objet;
        objet =inventory.quantityPerItem();

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("DEXTERITY_VEST", objet.get(Inventory.DEXTERITY_VEST)),
                        new PieChart.Data("AGED_BRIE", objet.get(Inventory.AGED_BRIE)),
                        new PieChart.Data("ELIXIR_OF_THE_MONGOOSE", objet.get(Inventory.ELIXIR_OF_THE_MONGOOSE)),
                        new PieChart.Data("SULFURAS_HAND_OF_RAGNAROS", objet.get(Inventory.SULFURAS_HAND_OF_RAGNAROS)),
                        new PieChart.Data("BACKSTAGE_PASSES_TO_CONCERT", objet.get(Inventory.BACKSTAGE_PASSES_TO_CONCERT)),
                        new PieChart.Data("CONJURED_MANA_CAKE", objet.get(Inventory.CONJURED_MANA_CAKE)));
        PieChart pieChart = new PieChart(pieChartData);
        anchorPane.getChildren().add(pieChart);
        pieChart.setLayoutX(520.0);
        pieChart.setLayoutY(105.0);

    }

    public void onUpdate() {
        inventory.updateQuality();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
    }

    public void onLoad() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(getClass().getResource("/json").getFile()));
            fc.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                inventory.addItems(inventory.ListItemsFromJSON(selectedFile.getAbsolutePath()));
                itemData.setAll(inventory.getItems());
                itemTable.getItems().setAll(itemData);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
