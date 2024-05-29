package servlets;

import controller.ProductManagementService;
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


@WebServlet("/showProducts")
public class ServletShowProducts extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession s = request.getSession();
        Object o = s.getAttribute("idUser"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

        if(o!=null)
        {
//            DBFoodList db = new DBFoodList();
//            List<MyFoodList> l = db.getFoodList(iduser, search);

            ProductManagementService pms = new ProductManagementService();
            List<ProductDisplay> products = pms.readAllForServlet();
            JSONObject json = new JSONObject();
            json.put("listFromBackend", products); // only food name
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

//TODO:de trimis un json,
