package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryController implements Initializable {

    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    ChoiceBox choiceBoxName;
    @FXML
    TextField textFieldSellIn;
    @FXML
    TextField textFieldQuality;
    @FXML
    DatePicker datePickerCreationdate;
    @FXML
    Label labelNom;

    @FXML
    Label labelQuality;

    @FXML
    Label labelSellIn;

    @FXML
    Label labelCreationDate;

    @FXML
    Button buttonBuy;
    @FXML
    ToggleButton toggleInventory;

    @FXML
    ToggleButton toggleTransactions;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    Label dateOfTheDay;

    @FXML
    PieChart pieChart;
    @FXML
    private Button buttonSell;

    @FXML
    BarChart<String, Number> barChartTransaction;

    @FXML
    BarChart<String, Number> barChartSellIn;

    @FXML
    BarChart<String, Number> barChartDate;
    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();
    private Inventory inventory = null;
    private Register register = null;
    private LocalDate date;

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    Label ErrorMess;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
        register = new Register();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.FRENCH);
        date = LocalDate.parse("2018-12-14", formatter);
        dateOfTheDay.setText(date.toString());
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
        transactionData.setAll(register.getTransactions());
        transactionTable.getItems().setAll(transactionData);
        AffichePieChart();
        afficheBarChartItemPerSellin();
        afficheBarChartItemPerDate();
        afficheBarChartTransactionPerDay();
        itemTable.getSelectionModel().selectedItemProperty().addListener(e -> buttonSell.setDisable(false));
    }

    public void onUpdate() {
        date = date.plusDays(1);
        dateOfTheDay.setText(date.toString());
        inventory.updateQuality();
        afficheBarChartItemPerSellin();
        itemTable.refresh();
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
                afficheBarChartItemPerDate();
                afficheBarChartTransactionPerDay();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afficheBarChartItemPerSellin() {

        barChartSellIn.setAnimated(false);
        barChartSellIn.setCategoryGap(10.0);
        barChartSellIn.getXAxis().setLabel("Sell In");
        barChartSellIn.getYAxis().setLabel("Number of items");
        barChartSellIn.setTitle("Number of items as a function of the sell in");
        Map<Integer, Integer> mapItem;
        mapItem = inventory.quantityPerSellIn();

        Set<Map.Entry<Integer, Integer>> setMapItem = mapItem.entrySet();
        Iterator<Map.Entry<Integer, Integer>> it = setMapItem.iterator();

        XYChart.Series bar = new XYChart.Series();

        bar.setName("items");

        while (it.hasNext()) {
            Map.Entry<Integer, Integer> e = it.next();
            bar.getData().add(new XYChart.Data<>(String.valueOf(e.getKey()), (Number) e.getValue()));
        }


        bar.getData().sort(new Comparator<XYChart.Data>() {
            @Override
            public int compare(XYChart.Data o1, XYChart.Data o2) {

                Integer xValue1 = Integer.valueOf((String) o1.getXValue());
                Integer xValue2 = Integer.valueOf((String) o2.getXValue());

                return xValue1.compareTo(xValue2);
            }
        });


        barChartSellIn.setData(FXCollections.observableArrayList(bar));
    }


    private void afficheBarChartItemPerDate() {

        barChartDate.setAnimated(false);
        barChartDate.setCategoryGap(10.0);
        barChartDate.getXAxis().setLabel("Creation Date");
        barChartDate.getYAxis().setLabel("Number of items");
        barChartDate.setTitle("Number of items as a function of the creation date");
        Map<LocalDate, Integer> mapItem;
        mapItem = inventory.quantityPerDate();

        Set<Map.Entry<LocalDate, Integer>> setMapItem = mapItem.entrySet();
        Iterator<Map.Entry<LocalDate, Integer>> it = setMapItem.iterator();

        XYChart.Series bar = new XYChart.Series();

        bar.setName("items");

        while (it.hasNext()) {
            Map.Entry<LocalDate, Integer> e = it.next();
            bar.getData().add(new XYChart.Data<>(String.valueOf(e.getKey()), (Number) e.getValue()));
        }

        bar.getData().sort(new Comparator<XYChart.Data>() {
            @Override
            public int compare(XYChart.Data o1, XYChart.Data o2) {

                LocalDate xValue1 = LocalDate.parse((String) o1.getXValue());
                LocalDate xValue2 = LocalDate.parse((String) o2.getXValue());

                return xValue1.compareTo(xValue2);
            }
        });

        barChartDate.setData(FXCollections.observableArrayList(bar));

    }

    private void afficheBarChartTransactionPerDay() {

        barChartTransaction.setAnimated(false);
        barChartTransaction.setCategoryGap(10.0);
        barChartTransaction.getXAxis().setLabel("Date of Transaction");
        barChartTransaction.getYAxis().setLabel("Number of items");
        barChartTransaction.setTitle("Number of items as a function of the date of transaction");


        Map<LocalDate, Integer> mapOfSoldItems = register.mapOfItemsInFunctionOfTransaction(Transaction.Transactiontype.sell);
        Map<LocalDate, Integer> mapOfPurchasedItems = register.mapOfItemsInFunctionOfTransaction(Transaction.Transactiontype.buy);

        Set<Map.Entry<LocalDate, Integer>> setMapItem1 = mapOfSoldItems.entrySet();
        Iterator<Map.Entry<LocalDate, Integer>> it1 = setMapItem1.iterator();

        Set<Map.Entry<LocalDate, Integer>> setMapItem2 = mapOfPurchasedItems.entrySet();
        Iterator<Map.Entry<LocalDate, Integer>> it2 = setMapItem2.iterator();

        XYChart.Series barSell = new XYChart.Series();
        XYChart.Series barPurchased = new XYChart.Series();

        barSell.setName("Sell");
        barPurchased.setName("Purchased");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        while (it1.hasNext()) {
            Map.Entry<LocalDate, Integer> e = it1.next();
            String date = e.getKey().format(formatter);
            barSell.getData().add(new XYChart.Data<>(date, (Number) e.getValue()));
        }

        while (it2.hasNext()) {
            Map.Entry<LocalDate, Integer> e = it2.next();
            String date = e.getKey().format(formatter);
            barPurchased.getData().add(new XYChart.Data<>(date, (Number) e.getValue()));
        }

        barChartTransaction.setData(FXCollections.observableArrayList(barSell, barPurchased));

    }

    public void onSell() {
        Item itemSell = itemTable.getSelectionModel().getSelectedItem();
        inventory.sellItem(itemTable.getSelectionModel().getSelectedItem());
        itemTable.getItems().remove(itemSell);
        Transaction transaction = new Transaction(itemSell, date, Transaction.Transactiontype.sell);
        register.addTransactions(transaction);
        transactionTable.getItems().add(transaction);
        AffichePieChart();
        afficheBarChartItemPerSellin();
        afficheBarChartItemPerDate();
        afficheBarChartTransactionPerDay();
    }

    private boolean ValidationFields() {
        boolean res = true;
        try {
            boolean nameIsPresent = !choiceBoxName.getSelectionModel().isEmpty();
            int sellIn = Integer.parseInt(textFieldSellIn.getText());
            int quality = Integer.parseInt(textFieldQuality.getText());
            LocalDate datecreation = datePickerCreationdate.getValue();
            if (!nameIsPresent || quality > 50 || quality < 0 || datecreation.isAfter(date)) {
                res = false;
            }
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    public void onBuy() {
        try {
            ErrorMess.setVisible(false);
            System.out.println(ValidationFields());
            if (ValidationFields()) {
                int sellIn = Integer.parseInt(textFieldSellIn.getText());
                int quality = Integer.parseInt(textFieldQuality.getText());
                LocalDate datecreation = datePickerCreationdate.getValue();
                String name = "";
                switch (choiceBoxName.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        name = Inventory.AGED_BRIE;
                        break;
                    case 1:
                        name = Inventory.BACKSTAGE_PASSES_TO_CONCERT;
                        break;
                    case 2:
                        name = Inventory.CONJURED_MANA_CAKE;
                        break;
                    case 3:
                        name = Inventory.DEXTERITY_VEST;
                        break;
                    case 4:
                        name = Inventory.ELIXIR_OF_THE_MONGOOSE;
                        break;
                    case 5:
                        name = Inventory.SULFURAS_HAND_OF_RAGNAROS;
                        break;
                }
                Item itemBought = new Item(name, sellIn, quality, datecreation);
                inventory.buyItem(itemBought);
                itemTable.getItems().add(itemBought);
                Transaction transaction = new Transaction(itemBought, date, Transaction.Transactiontype.buy);
                register.addTransactions(transaction);
                transactionTable.getItems().add(transaction);
                AffichePieChart();
                afficheBarChartItemPerSellin();
                afficheBarChartItemPerDate();
                afficheBarChartTransactionPerDay();
            } else {
                ErrorMess.setVisible(true);
            }
            choiceBoxName.getSelectionModel().clearSelection();
            textFieldSellIn.setText("");
            textFieldQuality.setText("");
            datePickerCreationdate.setValue(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onInventory() {
        toggleTransactions.setSelected(false);
        toggleInventory.setSelected(true);
        choiceBoxName.setVisible(false);
        textFieldQuality.setVisible(false);
        textFieldSellIn.setVisible(false);
        datePickerCreationdate.setVisible(false);
        pieChart.setVisible(true);
        buttonSell.setVisible(false);
        barChartDate.setVisible(true);
        barChartSellIn.setVisible(true);
        barChartTransaction.setVisible(false);
        transactionTable.setVisible(false);
        ErrorMess.setVisible(false);
        labelNom.setVisible(false);
        labelSellIn.setVisible(false);
        labelCreationDate.setVisible(false);
        labelQuality.setVisible(false);
        buttonBuy.setVisible(false);
    }

    public void onTransactions() {
        toggleTransactions.setSelected(true);
        toggleInventory.setSelected(false);
        choiceBoxName.setVisible(true);
        textFieldQuality.setVisible(true);
        textFieldSellIn.setVisible(true);
        datePickerCreationdate.setVisible(true);
        pieChart.setVisible(false);
        buttonSell.setVisible(true);
        barChartDate.setVisible(false);
        barChartSellIn.setVisible(false);
        barChartTransaction.setVisible(true);
        transactionTable.setVisible(true);
        ErrorMess.setVisible(false);
        labelNom.setVisible(true);
        labelSellIn.setVisible(true);
        labelCreationDate.setVisible(true);
        labelQuality.setVisible(true);
        buttonBuy.setVisible(true);
    }
}