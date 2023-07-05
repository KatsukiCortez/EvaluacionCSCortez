module org.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires openjpa.all;
    
    opens org.demo to javafx.fxml;
    exports org.demo;
}
