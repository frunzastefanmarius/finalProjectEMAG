package db;

import entity.CategoryDisplay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCategoryOperations {

    public List<CategoryDisplay> readAllCategory() {
        List<CategoryDisplay> lc = new ArrayList<>();

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

            String q = "SELECT * FROM public.categories\n" +
                    "ORDER BY id ASC ";
            PreparedStatement pSt = conn.prepareStatement(q);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {

                Long id = rs.getLong("id");
                String numeCategorie = rs.getString("name").trim();
                String descriereCategorie = rs.getString("description").trim();


                CategoryDisplay c = new CategoryDisplay(id, numeCategorie, descriereCategorie);//aici creaza un obiect de tipul ce vreau eu, si dupa il adauga in lista pe care o returneaza metoda.
                lc.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lc;
    }
}
