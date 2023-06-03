package com.cointosssimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{

    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;

    @FXML
    private Label AppLogoLabel, headsOrTailsLabel, orLabel, resultsLabel = new Label();

    @FXML
    private Label coinsTossed = new Label(), gamesWon = new Label(), gamesLost = new Label(), headsLanded = new Label(), tailsLanded = new Label();

    static Label resultsStaticLabel = new Label(), coinsTossedLabel = new Label("Coins Tossed: "), gamesWonLabel = new Label("Games Won: "), gamesLostLabel = new Label("Games Lost: "), headsLandedLabel = new Label("Heads Landed On: "), tailsLandedLabel = new Label("Tails Landed On: ");
    // Storing the statistic values so the static labels can be updated, so the non-static FXML labels can be updated when needed.
    static Integer gamesWonValue = 0;
    static Integer gamesLostValue = 0;
    static Integer headsLandedOnValue = 0;
    static Integer tailsLandedOnValue = 0;

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

    protected void gameStatsUpdate() { // Method responsible for updating the statistics page.  All the values on that page will be updated here, outside of the coin-tossed values;
        gamesWonValue = 0;
        gamesLostValue = 0;
        headsLandedOnValue = 0;
        tailsLandedOnValue = 0;
        for(int i = 0; i < CoinTossController.coinSidesRolled.size(); i++) {
            if(Objects.equals(CoinTossController.coinSidesRolled.get(i), CoinTossController.coinSidesSelected.get(i)) && CoinTossController.coinSidesSelected.get(i) != null) {
                gamesWonValue++;
            } else if (!Objects.equals(CoinTossController.coinSidesRolled.get(i), CoinTossController.coinSidesSelected.get(i)) && CoinTossController.coinSidesSelected.get(i) != null){
                gamesLostValue++;
            }
            if(CoinTossController.coinSidesRolled.get(i) == 1) {
                tailsLandedOnValue++;
            } else if (CoinTossController.coinSidesRolled.get(i) == 0) {
                headsLandedOnValue++;
            }
        }
        coinsTossedLabel.setText("Coins Tossed: " + CoinTossController.coinSidesSelected.size());
        gamesWonLabel.setText("Games Won: " + gamesWonValue);
        gamesLostLabel.setText("Games Lost: " + gamesLostValue);
        headsLandedLabel.setText("Heads Landed On: " + headsLandedOnValue);
        tailsLandedLabel.setText("Tails Landed On: " + tailsLandedOnValue);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameStatsUpdate();
        resultsLabel.setText(resultsStaticLabel.getText());
        coinsTossed.setText(coinsTossedLabel.getText());
        headsLanded.setText(headsLandedLabel.getText());
        tailsLanded.setText(tailsLandedLabel.getText());
        gamesWon.setText(gamesWonLabel.getText());
        gamesLost.setText(gamesLostLabel.getText());
    }
}
