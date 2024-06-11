package MenuOptions;

import MainController.emag;
import controller.CategoryManagementService;
import controller.ProductManagementService;
import controller.UserManagementService;
import db.DbBasketOperations;
import entity.CategoryDisplay;
import entity.Product;
import entity.ProductDisplay;
import entity.User;

import java.util.List;
import java.util.Scanner;

public class SellerMenu {
    public static void showMenuOptions(Long idUser) {
        Scanner scanner = new Scanner(System.in);
        int userMenuOption;

        do {
            //aici user e deja logat si alege din meniu ce vrea sa faca
            System.out.println("Menu pentru vanzator:");    //aici afisam pagina de "Home";
            System.out.println("1. Adauga un produs");
            System.out.println("2. Afiseaza lista userilor");
            System.out.println("3. Afiseaza lista produselor");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            userMenuOption = scanner.nextInt();
            DbBasketOperations dbb = new DbBasketOperations();
            boolean b = false;

            switch (userMenuOption) {
                case 1:
                    addProducts(idUser);
                    showMenuOptions(idUser);
                case 2:
                    showAllUsers(b);
                    showMenuOptions(idUser);
                case 3:
//                    showAllProducts();
                    showMenuOptions(idUser);
                case 0:
                    endProgram();
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (userMenuOption != 0);
        emag.start();
    }

//    public static void showAllProducts() {
//        System.out.println("Aceasta este lista de produse");
//        // list products
//        ProductManagementService pms = new ProductManagementService();
//        List<ProductDisplay> lp = pms.showAllProducts();
//        for (ProductDisplay p : lp) {
//            System.out.println(p);
//        }
//    }
    public static void showAllUsers(boolean b) {
        int codUnic = 1234;
        System.out.println("Te rog sa introduci codul unic: ");
        Scanner sca = new Scanner(System.in);
        int codIntrodus = sca.nextInt();
        if (codIntrodus == codUnic) {
            b = true;
            UserManagementService ums = new UserManagementService();
            List<User> lu = ums.ShowAllUsers(b);

            for (User u : lu) {
                System.out.println(u);
            }
        } else {
            System.out.println("Codul introdus nu este corect!");
        }
    }

    public static void addProducts(Long idUser) {

        //aici trebuie sa adaugam partea de sout si sa completeze de la tastatura toate campurile necesare adaugarii produsului in DB
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Te rugam sa introduci datele necesare pentru a adauga produsul in cos.");
            System.out.println("nume produs: ");
            String name = sc.nextLine();
            System.out.println("descriere produs: ");
            String description = sc.nextLine();
            System.out.println("pret produs: ");
            Double price = Double.parseDouble(sc.nextLine());

            showAllCategories();
            System.out.println("categorie produs: ");
            Long idCategoryIntrodus = Long.parseLong(sc.nextLine());
            Long idCategory;
            //trec prin toata lista de idCategory si daca gasesc idCategory introdus, il fac egal cu idCategory si merg mai departe

            if (true)
                idCategory = idCategoryIntrodus;
            Long iduser = idUser;
            Product p = new Product(name, description, price, iduser, idCategory);

            ProductManagementService pms = new ProductManagementService();
            pms.insert(p);
            System.out.println("Produsul a fost adaugat cu succes!");
        } catch (NumberFormatException e) {
            System.out.println("Pretul nu poate contine litere.");
            addProducts(idUser);
        }
    }

    public static void showAllCategories() {
        System.out.println("Aceasta este lista de categorii:");

        CategoryManagementService cms = new CategoryManagementService();
        List<CategoryDisplay> lc = cms.showAllCategories();

        for (CategoryDisplay c : lc) {
            System.out.println(c);
        }
    }

    public static void endProgram() {
        System.out.println("==========");
        System.out.println("O zi buna!");
        System.out.println("==========");
        emag.start();
        //System.exit(0);
    }

//    public void pretProdus(String){
//
//    }
}
