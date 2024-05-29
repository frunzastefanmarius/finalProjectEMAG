package MainController;

import MenuOptions.BuyerMenu;
import MenuOptions.SellerMenu;
import controller.UserManagementService;
import entity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class emag {
    Long idUser = -1L;//daca nu iese asa trimit parametrul idUser prin metoda
    //nu a iesit asa pentru ca am metode statice

    public static void start() {

        Scanner scanner = new Scanner(System.in);
        String input = null;
        do {
            System.out.println("Alegeti una din urmatoarele actiuni");
            System.out.println("1. Inregistrare");
            System.out.println("2. Logare");
            System.out.println("-------------------");

            try {
                input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        register();
                        start();
                    case 2:
                        login();
                        start();
                    default:
                        System.out.println("Nu ai ales o optiune valida.");
                        start();
                }
            } catch (NumberFormatException e) {
                System.out.println("Nu ai ales una din actiunile disponibile");
                start();
            }
        } while (input.equals(0));
    }

    public static void register() {
        boolean existentUser = false;
        System.out.println("Bine ai venit! Hai sa ne inregistram");

        do {
            Long idUser = null;
            existentUser = false;
            String usernameIntrodus;
            String pwdIntrodus;
            String username = null;
            String pwd = null;
            boolean buyer = false;
            try {
                System.out.println("Te rugam sa introduci un username si o parola: ");
                Scanner sc = new Scanner(System.in);
                System.out.print("New Username: ");
                usernameIntrodus = sc.nextLine();
                System.out.print("Pwd: ");
                pwdIntrodus = sc.nextLine();

                System.out.println("Selecteaza tipul de cont pe care doresti sa il creezi: ");
                System.out.println("1. Vanzator.");
                System.out.println("2. Cumparator. ");
                int tipUser = Integer.parseInt(sc.nextLine());
                if (tipUser == 1 || tipUser == 2) {
                    if (tipUser == 1) {
                        buyer = false;
                    } else buyer = true;
                } else {
                    System.out.println("nu ai ales un numar valid");
                    start();
                }


                if (usernameIntrodus.matches("[a-zA-Z]+")) {
                    username = usernameIntrodus;
                } else {
                    System.out.println("Username poate contine doar litere mari, litere mici si nu pot exista spatii. Te rugam sa incerci din nou");
                    start();
                }
                if (pwdIntrodus.length() < 8 && pwdIntrodus.contains(" ")) {
                    System.out.println("Parola trebuie sa fie mai lunga de 8 caractere si sa nu contina spatii.");
                    start();
                } else {
                    pwd = pwdIntrodus;
                }

                User u = new User(username, pwd, buyer);

                try {
                    UserManagementService ums = new UserManagementService();
                    ums.register(u);
                    System.out.println("Felicitari! Te-ai inregistrat cu succes, " + username + "!");
                } catch (SQLException e) {
                    existentUser = true;
                    System.out.println("Ne pare rau dar username " + username + " nu este disponibil. Te rugam sa incerci altul.");
                    start();
                }
            } catch (NumberFormatException e) {
                System.out.println("Ati introdus un text invalid.");
                start();
            }

        }
        while (existentUser);
    }

    public static Long login() {
        boolean loginSuccessfull = false;
        Long idUser = null;
        int attempts = 0;
        int maxAttempts = 3;
        String username;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Login User:");
            username = sc.nextLine();
            System.out.print("Pwd:");
            String pwd = sc.nextLine();
            User u = new User(username, pwd);
            UserManagementService ums = new UserManagementService();
            idUser = ums.login(u);

            if (loginSuccessfull) {
                attempts++;
                System.out.println("Mai aveti " + (maxAttempts - attempts) + " incercari.");
            }

        }
        while (idUser == null && attempts < maxAttempts);

        if (idUser == null) {
            System.out.println("Ati atins numarul maxim de incercari.");
        } else {
            loginSuccessfull = true;
            System.out.println("Salut " + username + "! Bine ai venit!");
        }

        if (loginSuccessfull) {
            rulareMeniu(idUser);
        }
        return idUser;
    }
    public static void rulareMeniu(Long idUser){
        UserManagementService ums = new UserManagementService();
        if (ums.checkUserType(idUser)){
            BuyerMenu.showMenuOptions(idUser);
        }
        else SellerMenu.showMenuOptions(idUser);
    }

}
