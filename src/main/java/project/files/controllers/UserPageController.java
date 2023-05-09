package project.files.controllers;

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
import project.files.customer.Product;
import project.files.database.DbHandler;
import project.files.final_project.StartApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    void logoutClick() throws IOException {
        Helper.changeScene(logoutButton, authorizePage);
    }

    private List<Product> productList;


    @FXML
    void initialize() throws IOException, SQLException {
        loginField.setText(Customer.login);
        balanceLabel.setText(Customer.balance.toString() + "$");

        Image im = new Image("C:\\Users\\magzu\\IdeaProjects\\final_project\\src\\main\\resources\\project\\files\\final_project\\img\\3048122.png");
        circleImage.setFill(new ImagePattern(im));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        this.productList = DbHandler.getAllProductList();

        for (int i = 0; i < productList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(productCard));


            AnchorPane anchorPane = fxmlLoader.load();

            ProductCardController productCardController = fxmlLoader.getController();
            productCardController.setProduct(productList.get(i));
            productCardController.setData();

            gridPane.add(anchorPane, 1, i);
        }
    }



}
