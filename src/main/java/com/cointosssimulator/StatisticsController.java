package com.cointosssimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{

    // Initializing variables responsible for switching to main scene.
    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;
    private GlobalStatsHandler statsHandler = new GlobalStatsHandler(); // global stats handler object.

    @FXML
    private Button backToMain;

    @FXML
    private Label localStatsLabel = new Label(), // Local Stats text
            globalStatsLabel = new Label(), // Global Stats text
            coinsTossed = new Label(),
            coinsTossedGlobal = new Label(),
            gamesWon = new Label(),
            gamesWonGlobal = new Label(),
            gamesLost = new Label(),
            gamesLostGlobal = new Label(),
            headsLanded = new Label(),
            headsLandedGlobal = new Label(),
            tailsLanded = new Label(),
            tailsLandedGlobal = new Label();

    // Storing the statistic values so the static labels can be updated,and so the non-static FXML labels can be updated when needed.
    static Label coinsTossedLabel = new Label("Coins Tossed: "),
            gamesWonLabel = new Label("Games Won: "),
            gamesLostLabel = new Label("Games Lost: "),
            headsLandedLabel = new Label("Heads Landed On: "),
            tailsLandedLabel = new Label("Tails Landed On: ");

    static Label coinsTossedGlobalLabel = new Label("Coins Tossed: "),
            gamesWonGlobalLabel = new Label("Games Won: "),
            gamesLostGlobalLabel = new Label("Games Lost: "),
            headsLandedGlobalLabel = new Label("Heads Landed On: "),
            tailsLandedGlobalLabel = new Label("Tails Landed On: ");

    // Initializes Static integers used on this page.
    static Integer gamesWonValue = 0;
    static Integer gamesLostValue = 0;
    static Integer headsLandedOnValue = 0;
    static Integer tailsLandedOnValue = 0;
    static Integer coinsTossedGlobalValue = 0;
    static Integer gamesWonGlobalValue = 0;
    static Integer gamesLostGlobalValue = 0;
    static Integer headsLandedOnGlobalValue = 0;
    static Integer tailsLandedOnGlobalValue = 0;

    public void switchBackToMainScene(ActionEvent event) { // Switches to the main page.  Loads the fxml file setup for it.
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

    protected void gameStatsUpdate() { // Method responsible for updating the statistics page.
        // All the local values on this page will be updated here, which is outside the coin-tossed values;
        gamesWonValue = 0;
        gamesLostValue = 0;
        headsLandedOnValue = 0;
        tailsLandedOnValue = 0;
        for(int i = 0; i < CoinTossController.coinSidesRolled.size(); i++) {
            if(Objects.equals(CoinTossController.coinSidesRolled.get(i),
                    CoinTossController.coinSidesSelected.get(i)) &&
                    CoinTossController.coinSidesSelected.get(i) != null) {
                gamesWonValue++;
            } else if (!Objects.equals(CoinTossController.coinSidesRolled.get(i),
                    CoinTossController.coinSidesSelected.get(i)) &&
                    CoinTossController.coinSidesSelected.get(i) != null){
                gamesLostValue++;
            }
            if(CoinTossController.coinSidesRolled.get(i) == 1) {
                tailsLandedOnValue++;
            } else if (CoinTossController.coinSidesRolled.get(i) == 0) {
                headsLandedOnValue++;
            }
        }
        // Assigning static labels with the corresponding values.
        coinsTossedLabel.setText("Coins Tossed: " + CoinTossController.coinSidesSelected.size());
        gamesWonLabel.setText("Games Won: " + gamesWonValue);
        gamesLostLabel.setText("Games Lost: " + gamesLostValue);
        headsLandedLabel.setText("Heads Landed On: " + headsLandedOnValue);
        tailsLandedLabel.setText("Tails Landed On: " + tailsLandedOnValue);
    }

    public void setGlobalStats() { // Method responsible for assigning importing the global values to this class,
        // and then assigning them to the corresponding static label

        statsHandler.setGlobalGameStats();

        coinsTossedGlobalLabel.setText("Coins Tossed: " + coinsTossedGlobalValue);
        gamesWonGlobalLabel.setText("Games Won: " + gamesWonGlobalValue);
        gamesLostGlobalLabel.setText("Games Lost: " + gamesLostGlobalValue);
        headsLandedGlobalLabel.setText("Heads Landed On: " + headsLandedOnGlobalValue);
        tailsLandedGlobalLabel.setText("Tails Landed On: " + tailsLandedOnGlobalValue);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // Initializes the values of the statistic pages along with the labels.

        setGlobalStats();

        coinsTossedGlobal.setText(coinsTossedGlobalLabel.getText());
        headsLandedGlobal.setText(headsLandedGlobalLabel.getText());
        tailsLandedGlobal.setText(tailsLandedGlobalLabel.getText());
        gamesWonGlobal.setText(gamesWonGlobalLabel.getText());
        gamesLostGlobal.setText(gamesLostGlobalLabel.getText());

        gameStatsUpdate();
        coinsTossed.setText(coinsTossedLabel.getText());
        headsLanded.setText(headsLandedLabel.getText());
        tailsLanded.setText(tailsLandedLabel.getText());
        gamesWon.setText(gamesWonLabel.getText());
        gamesLost.setText(gamesLostLabel.getText());
    }

}
