package servlets;

import controller.OrdersManagementService;
import controller.ProductManagementService;
import entity.Order;
import entity.OrderDisplay;
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


    @WebServlet("/showOrder")
    public class ServletOrder extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            Object o = session.getAttribute("idUser"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

            if(o!=null)
            {
                Long idUser = (Long) o;
                OrdersManagementService oms = new OrdersManagementService();
                List<OrderDisplay> products = oms.showOrder(idUser);
                for(Object produsDeAfisat : products){
                    System.out.println(produsDeAfisat);
                }
                System.out.println(products);
                JSONObject json = new JSONObject();
                json.put("listFromBackend", products);
                String result = json.toString();
                System.out.println(result);
                returnJsonResponse(response, result);

//                Order order = new Order(creationDateTime, delivery, payment, idUserForOrder, idProductForOrder);
//                oms.insertInOrder(order);

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
