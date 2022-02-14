module com.creseliana.cglab {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.creseliana.cglab to javafx.fxml;
    exports com.creseliana.cglab;
    exports com.creseliana.cglab.model;
    opens com.creseliana.cglab.model to javafx.fxml;
}