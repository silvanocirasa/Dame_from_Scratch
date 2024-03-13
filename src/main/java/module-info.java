module com.example.dame_from_scratch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dame_from_scratch to javafx.fxml;
    exports com.example.dame_from_scratch;
    exports com.example.dame_from_scratch.view;
    opens com.example.dame_from_scratch.view to javafx.fxml;
    exports com.example.dame_from_scratch.model;
    opens com.example.dame_from_scratch.model to javafx.fxml;
    exports com.example.dame_from_scratch.controller;
    opens com.example.dame_from_scratch.controller to javafx.fxml;
}