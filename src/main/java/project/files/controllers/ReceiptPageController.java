package project.files.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.files.customer.Purchase;
import project.files.database.DbHandler;

public class ReceiptPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label receiptIdLabel;

    @FXML
    private Label productIdLabel;

    @FXML
    private Label customerIdLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label monthLabel;

    @FXML
    private Label dayLabel;

    @FXML
    void initialize() {

    }

    void setData(Purchase purchase) {
        receiptIdLabel.setText("RECEIPT #" + purchase.getPurchaseId());
        productIdLabel.setText("Prod id: #" + purchase.getProductId());
        customerIdLabel.setText("Customer id: #" + purchase.getCustomerId());
        priceLabel.setText("Price: $" + purchase.getPurchaseQuantity() * DbHandler.getProductCostById(purchase.getProductId()));

        String dateStr = purchase.getPurchaseDate().toString();
        monthLabel.setText(dateStr);
    }
}
