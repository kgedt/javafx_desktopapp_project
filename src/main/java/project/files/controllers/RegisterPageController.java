package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.files.Helper;
import project.files.database.DbHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static project.files.pages.Pages.*;


public class RegisterPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorText;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    void backClick() throws IOException {
        Helper.changeScene(backButton, authorizePage);
    }

    @FXML
    void registerClick(ActionEvent event) {
        String login = loginField.getText().strip();
        String password = passwordField.getText().strip();
        String firstName = firstnameField.getText().strip();
        String lastName = lastnameField.getText().strip();

        if (isValidFields(login, password, firstName, lastName)) {
            DbHandler.addCustomer(login, password, 0.0, firstName, lastName);
        }
    }


    @FXML
    void initialize() {

    }

    boolean isValidFields(String login, String password, String firstName, String lastName) {
        if (!login.matches("[A-Za-z0-9]{3,20}")) {
            errorText.setText("Only Latin characters and numbers\n" +
                    "Length is 3-20 characters");
        } else if (password.isEmpty()) {
            errorText.setText("Password is empty");
        } else if (!firstName.matches("[A-Z][a-z]{2,20}") || !lastName.matches("[A-Z][a-z]{2,20}")) {
            errorText.setText("The first letter of the name is capitalized\n" +
                    "Latin characters only\n" +
                    "Length is 3-20 characters");
        } else return true;
        return false;
    }


}
