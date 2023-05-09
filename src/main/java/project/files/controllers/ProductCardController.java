package project.files.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.files.customer.Product;

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

    private Product product;

    public void setProduct(Product prod) {
        this.product = prod;
    }

    @FXML
    void initialize() {

    }

    public void setData() {
        price.setText("$" + product.getPrice());

        title.setText(product.getTitle());

        Image im = new Image("C:\\Users\\magzu\\IdeaProjects\\final_project\\src\\main\\resources\\project\\files\\final_project\\img\\" + product.getTitle() + ".jpg");
        img.setImage(im);
    }
}