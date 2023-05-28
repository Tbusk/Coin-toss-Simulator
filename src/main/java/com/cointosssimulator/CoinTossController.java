package com.cointosssimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoinTossController implements Initializable {

    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;

    @FXML
    private Button startButton, backButton, headsButton, tailsButton;

    @FXML
    private Label AppLogoLabel, headsOrTailsLabel, orLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private ComboBox<String> coinList = new ComboBox<>();

    public void switchToCoinFlipScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("coin-toss.fxml"));
            mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainScreen = new Scene(root);
            mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
            mainStage.setScene(mainScreen);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void switchToMainScreenScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainScreen = new Scene(root);
            mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
            mainStage.setScene(mainScreen);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<String> createCoinList() {
        ObservableList<String> coins = FXCollections.observableArrayList("Quarter","Nickel","Dime", "Penny");
        return coins;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(coinList.getItems() != null) {
                coinList.getItems().addAll(createCoinList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}