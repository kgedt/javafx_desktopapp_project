package project.files.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.time.LocalDate;
import java.sql.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import project.files.Helper;
import project.files.customer.Customer;
import project.files.customer.Order;
import project.files.customer.Product;
import project.files.customer.Purchase;
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
    private Button buyButton;
    @FXML
    private Label errorText;
    @FXML
    void buyClick(ActionEvent event) throws Exception {
        if (Order.orderList.isEmpty()) {
            errorText.setText("Cart is empty");
            return;
        }
        if (balanceIsEnough()) {
            Customer.balance -= Order.totalCost;
            Customer.balance = Math.round(Customer.balance * 100.0) / 100.0;
            DbHandler.subtractBalance(Order.totalCost);
            List<Integer> prodIdList = new ArrayList<>();

            for (int i = 0; i < Order.orderList.size(); i++) {
                Product curProd = Order.orderList.get(i);

                Purchase curPurchase = new Purchase(DbHandler.lastReceiptId(),
                        Customer.id,
                        curProd.getProduct_id(),
                        Date.valueOf(LocalDate.now().toString()),
                        curProd.getOrderQuantity());

                Purchase.purchaseList.add(curPurchase);
                prodIdList.add(curProd.getProduct_id());

            }

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(receiptPage));
            Scene scene = new Scene(fxmlLoader.load(), 300, 600);
            stage.setTitle("RECEIPT");
            stage.setResizable(false);
            stage.setScene(scene);

            ReceiptPageController receiptPageController = fxmlLoader.getController();
            receiptPageController.setData(Purchase.purchaseList.get(Purchase.purchaseList.size() - 1), prodIdList);


            DbHandler.resetOrders(Customer.id);

            DbHandler.addPurchases();

            Helper.changeScene(buyButton, cartPage);

            stage.show();
        } else {
            errorText.setText("Not enough money");
        }

    }
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
        setCustomerData();
        setOrders();
        setBuyImage();
        setBackImage();
    }
    boolean balanceIsEnough() {
        Order.totalCost = Helper.calculateTotalCost();
        return Customer.balance >= Order.totalCost;
    }
    private void setCustomerData() {
        loginField.setText(Customer.login);
        balanceLabel.setText(Customer.balance.toString() + "$");

        Image im = new Image("C:\\Users\\magzu\\IdeaProjects\\final_project\\src\\main\\resources\\project\\files\\final_project\\img\\3048122.png");
        circleImage.setFill(new ImagePattern(im));
        circleImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }
    private void setOrders() throws IOException {
        Order.orderList = DbHandler.getOrdersById(Customer.id);
        Order.totalCost = 0.0;
        if (Order.orderList.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(StartApplication.class.getResource(emptyCard));

            AnchorPane anchorPane = fxmlLoader.load();


            grid.add(anchorPane, 1, 0);
        }

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
    private void setBuyImage() {
        ImageView imv = new ImageView(String.valueOf(StartApplication.class.getResource("img/purchase.png")));
        imv.setFitWidth(60);
        imv.setFitHeight(60);
        buyButton.setText("");
        buyButton.graphicProperty().setValue(imv);
    }
    private void setBackImage() {
        ImageView imv = new ImageView(String.valueOf(StartApplication.class.getResource("img/back.png")));
        imv.setFitWidth(60);
        imv.setFitHeight(60);
        backButton.setText("");
        backButton.graphicProperty().setValue(imv);
    }
}