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
import java.util.Arrays;
import java.util.List;


@WebServlet("/addToBasket")
public class AddBasketProducts extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("idUser");
        String idProductString = req.getParameter("idProduct");
        String[] idProductArray = idProductString.split(",");

        long[] longArray = Arrays.stream(idProductArray).mapToLong(str -> {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + str);
                return 0L;
            } }) .toArray();

                System.out.println(idProductString + " de debug");
                boolean productsAdded = false;

                for (long idProdus : longArray) {
                    System.out.println("aici m am blocat");
                    System.out.println("aici : "+userId+ " si "+idProdus);

                    Basket b = new Basket(userId, idProdus);
                    System.out.println("aici am creat basket b");

                    BasketManagementService bms = new BasketManagementService();
                    productsAdded = bms.insertInBasket(b);

                }
                if (userId != null && productsAdded) {

                    success(resp, "Selected product items deleted successfully.");
                } else {
                    error(resp, "Operation forbidden. User is not logged in or no product items selected.");
                }
            }

            private void success (HttpServletResponse resp, String message){
                returnJsonResponse(resp, "{\"status\":\"success\",\"message\":\"" + message + "\"}");
            }

            private void returnJsonResponse (HttpServletResponse response, String jsonResponse){
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
            private void error (HttpServletResponse resp, String mesaj){
                try {
                    PrintWriter pw = resp.getWriter();
                    pw.println(mesaj);
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
