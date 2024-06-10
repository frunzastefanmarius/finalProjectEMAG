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
import java.util.List;


@WebServlet("/deleteBasketProducts")
public class DeleteBasketProducts extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("id");

        BasketManagementService bms = new BasketManagementService();
        List<BasketDisplay2> listaDeIdProductsDinBasketTipDisplay = bms.listaIdProductFromBasket(userId);

        List<Long> idProductList = new ArrayList<>();
        for (BasketDisplay2 item : listaDeIdProductsDinBasketTipDisplay) {
            idProductList.add(item.id);
        }

        Long[] idProductArray = idProductList.toArray(new Long[0]);

        String idProductString = req.getParameter("idProduct");
        Long idProduct = Long.parseLong(idProductString);


        if (userId != null && idProductArray != null) {

            boolean allDeletedSuccessfully = true;
            for (Long idProdusCos : idProductArray) {
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
