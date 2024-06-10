package db;

import entity.Basket;
import entity.BasketDisplay;
import entity.BasketDisplay2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbBasketOperations {


    public boolean insert(Basket b) {

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
            PreparedStatement pSt = conn.prepareStatement("insert into basket(iduser, idproduct) values(?, ?)");
            pSt.setLong(1, b.getIdUser());
            pSt.setLong(2, b.getIdProduct());
            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;
    }

    public List<BasketDisplay> readBasketOfAUser(Long idUser) {
        List<BasketDisplay> lp = new ArrayList<>();

        try {
            final String URLDB = "jdbc:postgresql://localhost:5432/emag";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            String q = "select products.name as name, basket.id as id from products,basket \n" +
                    "\t where basket.iduser=? \n" +
                    "and basket.idproduct=products.id";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, idUser);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name").trim();
                long id = rs.getLong("id");

                BasketDisplay p = new BasketDisplay(id, name);
                lp.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lp;
    }


    public boolean deleteBasketItem(Long idBasket) {

        final String URLDB = "jdbc:postgresql://localhost:5432/emag";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "vvv";
        int val = 0; // 1
        try {
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            PreparedStatement pSt = conn.prepareStatement("delete from basket where id = ?");
            pSt.setLong(1, idBasket);

            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;

    }
    public boolean deleteAllBasket(Long idUser) {

        final String URLDB = "jdbc:postgresql://localhost:5432/emag";
        final String USERNAMEDB = "postgres";
        final String PWDDB = "vvv";
        int val = 0; // 1
        try {
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            PreparedStatement pSt = conn.prepareStatement("delete from basket where iduser = ?");
            pSt.setLong(1, idUser);

            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;

    }

    public List<BasketDisplay2> listaDeIdBasketDinBasket (Long iduser){
        List<BasketDisplay2> listIdDeBasket = new ArrayList<>();

        try {
            final String URLDB = "jdbc:postgresql://localhost:5432/emag";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            String q = "select basket.id\n" +
                    "\tfrom basket\n" +
                    "\twhere basket.iduser = ? \n" +
                    "\torder by basket.id asc ";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, iduser);

            ResultSet rs = pSt.executeQuery();//executa queriul iar cat timp am rezultate retunreaza true


            while (rs.next()) {//rs este ce imi returneaza baza de date
;
                long id = rs.getLong("id");

                BasketDisplay2 IdBasketOfAUser = new BasketDisplay2(id);
                //aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                listIdDeBasket.add(IdBasketOfAUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIdDeBasket;

    }
    public boolean deleteBasketProductDB(Long iduser, Long idproduct) {
        boolean isDeleted = false;

        try {
            final String URLDB = "jdbc:postgresql://localhost:5432/emag";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            // Create a prepared statement to delete the food item
            String deleteQuery = "DELETE FROM basket WHERE iduser = ? AND idproduct = ?";
            PreparedStatement pSt = conn.prepareStatement(deleteQuery);
            pSt.setLong(1, iduser);
            pSt.setLong(1, idproduct);

            // Execute the delete query
//            int deletedRows = pSt.executeUpdate();
//
//            if (deletedRows > 0) {
//                isDeleted = true;
//                System.out.println("managed to delete " +foodName.trim());
//            }
            isDeleted = true;

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isDeleted = false;
        }

        return isDeleted;
    }
    public List<BasketDisplay2> listaDeIdBasketDinBasketDB (Long iduser){
        List<BasketDisplay2> listIdDeBasket = new ArrayList<>();

        try {
            final String URLDB = "jdbc:postgresql://localhost:5432/emag";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            String q = "select basket.idproduct\n" +
                    "\tfrom basket\n" +
                    "\twhere basket.iduser = ? \n" +
                    "\torder by basket.id asc ";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, iduser);

            ResultSet rs = pSt.executeQuery();//executa queriul iar cat timp am rezultate retunreaza true


            while (rs.next()) {//rs este ce imi returneaza baza de date
                ;
                long id = rs.getLong("id");

                BasketDisplay2 IdBasketOfAUser = new BasketDisplay2(id);
                //aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                listIdDeBasket.add(IdBasketOfAUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIdDeBasket;

    }

}
