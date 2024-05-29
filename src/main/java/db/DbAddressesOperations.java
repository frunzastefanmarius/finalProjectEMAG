package db;

import entity.Addresses;
import entity.AddressesDisplay;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbAddressesOperations {
    public boolean insert(Addresses addresses) {

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
            PreparedStatement pSt = conn.prepareStatement("insert into addresses(address, iduser) values(?, ?)");
            pSt.setString(1, addresses.getAddress());
            pSt.setLong(2, addresses.getIdUser());
            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;
    }
    public List<AddressesDisplay> readAddressOfAUser(Long idUser) {
        List<AddressesDisplay> la = new ArrayList<>();

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

            String q = "SELECT id,address,iduser FROM public.addresses\n" +
                    "\twhere iduser = ?;";
            PreparedStatement pSt = conn.prepareStatement(q);

            pSt.setLong(1, idUser);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {

                long id = rs.getLong("id");
                String address = rs.getString("address").trim();
                long iduser = rs.getLong("iduser");

                AddressesDisplay a = new AddressesDisplay(id, address, iduser);
                la.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return la;
    }
    //deleteAddressFromDB

    public boolean deleteAddressFromDB(Long idAddress) {

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

            PreparedStatement pSt = conn.prepareStatement("delete from addresses where id = ?");
            pSt.setLong(1, idAddress);

            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean ok = false;
        if (val != 0)
            ok = true;
        return ok;

    }
}
