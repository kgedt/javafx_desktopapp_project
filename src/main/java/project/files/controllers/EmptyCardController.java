package project.files.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmptyCardController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label emptyText;
    @FXML
    void initialize() {

    }
    public void nullText() {
        emptyText.setText("");
    }
}
