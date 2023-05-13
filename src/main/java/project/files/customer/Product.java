package project.files.customer;

import java.util.List;

public class Product {
    private Integer product_id = -1;
    private String title = "";
    private Double price = -1.0;
    private Integer order_quantity = 0;
    private Integer market_quantity = 0;
    private Integer order_id = 0;
    public static List<Product> productList;

    public Product(Integer id, String title, Double price) {
        this.product_id = id;
        this.title = title;
        this.price = price;
    }
    public Product(Integer id, String title, Double price, Integer order_quantity, Integer order_id) {
        this.product_id = id;
        this.title = title;
        this.price = price;
        this.order_quantity = order_quantity;
        this.order_id = order_id;
    }

    public Product() {

    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderQuantity() {
        return order_quantity;
    }

    public void setOrderQuantity(Integer order_quantity) {
        this.order_quantity = order_quantity;
    }

    public Integer getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getMarketQuantity() {
        return market_quantity;
    }

    public void setMarketQuantity(Integer market_quantity) {
        this.market_quantity = market_quantity;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
