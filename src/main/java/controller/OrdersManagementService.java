package controller;

import db.DbOrdersOperations;
import entity.Order;
import entity.OrderDisplay;

import java.util.List;

public class OrdersManagementService {

    public boolean insertInOrder(Order o){
        DbOrdersOperations dbOrdersOperations = new DbOrdersOperations();
        return dbOrdersOperations.insert(o);
    }
    public List<OrderDisplay> showOrder(Long idUser){
        DbOrdersOperations dbOrdersOperations = new DbOrdersOperations();
        List<OrderDisplay> listOfOrders = dbOrdersOperations.readAllOrdersOfAUser(idUser);
        return listOfOrders;


    }
}
