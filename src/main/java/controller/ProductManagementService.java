package controller;

import db.DbProductsOperations;
import entity.Product;
import entity.ProductDisplay;

import java.util.List;

public class ProductManagementService {
    DbProductsOperations db = new DbProductsOperations();


    public List<ProductDisplay> showAllProducts(String searchParam) {
        return db.readAllProducts(searchParam);
    }

    public boolean insert(Product p) {
        return db.insert(p);
    }

    public List<ProductDisplay> idProductForOrder(Long idUser) {
        return db.readIdForOrder(idUser);
    }

    public  List<ProductDisplay> idProductForAddInBasket(){
        return db.readAllIdProduct();
    }
    public List<ProductDisplay> readAllForServlet(){
        return db.readAllProductsForServlet();
    }

}
