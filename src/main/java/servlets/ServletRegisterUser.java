package servlets;

import db.DbUsersOperations;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/registerUser")
public class ServletRegisterUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String un;
        String pwd;
        String pwd1;

        //TODO: daca e fail sa se afisesze ceva si de acolo sa trimit din nou
        // la register User dupa ce apasa pe un buton

            un = request.getParameter("username");
            pwd = request.getParameter("userpwd");
            pwd1 = request.getParameter("userpwd1");

            if(!pwd.equals(pwd1)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/eroareParola.html");
                dispatcher.forward(request, resp);
            }



        User u = new User(un, pwd);
        DbUsersOperations db = new DbUsersOperations();
        String errM = null;
        boolean isOk = false;
        try {
            isOk = db.insert(u);
        } catch (SQLException e) {
            e.printStackTrace();
            errM = e.getMessage();
        }
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isOk) {
            pw.println("Multumim pt inregistrare. Totul ok!");
        } else {
            if (errM != null && errM.indexOf("duplicate key value violates unique constraint") != -1)
                pw.println("Ne pare rau dar acest user deja exista");
            else
                pw.println("Ne pare rau, avem probl tehnice !");

        }


    }
}
