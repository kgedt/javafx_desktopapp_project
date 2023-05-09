package project.files.customer;

public class Product {
    private Integer id = -1;
    private String title = "";
    private Double price = -1.0;

    public Product(Integer id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
