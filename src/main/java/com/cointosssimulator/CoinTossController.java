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
import java.util.Random;
import java.util.ResourceBundle;

public class CoinTossController implements Initializable {

    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;
    static String coinSelection;

    protected ArrayList<Integer> results = new ArrayList<>();
    protected ArrayList<Integer> selection = new ArrayList<>();

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
            coinSelection = coinList.getValue();
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
        ObservableList<String> coins = FXCollections.observableArrayList("Quarter","Nickel","Dime");
        return coins;
    }

    @FXML
    private void headsOrTails(ActionEvent event) {
        Random random = new Random();
        results.add(random.nextInt(2));
        if(event.getSource()==tailsButton) {
            selection.add(1);
            System.out.println("User selected tails.");
            if (results.get(results.size()-1) == 1) {
                System.out.println("The coin landed on tails.");
            } else {
                System.out.println("The coin landed on heads.");
            }

        }
        if(event.getSource()==headsButton) {
            selection.add(0);
            System.out.println("User selected heads.");
            if (results.get(results.size()-1) == 1) {
                System.out.println("The coin landed on tails.");
            } else {
                System.out.println("The coin landed on heads.");
            }
        }
        if(coinSelection.equals("Quarter")){
                try {
                    if(results.get(results.size() - 1) == 0) { // Heads
                        root = FXMLLoader.load(getClass().getResource("Quarter-heads.fxml"));
                    } else {
                        root = FXMLLoader.load(getClass().getResource("Quarter-tails.fxml"));
                    }
                    mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainScreen = new Scene(root);
                    mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
                    mainStage.setScene(mainScreen);
                } catch (Exception e) {
                    System.out.println(e);
                }
        } else if(coinSelection.equals("Nickel")){
            try {
                if(results.get(results.size() - 1) == 0) { // Heads
                    root = FXMLLoader.load(getClass().getResource("Nickel-heads.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("Nickel-tails.fxml"));
                }
                mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainScreen = new Scene(root);
                mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
                mainStage.setScene(mainScreen);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if(coinSelection.equals("Dime")){
            try {
                if(results.get(results.size() - 1) == 0) { // Heads
                    root = FXMLLoader.load(getClass().getResource("Dime-heads.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("Dime-tails.fxml"));
                }
                mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainScreen = new Scene(root);
                mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
                mainStage.setScene(mainScreen);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
                coinList.getItems().addAll(createCoinList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}