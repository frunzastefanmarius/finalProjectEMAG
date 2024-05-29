package controller;

import db.DbBasketOperations;
import entity.Basket;
import entity.BasketDisplay;
import entity.BasketDisplay2;

import java.util.List;

public class BasketManagementService {

    public boolean insertInBasket(Basket b) {
        DbBasketOperations dbBasketOperations = new DbBasketOperations();
        return dbBasketOperations.insert(b);
    }

    public List<BasketDisplay> readBasket(Long idUser) {

        DbBasketOperations dbBasketOperations = new DbBasketOperations();
        return dbBasketOperations.readBasketOfAUser(idUser);
    }
    public boolean deleteProductFromBasket(Long idBasket){
        DbBasketOperations dbb = new DbBasketOperations();
        return dbb.deleteBasketItem(idBasket);
    }

    public boolean deleteAllFromBasket(Long idUser){
        DbBasketOperations dbBasketOperations = new DbBasketOperations();
        return dbBasketOperations.deleteAllBasket(idUser);

    }
    public List<BasketDisplay2> listaIdBasketFromBasket(Long idUser){
        DbBasketOperations dbb = new DbBasketOperations();
        return dbb.listaDeIdBasketDinBasket(idUser);
    }

}
