package project.files.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    }

    @FXML
    void initialize() throws IOException {
        List<Product> productList = DbHandler.getOrdersById(Customer.id);

        for (int i = 0; i < productList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(productCard));

            AnchorPane anchorPane = fxmlLoader.load();

            ProductCardController productCardController = fxmlLoader.getController();
            productCardController.setProduct(productList.get(i));
            productCardController.setData();

            grid.add(anchorPane, 1, i);
        }

    }
}