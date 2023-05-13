package project.files.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private Label loginField;

    @FXML
    private Label balanceLabel;

    @FXML
    private Circle circleImage;

    @FXML
    private Label totalCostLabel;

    @FXML
    private Button recalculateButton;

    @FXML
    private Button backButton;

    @FXML
    void recalculateClick() {
        totalCostLabel.setText("TOTAL: $" + Helper.calculateTotalCost());
    }

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
        loginField.setText(Customer.login);
        balanceLabel.setText(Customer.balance.toString() + "$");

        Image im = new Image("C:\\Users\\magzu\\IdeaProjects\\final_project\\src\\main\\resources\\project\\files\\final_project\\img\\3048122.png");
        circleImage.setFill(new ImagePattern(im));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        Order.orderList = DbHandler.getOrdersById(Customer.id);
        Order.totalCost = 0.0;

        for (int i = 0; i < Order.orderList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(orderCard));

            AnchorPane anchorPane = fxmlLoader.load();

            OrderCardController orderCardController = fxmlLoader.getController();
            orderCardController.setProduct(i);
            orderCardController.setData();

            grid.add(anchorPane, 1, i);

        }
        totalCostLabel.setText("TOTAL: $" + Helper.calculateTotalCost());

    }
}