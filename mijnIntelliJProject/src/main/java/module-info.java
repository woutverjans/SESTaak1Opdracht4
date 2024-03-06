module com.kuleuven.mijnintellijproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kuleuven.mijnintellijproject to javafx.fxml;
    exports com.kuleuven.mijnintellijproject;
}