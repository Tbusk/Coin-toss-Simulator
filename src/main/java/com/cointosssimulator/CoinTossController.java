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
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class CoinTossController implements Initializable {

    // Basic scene, stage, and root variables used in the project.  The scene is equivalent to a panel.  The Stage is equavalent of a window, the root is equivalent of a node that holds other nodes, which is the fxml file.
    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;
    static String coinSelection;

    // The Arraylists of the project.  coinSidesRolled = what the coins landed on.  coinSidesSelected = what the user chose.  Every coin flip updates those values.
    protected static final ArrayList<Integer> coinSidesRolled = new ArrayList<>();
    protected static final ArrayList<Integer> coinSidesSelected = new ArrayList<>();

    // The buttons used in this project.  There is a start button, the back buttons, a select heads button, a select tails button, and a statistics button.
    @FXML
    private Button startButton, backButton, headsButton, tailsButton, statisticsButton;

    // The labels used in the project.  All of which are for text to be displayed on the screen. The static labels make updating the values of the regular labels possible.
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

    @FXML
    private ImageView imageView; // Used in FXML to display images (required)

    @FXML
    private ComboBox<String> coinList = new ComboBox<>(); // Contains the coins available.  Shown on main screen.

    public void switchToCoinFlipScene(ActionEvent event) { // Switches from the main page to the coin flipping page.  Loads the fxml file setup for it.
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

    public void switchToMainScreenScene(ActionEvent event) { // Switches to the main page.  Loads the fxml file setup for it.
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

    public void switchToStatsScene(ActionEvent event) { // Switches to the stats page.  Loads the fxml file setup for it.
        try {
            coinsTossed(); gameStatsUpdate();
            root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
            mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainScreen = new Scene(root);
            mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
            coinSelection = coinList.getValue();
            mainStage.setScene(mainScreen);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<String> createCoinList() { // Method that is responsible for populating the ComboBox used on the main page.
        return FXCollections.observableArrayList("Quarter","Nickel","Dime");
    }

    /*
    This is the method that is responsible for various tasks including a random element that stores what side of the coin is landed on using Integers and the Random class.
    The Integer is stored in an arraylist called coinSidesRolled.
    Based on which button is clicked, i.e. the heads or the tails button, the results page will display an image of the side of the coin that was landed on based on the coin the user chose.
    Also, on the results page, whether the user chose correctly or not, will be displayed on the page.
    The new page will be opened up and its corresponding fxml file will also be loaded.
     */
    @FXML
    private void headsOrTails(ActionEvent event) {

        // Coin side selection using the Random class.
        Random random = new Random();
        coinSidesRolled.add(random.nextInt(2));

        if(event.getSource()==tailsButton) { // if the tails button is pressed by the user, this will be executed.
            coinSidesSelected.add(1);
            System.out.println("User selected tails.");
            if (coinSidesRolled.get(coinSidesRolled.size()-1) == 1) {
                resultsStaticLabel.setText("You selected tails. You Win!");
                System.out.println("The coin landed on tails.");

            } else {
                resultsStaticLabel.setText("You selected tails. You Lose!");
                System.out.println("The coin landed on heads.");
            }

        }
        if(event.getSource()==headsButton) { // if the heads button is pressed by the user, this will be executed.
            coinSidesSelected.add(0);
            System.out.println("User selected heads.");
            if (coinSidesRolled.get(coinSidesRolled.size()-1) == 1) {
                System.out.println("The coin landed on tails.");
                resultsStaticLabel.setText("You selected heads. You lose!");
            } else {
                System.out.println("The coin landed on heads.");
                resultsStaticLabel.setText("You selected heads. You Win!");
            }
        }
        if(coinSelection.equals("Quarter")){ // If the user chose quarter on the main screen to flip, this will be executed.
                try {
                    if(coinSidesRolled.get(coinSidesRolled.size() - 1) == 0) { // Heads
                        root = FXMLLoader.load(getClass().getResource("Quarter-heads.fxml"));

                    } else { // Tails
                        root = FXMLLoader.load(getClass().getResource("Quarter-tails.fxml"));

                    }
                    mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    mainScreen = new Scene(root);
                    mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
                    mainStage.setScene(mainScreen);
                } catch (Exception e) {
                    System.out.println(e);
                }
        } else if(coinSelection.equals("Nickel")){ // If the user chose nickel on the main screen to flip, this will be executed.
            try {
                if(coinSidesRolled.get(coinSidesRolled.size() - 1) == 0) { // Heads
                    root = FXMLLoader.load(getClass().getResource("Nickel-heads.fxml"));
                } else { // Tails
                    root = FXMLLoader.load(getClass().getResource("Nickel-tails.fxml"));
                }
                mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainScreen = new Scene(root);
                mainScreen.getStylesheets().add(getClass().getResource("CoinTossStyle.css").toExternalForm());
                mainStage.setScene(mainScreen);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if(coinSelection.equals("Dime")){ // If the user chose dime on the main screen to flip, this will be executed.
            try {
                if(coinSidesRolled.get(coinSidesRolled.size() - 1) == 0) { // Heads
                    root = FXMLLoader.load(getClass().getResource("Dime-heads.fxml"));
                } else { // Tails
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

    protected void gameStatsUpdate() { // Method responsible for updating the statistics page.  All the values on that page will be updated here, outside of the coin-tossed values;
        gamesWonValue = 0;
        gamesLostValue = 0;
        headsLandedOnValue = 0;
        tailsLandedOnValue = 0;
        for(int i = 0; i < coinSidesRolled.size(); i++) {
            if(Objects.equals(coinSidesRolled.get(i), coinSidesSelected.get(i)) && coinSidesSelected.get(i) != null) {
                gamesWonValue++;
            } else if (!Objects.equals(coinSidesRolled.get(i), coinSidesSelected.get(i)) && coinSidesSelected.get(i) != null){
                gamesLostValue++;
            }
            if(coinSidesRolled.get(i) == 1) {
                tailsLandedOnValue++;
            } else if (coinSidesRolled.get(i) == 0) {
                headsLandedOnValue++;
            }
        }
        gamesWonLabel.setText("Games Won: " + gamesWonValue);
        gamesLostLabel.setText("Games Lost: " + gamesLostValue);
        headsLandedLabel.setText("Heads Landed On: " + headsLandedOnValue);
        tailsLandedLabel.setText("Tails Landed On: " + tailsLandedOnValue);
    }

    protected void coinsTossed() { // Method responsible for updating the coins-tossed statistic on the statistic page
        coinsTossedLabel.setText("Coins Tossed: " + coinSidesSelected.size());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // Method responsible for initialization of particular values, like the combobox and the stats label values tracked.
        try {
                coinList.getItems().addAll(createCoinList());
                coinList.getSelectionModel().selectFirst();
                resultsLabel.setText(resultsStaticLabel.getText());
                coinsTossed.setText(coinsTossedLabel.getText());
                headsLanded.setText(headsLandedLabel.getText());
                tailsLanded.setText(tailsLandedLabel.getText());
                gamesWon.setText(gamesWonLabel.getText());
                gamesLost.setText(gamesLostLabel.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}