package servlets;

import controller.BasketManagementService;
import controller.ProductManagementService;
import entity.BasketDisplay;
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
import java.util.List;


@WebServlet("/showBasket")
public class ServletBasketProducts extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object o = session.getAttribute("idUser"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

        //boolean variabila = Utility.isLoggedIn();
        //TODO:aici e problema

        if(o!=null)
        {
            Long iduser = (Long)o;

            BasketManagementService bms = new BasketManagementService();
            List<BasketDisplay> lb = bms.readBasket(iduser);
            JSONObject json = new JSONObject();
            json.put("listBasket", lb);
            String result = json.toString();
            returnJsonResponse(response, result);

        }
        else
        {
            error(response, "operation forbidden. user is not logged in.");
        }
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
