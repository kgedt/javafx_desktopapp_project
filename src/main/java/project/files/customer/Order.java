package project.files.customer;

import java.util.List;

public class Order {
    private Integer order_id;
    private Integer customer_id;
    private Integer product_id;
    private Integer quantity;
    public static Double totalCost = 0.0;

    public static List<Product> orderList;

    public Order(Integer order_id, Integer customer_id, Integer product_id, Integer quantity) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Order() {
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setId(Integer id) {
        this.order_id = id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Order another_order) {
        return product_id.equals(another_order.product_id);
    }
}
