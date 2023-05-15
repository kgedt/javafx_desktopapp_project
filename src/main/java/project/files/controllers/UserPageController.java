package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import project.files.Helper;
import project.files.comparators.*;
import project.files.customer.Customer;
import project.files.customer.Order;
import project.files.customer.Product;
import project.files.database.DbHandler;
import project.files.final_project.StartApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
    private Button sortAZ;

    @FXML
    private Button sortZA;

    @FXML
    private Button sortLowHigh;

    @FXML
    private Button sortHighLow;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label addErrorText;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button cartButton;

    @FXML
    private Button addCartButton;

    @FXML
    void sortAZclick(ActionEvent event) throws IOException {
        this.sortBy = 1;
        Helper.changeScene(sortAZ, userPage);
    }

    @FXML
    void sortHighLowClick(ActionEvent event) throws IOException {
        this.sortBy = 4;
        Helper.changeScene(sortHighLow, userPage);
    }

    @FXML
    void sortLowHighClick(ActionEvent event) throws IOException {
        this.sortBy = 3;
        Helper.changeScene(sortLowHigh, userPage);
    }

    @FXML
    void sortZAclick(ActionEvent event) throws IOException {
        this.sortBy = 2;
        Helper.changeScene(sortZA, userPage);
    }


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
            addErrorText.setText("Enter only numbers");
//            e.printStackTrace();
        }
    }

    @FXML
    void logoutClick() throws IOException {
        Helper.changeScene(logoutButton, authorizePage);
    }


    @FXML
    void initialize() throws SQLException, IOException {
        setCartButtonImage();
        setAddToCartButtonImage();
        setCustomerData();
        setProducts();
        setLogoutImage();
    }

    private void setCartButtonImage() {
        ImageView imv = new ImageView(String.valueOf(StartApplication.class.getResource("img/basket.png")));
        imv.setFitWidth(60);
        imv.setFitHeight(60);
        cartButton.graphicProperty().setValue(imv);
        cartButton.setText("");
    }

    private void setAddToCartButtonImage() {
        ImageView imv = new ImageView(String.valueOf(StartApplication.class.getResource("img/addToCart.png")));
        imv.setFitWidth(60);
        imv.setFitHeight(60);
        addCartButton.setText("");
        addCartButton.graphicProperty().setValue(imv);
    }

    private void setCustomerData() {
        loginField.setText(Customer.login);
        balanceLabel.setText(Customer.balance.toString() + "$");
        Image im = new Image(String.valueOf(StartApplication.class.getResource("img/3048122.png")));
        circleImage.setFill(new ImagePattern(im));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

    }

    private void setProducts() throws SQLException, IOException {
        Product.productList = DbHandler.getAllProductList();
        Comparator<Product> comp = null;

        switch (sortBy) {
            case 1:
                comp = new AZComparator().thenComparing(new AZComparator());
                break;
            case 2:
                comp = new ZAComparator().thenComparing(new ZAComparator());
                break;
            case 3:
                comp = new LHComparator().thenComparing(new LHComparator());
                break;
            case 4:
                comp = new HLComparator().thenComparing(new HLComparator());
                break;
            default:
                this.sortBy = -1;
        }
        if (sortBy >= 1 && sortBy <= 4) {
            Product.productList.sort(comp);
        }

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

    private void setLogoutImage() {
        ImageView imv = new ImageView(String.valueOf(StartApplication.class.getResource("img/logout.png")));
        imv.setFitWidth(60);
        imv.setFitHeight(60);
        logoutButton.setText("");
        logoutButton.graphicProperty().setValue(imv);
    }

    public static int sortBy = -1;

}
