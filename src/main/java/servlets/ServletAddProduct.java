package servlets;


import controller.BasketManagementService;
import controller.ProductManagementService;
import entity.Basket;
import entity.ProductDisplay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProduct")
public class ServletAddProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idUser = Long.parseLong(request.getParameter("idUser"));
        Long idProd = Long.parseLong(request.getParameter("idProd"));

        ProductManagementService pms = new ProductManagementService();
        List<ProductDisplay> listAllProductObj = pms.idProductForAddInBasket();
        List<Long> listAllProductLong = new ArrayList<>();

        for (ProductDisplay obj : listAllProductObj){
            listAllProductLong.add((long) obj.getId());
        }

        if(listAllProductLong.contains(idProd)){

            //creez o lista cu id de produse si daca idprod introdus este egal cu un element din lista,
            //sa se exectute daca nu sa spuna ca nu e si apeleze buyerMenu

            //creez o lista cu id de produse si daca idprod introdus este egal cu un element din lista,
            //sa se exectute daca nu sa spuna ca nu e si apeleze buyerMenu

            Basket b = new Basket(idUser, idProd);

            BasketManagementService bms = new BasketManagementService();
            bms.insertInBasket(b);
            bms.readBasket(idUser);
        }else {
            System.out.println("Nu ati introdus un id de produs.");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addProduct.html").forward(request, response);
    }
}