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
import java.util.Objects;


@WebServlet("/showProducts")
public class ServletShowProducts extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object o = session.getAttribute("idUser"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

        if(o!=null)
        {
            String searchParam = request.getParameter("search");
            if(searchParam==null)
                searchParam="";

            ProductManagementService pms = new ProductManagementService();
            List<ProductDisplay> products = pms.showAllProducts(searchParam);
            JSONObject json = new JSONObject();
            json.put("listFromBackendShow", products);
            String result = json.toString();
            System.out.println(result);
            System.out.println("aici este searchParam: " + searchParam);//de debug pentru consola daca afiseaza inseamna ca intra aici
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
