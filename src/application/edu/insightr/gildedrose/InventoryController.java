package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    @FXML
    private Button btnUpdate;

    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item,String> itemName;
    @FXML
    private TableColumn<Item,String> itemSellIn;
    @FXML
    private TableColumn<Item,String>  itemQuality;
    private Inventory inventory=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            inventory=new Inventory();
            itemData.setAll(inventory.getItems());
            itemTable.getItems().setAll(itemData);
    }

    public void onUpdate(ActionEvent actionEvent) {
        inventory.updateQuality();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
    }
}
