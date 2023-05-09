package project.files.customer;

public class Order {
    private Integer id;
    private Integer customer_id;
    private Integer product_id;
    private boolean is_bought;

    public Order(Integer id, Integer customer_id, Integer product_id, boolean is_bought) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.is_bought = is_bought;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isIs_bought() {
        return is_bought;
    }

    public void setIs_bought(boolean is_bought) {
        this.is_bought = is_bought;
    }

    public boolean equals(Order another_order) {
        return product_id.equals(another_order.product_id);
    }
}
