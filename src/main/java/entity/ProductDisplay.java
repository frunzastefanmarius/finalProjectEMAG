package entity;

public class ProductDisplay {

    private String name;
    private long id;
    private String description;
    private String price;
    private String vendorName;
    private String categoryName;

    public ProductDisplay(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductDisplay(String name, long id, String description, String price, String vendorName, String categoryName) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.vendorName = vendorName;
        this.categoryName = categoryName;
    }

    public ProductDisplay(long id) {
        this.id = id;
    }

    public ProductDisplay() {
    }

    @Override
    public String toString() {
        return "ProductDisplay{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
