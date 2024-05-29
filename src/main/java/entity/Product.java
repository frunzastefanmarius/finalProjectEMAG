package entity;

public class Product {

    private long id;

    private String name;
    private String description;
    private double price;
    private long iduser;
    private long idCategory;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", iduser=" + iduser +
                ", idCategory=" + idCategory +
                '}';
    }

    public Product(String name, String description, double price, long iduser, long idCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.iduser = iduser;
        this.idCategory = idCategory;
    }

    public Product(long id, String name, String description, double price, long iduser, long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.iduser = iduser;
        this.idCategory = idCategory;
    }


}
