package com.example.harrypotterathomev2;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class BorderPane {
    private Label welcomeText;

    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
