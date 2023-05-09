package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.files.Helper;
import project.files.customer.Customer;
import project.files.database.DbHandler;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static project.files.database.DbConst.*;
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
        String entered_login = loginField.getText();
        String entered_pass = passwordField.getText();

        if (!isValidFields(entered_login, entered_pass)) {
            errorText.setText("Fields are empty");
            return;
        }
        try {
            String true_pass = DbHandler.getCustomerColumn(entered_login, "password");

            if (Helper.cypher(entered_pass).equals(true_pass)) {
                setCustomerInfo(entered_login);
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

    private void setCustomerInfo(String entered_login) {
        Customer.id = Integer.valueOf(DbHandler.getCustomerColumn(entered_login, CUSTOMER_ID));
        Customer.login = DbHandler.getCustomerColumn(entered_login, CUSTOMER_LOGIN);
        Customer.firstName = DbHandler.getCustomerColumn(entered_login, CUSTOMER_FIRSTNAME);
        Customer.lastName = DbHandler.getCustomerColumn(entered_login, CUSTOMER_LASTNAME);
        Customer.balance = Double.valueOf(DbHandler.getCustomerColumn(entered_login, CUSTOMER_BALANCE));
    }

    private boolean isValidFields(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) return false;
        return true;
    }

}
