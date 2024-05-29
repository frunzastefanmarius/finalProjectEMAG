package entity;

public class BasketDisplay {

    private Long id;
    private String productName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BasketDisplay(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public BasketDisplay(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BasketDisplay{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }
}
