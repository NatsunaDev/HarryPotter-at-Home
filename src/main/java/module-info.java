module com.example.harrypotterathomev2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harrypotterathomev2 to javafx.fxml;
    exports com.example.harrypotterathomev2;
}