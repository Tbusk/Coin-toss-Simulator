module com.example.cointosssimulator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.cointosssimulator to javafx.fxml;
    exports com.cointosssimulator;
}