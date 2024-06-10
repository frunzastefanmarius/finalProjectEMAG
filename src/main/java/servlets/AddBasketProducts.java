package servlets;

import controller.BasketManagementService;
import entity.Basket;
import entity.BasketDisplay2;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/addToBasket")
public class AddBasketProducts extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("id");
        String idProductString = req.getParameter("idProduct");
        String[] idProductArray = idProductString.split(",");
        boolean productsAdded = false;
        for (String idProdus : idProductArray) {
            Long idProduct = 1L;
            try {
                idProduct = Long.parseLong(idProdus);
                System.out.println(idProduct);
            } catch (NumberFormatException e) {
                System.err.println("Invalid long value: " + idProdus);
            }
            Basket b = new Basket(userId, idProduct);

            BasketManagementService bms = new BasketManagementService();
            productsAdded = bms.insertInBasket(b);
        }
        if (userId != null && productsAdded) {

                success(resp, "Selected product items deleted successfully.");
        } else {
            error(resp, "Operation forbidden. User is not logged in or no product items selected.");
        }
    }

    private void success(HttpServletResponse resp, String message) {
        returnJsonResponse(resp, "{\"status\":\"success\",\"message\":\"" + message + "\"}");
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
    private void error( HttpServletResponse resp, String mesaj) {
        try {
            PrintWriter pw = resp.getWriter();
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
