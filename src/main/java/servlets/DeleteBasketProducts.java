package servlets;

import controller.BasketManagementService;
import entity.BasketDisplay;
import entity.BasketDisplay2;
import entity.ProductDisplay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/deleteBasketProducts")
public class DeleteBasketProducts extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("am ajuns la deleteBasketProductServlet");
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("id");

        String idProductString = req.getParameter("idProduct");
        System.out.println("asta e id: "+idProductString);
        String[] idProductArray = idProductString.split(",");

        BasketManagementService bms = new BasketManagementService();

        //este o problema aici ca e long si nu Long?
        long[] longArray = Arrays.stream(idProductArray).mapToLong(str -> {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + str);
                return 0L;
            }
        }) .toArray();

        if (userId != null && longArray != null) {

            boolean allDeletedSuccessfully = true;
            for (Long idProdusCos : longArray) {
                System.out.println("sterg produsul cu id" + idProdusCos);
                boolean deleted = bms.deleteBasketProduct(userId, idProdusCos);
                if (!deleted) {
                    allDeletedSuccessfully = false;
                }
            }

            if (allDeletedSuccessfully) {
                success(resp, "Selected product items deleted successfully.");
            } else {
                error(resp, "Failed to delete selected product items.");
            }
        } else {
            error(resp, "Operation forbidden. User is not logged in or no food items selected.");
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
