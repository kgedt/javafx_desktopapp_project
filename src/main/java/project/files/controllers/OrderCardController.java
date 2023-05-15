package project.files.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.files.customer.Order;
import project.files.customer.Product;
import project.files.final_project.StartApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderCardController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView img;
    @FXML
    private Label title;
    @FXML
    private Label price;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Label quantityLabel;
    @FXML
    void addClick(ActionEvent event) {
        quantity++;
        quantityLabel.setText(String.valueOf(quantity));
        Order.orderList.get(order_ind).setOrderQuantity(quantity);
        Order.totalCost += product.getPrice();
    }
    @FXML
    void removeClick(ActionEvent event) {
        if (quantity >= 1) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
            Order.orderList.get(order_ind).setOrderQuantity(quantity);
            Order.totalCost -= product.getPrice();
        }
    }
    private Product product;
    private Integer order_ind;
    private Integer quantity = 0;
    public void setProduct(Integer ind) {
        this.product = Order.orderList.get(ind);
        this.quantity = this.product.getOrderQuantity();
        this.order_ind = ind;
    }
    @FXML
    void initialize() {

    }
    public void setData() {
        price.setText("$" + product.getPrice());

        title.setText(product.getTitle());

        quantityLabel.setText(String.valueOf(product.getOrderQuantity()));

        Image im = new Image(String.valueOf(StartApplication.class.getResource("img/" + product.getTitle() + ".jpg")));
        img.setImage(im);
    }
}
