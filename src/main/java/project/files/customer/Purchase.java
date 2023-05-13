package project.files.customer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    public static List<Purchase> purchaseList = new ArrayList<>();
    private int purchaseId = -1;
    private int customerId;
    private int productId;
    private Date purchaseDate;
    private int purchaseQuantity;

    public Purchase(int purchase_id, int customer_id, int product_id, Date purchase_date, int purchase_quantity) {
        this.purchaseId = purchase_id;
        this.customerId = customer_id;
        this.productId = product_id;
        this.purchaseDate = purchase_date;
        this.purchaseQuantity = purchase_quantity;
    }

    public Purchase(int customer_id, int product_id, Date purchase_date, int purchase_quantity) {
        this.customerId = customer_id;
        this.productId = product_id;
        this.purchaseDate = purchase_date;
        this.purchaseQuantity = purchase_quantity;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
