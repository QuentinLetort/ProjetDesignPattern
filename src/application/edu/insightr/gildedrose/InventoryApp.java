package edu.insightr.gildedrose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryApp extends Application {
    // TODO (PBZ) : the appliation is not at the right place. Maven doesn't accept it, read the Maven directory layout rules...
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // TODO (PBZ) : the fxml file is not stored at the right place. Read the Maven Directory layout rules...
            Parent root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
            Scene scene = new Scene(root,550,670);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
