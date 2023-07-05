module org.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.demo to javafx.fxml;
    exports org.demo;
}
