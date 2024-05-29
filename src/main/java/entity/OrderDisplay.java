package entity;

import java.sql.Timestamp;

public class OrderDisplay {

    private Long id;
    private Timestamp creationDateTime;
    private boolean delivery;
    private boolean payment;
    private Long idBasket;
    private Long idUserFromBasket;
    private Long idProductFromBasket;

    public OrderDisplay(Long id, Timestamp creationDateTime, boolean delivery, boolean payment, Long idBasket) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.delivery = delivery;
        this.payment = payment;
        this.idBasket = idBasket;
    }

    public OrderDisplay(Long id, Timestamp creationDateTime, boolean delivery, boolean payment, Long idUserFromBasket, Long idProductFromBasket) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.delivery = delivery;
        this.payment = payment;
        this.idUserFromBasket = idUserFromBasket;
        this.idProductFromBasket = idProductFromBasket;
    }

    @Override
    public String toString() {
        return "OrderDisplay{" +
                "creationDateTime=" + creationDateTime +
                ", delivery=" + delivery +
                ", payment=" + payment +
                ", idUserFromBasket=" + idUserFromBasket +
                ", idProductFromBasket=" + idProductFromBasket +
                '}';
    }

}
