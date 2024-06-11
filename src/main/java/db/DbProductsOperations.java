package db;

import entity.Product;
import entity.ProductDisplay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbProductsOperations {


    public boolean insert(Product p) {

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
            PreparedStatement pSt = conn.prepareStatement("insert into products(name, description, price, iduser, idcategory) values (?, ?, ?,?,?)");
            pSt.setString(1, p.getName());
            pSt.setString(2, p.getDescription());
            pSt.setDouble(3, p.getPrice());
            pSt.setLong(4, p.getIduser());
            pSt.setLong(5, p.getIdCategory());
            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;
    }

    public List<ProductDisplay> readAllProducts(String searchParam) {
        List<ProductDisplay> lp = new ArrayList<>();

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

            String q = "select products.id as id, products.name as name,products.description as description, products.price as price, users.username as vendorname, categories.name as categoryname \n" +
                    "\tfrom products, users, categories\n" +
                    "\twhere products.iduser=users.id \n" +
                    "\tand products.idcategory=categories.id \n" +
                    "\tand products.name like CONCAT( '%',?,'%') \n" +//de aici modific dac avreau sa afiseze altceva
                    "\torder by users.username asc ";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setString(1, searchParam);


            ResultSet rs = pSt.executeQuery();//executa queriul iar cat timp am rezultate retunreaza true


            while (rs.next()) {//rs este ce imi returneaza baza de date

                String name = rs.getString("name").trim();
                String desc = rs.getString("description").trim();
                double price = rs.getDouble("price");

                String vendorname = rs.getString("vendorname").trim();
                String categoryname = rs.getString("categoryname").trim();
                long id = rs.getLong("id");

                ProductDisplay p = new ProductDisplay(name, id, desc, String.valueOf(price), vendorname, categoryname);//aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                lp.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lp;
    }
    public List<ProductDisplay> readIdForOrder(Long idUser) {
        List<ProductDisplay> lpid = new ArrayList<>();

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

            String q = "SELECT basket.idproduct " +
                    "FROM basket " +
                    "WHERE iduser = ?";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, idUser);

            ResultSet rs = pSt.executeQuery();

            //executa queriul iar cat timp am rezultate retunreaza true

            while (rs.next()) {//rs este ce imi returneaza baza de date

                Long id = rs.getLong("idproduct");

                ProductDisplay pId = new ProductDisplay(id);//aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                lpid.add(pId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lpid;
    }

    public List<ProductDisplay> readAllIdProduct() {
        List<ProductDisplay> lpId = new ArrayList<>();

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

            String q = "select products.id as id\n" +
                    "\tfrom products\n";

            PreparedStatement pSt = conn.prepareStatement(q);

            ResultSet rs = pSt.executeQuery();//executa queriul iar cat timp am rezultate retunreaza true


            while (rs.next()) {//rs este ce imi returneaza baza de date

                long id = rs.getLong("id");

                ProductDisplay p = new ProductDisplay(id);//aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                lpId.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lpId;
    }
    public List<ProductDisplay> readAllProductsForServlet() {
        List<ProductDisplay> lp = new ArrayList<>();

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

            String q = "SELECT products.name AS name, products.price AS price " +
                    "FROM products " +
                    "ORDER BY products.name ASC";

            PreparedStatement pSt = conn.prepareStatement(q);

            ResultSet rs = pSt.executeQuery();//executa queriul iar cat timp am rezultate retunreaza true


            while (rs.next()) {//rs este ce imi returneaza baza de date

                String name = rs.getString("name").trim();
                double price = rs.getDouble("price");


                ProductDisplay p = new ProductDisplay(name, String.valueOf(price));//aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                lp.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lp;
    }


}
