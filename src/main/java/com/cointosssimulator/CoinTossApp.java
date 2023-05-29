package com.cointosssimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoinTossApp extends Application {
    @Override
    public void start(Stage stage){ // Sets up the main page.  Loads the css file for styling.  Also loads the main menu fxml file for layout.

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
            stage.setTitle("Coin-toss Simulator");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    } // Launches the app.
}