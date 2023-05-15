package project.files.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.files.customer.Product;
import project.files.final_project.StartApplication;

public class ProductCardController {
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
    private Product product;
    private Integer quantity = 0;
    private Integer prod_ind;
    @FXML
    void addClick(ActionEvent event) {
        quantity++;
        quantityLabel.setText(String.valueOf(quantity));
        Product.productList.get(prod_ind).setMarketQuantity(quantity);
    }
    @FXML
    void removeClick(ActionEvent event) {
        if (quantity >= 1) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
            Product.productList.get(prod_ind).setMarketQuantity(quantity);
        }
    }
    public void setProduct(int ind) {
        this.product = Product.productList.get(ind);
        this.prod_ind = ind;
    }
    @FXML
    void initialize() {

    }
    public void setData() {
        price.setText("$" + product.getPrice());

        title.setText(product.getTitle());

        Image im = new Image(String.valueOf(StartApplication.class.getResource("img/" + product.getTitle() + ".jpg")));
        img.setImage(im);
    }
}