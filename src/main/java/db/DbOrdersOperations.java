package db;

import entity.Order;
import entity.OrderDisplay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrdersOperations {

    public boolean insert(Order Order) {

        final String URLDB = "jdbc:postgresql://localhost:5432/emag";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "vvv";
        int val = 0; // 1
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            // rulare sql
            PreparedStatement pSt = conn.prepareStatement("insert into orders(createdtime, delivery, payment, iduser, idproduct) values(?, ?, ?, ?, ?)");

            pSt.setTimestamp(1, Order.getCreationDateTime());
            pSt.setBoolean(2, Order.isDelivery());
            pSt.setBoolean(3, Order.isPayment());
            pSt.setLong(4, Order.getIdUserFromBasket());
            pSt.setLong(5, Order.getIdProductFromBasket());

            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;
    }
    public List<OrderDisplay> readAllOrdersOfAUser(Long idUser) {
        List<OrderDisplay> lo = new ArrayList<>();

        try {
            final String URLDB = "jdbc:postgresql://localhost:5432/emag";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

//            String q = "select * from orders \n" +
//                    "\t where basket.iduser=? \n" +
//                    "and basket.idproduct=products.id";

//            String q = "SELECT * FROM orders " +
//                    "JOIN users ON users.id = id " +
//                    "JOIN products ON products.id = id " +
//                    "WHERE users.id = ?;";

            String q = "SELECT * FROM orders " +
                    "JOIN users ON users.id = orders.iduser " +
                    "JOIN products ON products.id = orders.idproduct " +
                    "WHERE users.id = ?;";

            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, idUser);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {

                long id = rs.getLong("id");
                Timestamp creationTime = rs.getTimestamp("createdtime");
                boolean delivery = rs.getBoolean("delivery");
                boolean payment = rs.getBoolean("payment");
                Long idUserFromBasket = rs.getLong("iduser");
                Long idProductFromBasket = rs.getLong("idproduct");

                OrderDisplay o = new OrderDisplay(id, creationTime, delivery, payment, idUserFromBasket, idProductFromBasket);
                lo.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lo;

    }
}
