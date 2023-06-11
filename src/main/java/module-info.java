module com.example.cointosssimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires mail;


    opens com.cointosssimulator to javafx.fxml;
    exports com.cointosssimulator;
}