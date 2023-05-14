package project.files.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.files.customer.Order;
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

    void setData(Purchase purchase, List<Integer> prodIdList) {
        String prodListString = prodIdList.toString();
        receiptIdLabel.setText("RECEIPT #" + purchase.getPurchaseId());
        if (prodIdList.size() >= 4) {
            productIdLabel.setText("ProdList id: #" + prodListString.substring(0, prodListString.length()/2) + "\n"
                    + prodListString.substring(prodListString.length()/2));
        } else {
            productIdLabel.setText("ProdList id: #" + prodListString);
        }
        customerIdLabel.setText("Customer id: #" + purchase.getCustomerId());
        priceLabel.setText("Price: $" + Order.totalCost);

        String dateStr = purchase.getPurchaseDate().toString();
        monthLabel.setText(dateStr);
    }
}
