package project.files.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import project.files.Helper;
import project.files.customer.Customer;
import project.files.customer.Order;
import project.files.customer.Product;
import project.files.database.DbHandler;
import project.files.final_project.StartApplication;

import static project.files.pages.Pages.*;

public class CartPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Button backButton;

    @FXML
    void backClick(ActionEvent event) throws IOException {
        Helper.changeScene(backButton, userPage);
        Product curProd;

        for (int i = 0; i < Order.orderList.size(); i++) {
            curProd = Order.orderList.get(i);
            DbHandler.orderUpdate(curProd.getOrderId(), curProd.getOrderQuantity());
        }
    }


    @FXML
    void initialize() throws IOException {
        Order.orderList = DbHandler.getOrdersById(Customer.id);

        for (int i = 0; i < Order.orderList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(orderCard));

            AnchorPane anchorPane = fxmlLoader.load();

            OrderCardController orderCardController = fxmlLoader.getController();
            orderCardController.setProduct(i);
            orderCardController.setData();

            grid.add(anchorPane, 1, i);
        }

    }
}