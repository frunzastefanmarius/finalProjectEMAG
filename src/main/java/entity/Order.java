package entity;
import java.sql.Timestamp;

public class Order {

    private long id;
    private Timestamp creationDateTime;
    private boolean delivery;
    private boolean payment;
    //private long idBasket;
    private long idUserFromBasket;
    private long idProductFromBasket;

    public Order(Timestamp creationDateTime, boolean delivery, boolean payment) {
        this.creationDateTime = creationDateTime;
        this.delivery = delivery;
        this.payment = payment;
    }

    public Order(Timestamp creationDateTime, boolean delivery, boolean payment, long idUserFromBasket, long idProductFromBasket) {
        this.creationDateTime = creationDateTime;
        this.delivery = delivery;
        this.payment = payment;
        this.idUserFromBasket = idUserFromBasket;
        this.idProductFromBasket = idProductFromBasket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Timestamp creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }



    public long getIdUserFromBasket() {
        return idUserFromBasket;
    }

    public void setIdUserFromBasket(long idUserFromBasket) {
        this.idUserFromBasket = idUserFromBasket;
    }

    public long getIdProductFromBasket() {
        return idProductFromBasket;
    }

    public void setIdProductFromBasket(long idProductFromBasket) {
        this.idProductFromBasket = idProductFromBasket;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDateTime=" + creationDateTime +
                ", delivery=" + delivery +
                ", payment=" + payment +
                '}';
    }
}
