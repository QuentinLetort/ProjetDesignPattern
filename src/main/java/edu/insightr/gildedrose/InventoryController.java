package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {


    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    private TableView<Item> itemTable;
    private Inventory inventory = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
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
                Item[] inv;
                JSONParser parser = new JSONParser();
                String path = selectedFile.getAbsolutePath();
                Object obj = parser.parse(new FileReader(path));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray items = (JSONArray) jsonObject.get("inventory");
                inv = new Item[items.size()];
                for (int i = 0; i < items.size(); i++) {
                    JSONObject item = (JSONObject) items.get(i);
                    String name = (String) item.get("name");
                    long sellIn = (long) item.get("sellIn");
                    long quality = (long) item.get("quality");
                    inv[i] = new Item(name, (int) sellIn, (int) quality);
                }
                inventory.addItems(inv);
                itemData.setAll(inventory.getItems());
                itemTable.getItems().setAll(itemData);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
