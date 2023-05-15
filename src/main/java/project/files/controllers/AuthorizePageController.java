package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.files.Helper;
import project.files.database.DbHandler;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static project.files.pages.Pages.*;


public class AuthorizePageController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button signInButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Label errorText;
    @FXML
    void registerClick(ActionEvent event) throws IOException {
        Helper.changeScene(registerButton, registerPage);
    }
    @FXML
    void signInClick(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String enteredLogin = loginField.getText();
        String enteredPass = passwordField.getText();

        if (!isValidFields(enteredLogin, enteredPass)) {
            errorText.setText("Fields are empty");
            return;
        }
        try {
            String truePass = DbHandler.getCustomerColumn(enteredLogin, "password");

            if (Helper.cypher(enteredPass).equals(truePass)) {
                Helper.setCustomerInfo(enteredLogin);
                Helper.changeScene(signInButton, userPage);
            } else {
                errorText.setText("Password is incorrect");
            }
        } catch (RuntimeException e) {
            errorText.setText("User doesn't exist");
        }
    }
    @FXML
    void initialize() {

    }
    private boolean isValidFields(String login, String password) {
        return !login.isEmpty() && !password.isEmpty();
    }
}
