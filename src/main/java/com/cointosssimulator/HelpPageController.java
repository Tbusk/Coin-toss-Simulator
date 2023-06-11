package com.cointosssimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelpPageController implements Initializable {

    // Initializing variables responsible for switching to main scene.
    private Scene mainScreen;
    private Stage mainStage;
    private Parent root;
    private Dialog<String> diaglogbx = new Dialog<>(); // Diaglog box for too few char entered in body and subject text areas
    private TextInputDialog credentialEntryBox = new TextInputDialog(); // Dialog box for entering password for email

    @FXML
    protected Button sendMessage, backHelpButton;

    @FXML
    protected TextArea subjectTextArea, bodyTextArea;

    @FXML
    protected Label subjectTextLabel, bodyTextLabel;

    public void switchBackToMain(ActionEvent event) { // Switches to the main page.  Loads the fxml file setup for it.
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

    public void sendEmail() { // Sends out email through a burner account with what user types in.  Subject and Message must be greater than 5 characters.
        // A popup will show if the subject and body are not 6 or more characters.
        // Another popup will appear asking user for email password to send email.  This is to prevent hard-coding of email creds.
        try {
            if (subjectTextArea.getText().length() > 5
            && bodyTextArea.getText().length() > 5) {
                Optional<String> result = credentialEntryBox.showAndWait();
                new EmailController("smtp.office365.com", 587, "sentfromide@outlook.com", result.get())
                        // host (SMTP), port, username, password are the fields
                        .sendMail("sentfromide@outlook.com", // From email-address
                                "support@tbusk.com", // To-email address
                                subjectTextArea.getText(), // Email subject text
                                bodyTextArea.getText()); // Email body text
                subjectTextArea.clear();
                bodyTextArea.clear();
            } else {
                diaglogbx.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // When page is loaded, these are initialized.

        /*
         * The cred-entry (password) box is initialized with desired values.
         * Also, the dialog box that pops up if the user tries to send an email with too little characters is initialized here.
         */

        credentialEntryBox.setTitle("Credential Entry");
        credentialEntryBox.setHeaderText("Please enter your email creds");
        credentialEntryBox.setContentText("Password: ");
        diaglogbx.setTitle("Hold your horses!");
        diaglogbx.setContentText("You have to enter at least 5 characters in both the body and subject to send out an email.");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        diaglogbx.getDialogPane().getButtonTypes().add(type);
    }
}
