package project.files;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.files.customer.Customer;
import project.files.customer.Order;
import project.files.customer.Product;
import project.files.database.DbHandler;
import project.files.final_project.StartApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static project.files.database.DbConst.*;

public class Helper {
    private Helper() {}
    public static String cypher(String pass) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[] arr = msg.digest(pass.getBytes(StandardCharsets.UTF_8));
        StringBuilder res = new StringBuilder();

        for (var i : arr) {
            res.append(String.format("%02x", i));
        }

        return res.toString();
    }

    public static void changeScene(Button clickedButton, String fxmlFilename) throws IOException {
        Stage stage = StartApplication.app_stage;

        clickedButton.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(StartApplication.class.getResource(fxmlFilename));

        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();

        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.show();
    }

    public static void setCustomerInfo(String enteredLogin) {
        Customer.id = Integer.valueOf(DbHandler.getCustomerColumn(enteredLogin, CUSTOMER_ID));
        Customer.login = DbHandler.getCustomerColumn(enteredLogin, CUSTOMER_LOGIN);
        Customer.firstName = DbHandler.getCustomerColumn(enteredLogin, CUSTOMER_FIRSTNAME);
        Customer.lastName = DbHandler.getCustomerColumn(enteredLogin, CUSTOMER_LASTNAME);
        Customer.balance = Double.valueOf(DbHandler.getCustomerColumn(enteredLogin, CUSTOMER_BALANCE));
    }

    public static double calculateTotalCost() {
        double res = 0.0;
        for (int i = 0; i < Order.orderList.size(); i++) {
            Product curProduct = Order.orderList.get(i);

            res += curProduct.getPrice() * curProduct.getOrderQuantity();
        }

        return res;
    }

}

