package servlets;

import MenuOptions.BuyerMenu;
import db.DbUsersOperations;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginUser")
public class ServletLoginUser extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String un = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        User u = new User(un,pwd);

        DbUsersOperations db = new DbUsersOperations();
        Long idUser=null;
        String errM=null;

        idUser=db.searchUserForLogin(u);

        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(idUser!=null) {
            pw.println("Bine ai venit in cont, " + u.getUsername());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/buyerMenu.jsp");
            dispatcher.forward(request, resp);
            // sa il trimitem la produse
        }
        else
        {
            pw.println("Login failed !");
        }
    }



}
