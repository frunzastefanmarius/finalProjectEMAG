package entity;

public class Basket {

    private long id;

    private long idUser;

    private long idProduct;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public Basket(long idUser, long idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idProduct=" + idProduct +
                '}';
    }
}
