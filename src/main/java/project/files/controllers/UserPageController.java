package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static project.files.pages.Pages.*;


public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label firstnameField;

    @FXML
    private Label lastnameField;

    @FXML
    private Label loginField;


    @FXML
    private Button logoutButton;

    @FXML
    private Label balanceLabel;

    @FXML
    private Circle circleImage;

    @FXML
    private TextField moneyAmount;

    @FXML
    private Button addMoneyButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button cartButton;

    @FXML
    private Button addCartButton;


    @FXML
    void cartClick(ActionEvent event) throws IOException {
        Helper.changeScene(cartButton, cartPage);
    }

    @FXML
    void addCartClick(ActionEvent event) throws SQLException, IOException {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < Product.productList.size(); i++) {
            Product cur = Product.productList.get(i);
            if (cur.getMarketQuantity() > 0) {
                orders.add(new Order(cur.getOrderId(), Customer.id, cur.getProduct_id(), cur.getMarketQuantity()));
            }
        }
        DbHandler.addOrders(orders);
        Helper.changeScene(addCartButton, cartPage);
    }

    @FXML
    void addMoneyClick(ActionEvent event) {
        try {
            DbHandler.addBalance(Double.parseDouble(moneyAmount.getText()));
            Customer.balance += Double.parseDouble(moneyAmount.getText());
            moneyAmount.setText("");
            Helper.changeScene(addMoneyButton, userPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutClick() throws IOException {
        Helper.changeScene(logoutButton, authorizePage);
    }


    @FXML
    void initialize() throws IOException, SQLException {
        loginField.setText(Customer.login);
        balanceLabel.setText(Customer.balance.toString() + "$");

        Image im = new Image("C:\\Users\\magzu\\IdeaProjects\\final_project\\src\\main\\resources\\project\\files\\final_project\\img\\3048122.png");
        circleImage.setFill(new ImagePattern(im));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        Product.productList = DbHandler.getAllProductList();

        for (int i = 0; i < Product.productList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(productCard));

            AnchorPane anchorPane = fxmlLoader.load();

            ProductCardController productCardController = fxmlLoader.getController();
            productCardController.setProduct(i);
            productCardController.setData();

            gridPane.add(anchorPane, 1, i);
        }
    }



}
