package MenuOptions;

import MainController.emag;
import controller.*;
import db.DbBasketOperations;
import entity.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerMenu {
    public static void showMenuOptions(Long idUser) {
        Scanner scanner = new Scanner(System.in);
        int userMenuOption;

        do {
            //aici user e deja logat si alege din meniu ce vrea sa faca
            System.out.println("Menu pentru cumparator:");    //aici afisam pagina de "Home";
            System.out.println("1. Vizualizeaza lista de produse");
            System.out.println("2. Adauga un produs in cos");
            System.out.println("3. Afiseaza cosul de cumparaturi");
            System.out.println("4. Sterge un produs din cos");
            System.out.println("5. Goleste cosul de cumparaturi");
            System.out.println("6. Afiseaza categoriile");

            System.out.println("7. Adauga o adresa");
            System.out.println("8. Afiseaza adresele mele");
            System.out.println("9. Sterge o adresa");

            System.out.println("10. Plaseaza o comanda");
            System.out.println("11. Afiseaza comenzile mele");

            System.out.println("12. Afiseaza lista userilor");

            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            userMenuOption = scanner.nextInt();
            DbBasketOperations dbb = new DbBasketOperations();
            boolean b = false;

            switch (userMenuOption) {
                case 1:
//                    showAllProducts();
                    showMenuOptions(idUser);
                case 2:
                    addProductsInBasket(idUser);
                    showMenuOptions(idUser);
                case 3:
                    readBasket(idUser);
                    showMenuOptions(idUser);
                case 4:
                    deleteProductFromBasket(idUser);
                    showMenuOptions(idUser);
                case 5:
                    clearAllBassket(idUser);
                    showMenuOptions(idUser);
                case 6:
                    showAllCategories();
                    showMenuOptions(idUser);
                case 7:
                    addAddress(idUser);
                    showMenuOptions(idUser);
                case 8:
                    readAddresses(idUser);
                    showMenuOptions(idUser);
                case 9:
                    deleteAnAddress(idUser);
                    showMenuOptions(idUser);
                case 10:
                    createAnOrder(idUser);
                    showMenuOptions(idUser);
                case 11:
                    accessOrders(idUser);
                    showMenuOptions(idUser);
                case 12:
                    showAllUsers(b);
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

    public static void addProductsInBasket(Long idUser) {
        System.out.println("Adauga produse in cos:");
        Scanner sc = new Scanner(System.in);
        System.out.print("ce id pui in cos:");
        long idprod = sc.nextLong();
        ProductManagementService pms = new ProductManagementService();
        List<ProductDisplay> listAllProductObj = pms.idProductForAddInBasket();
        List<Long> listAllProductLong = new ArrayList<>();

        for (ProductDisplay obj : listAllProductObj){
            listAllProductLong.add((long) obj.getId());
        }

        if(listAllProductLong.contains(idprod)){

        //creez o lista cu id de produse si daca idprod introdus este egal cu un element din lista,
        //sa se exectute daca nu sa spuna ca nu e si apeleze buyerMenu

        //creez o lista cu id de produse si daca idprod introdus este egal cu un element din lista,
        //sa se exectute daca nu sa spuna ca nu e si apeleze buyerMenu

        Basket b = new Basket(idUser, idprod);

        BasketManagementService bms = new BasketManagementService();
        bms.insertInBasket(b);
        readBasket(idUser);
        }else {
            System.out.println("Nu ati introdus un id de produs.");
            showMenuOptions(idUser);
        }

    }

    public static void readBasket(Long idUser) {
        BasketManagementService bms = new BasketManagementService();
        List<BasketDisplay> lb = bms.readBasket(idUser);
        if(!lb.isEmpty()){
            for (BasketDisplay basketOfCurrentUser : lb) {
                System.out.println(basketOfCurrentUser);
            }
            showMenuOptions(idUser);
        }else {
            System.out.println("Cosul de cumparaturi este gol");
        }
    }

    public static void deleteProductFromBasket(Long idUser) {
        //readBasket(idUser);
        BasketManagementService bms = new BasketManagementService();
        List<BasketDisplay> lb = bms.readBasket(idUser);
        if (!lb.isEmpty()) {
            System.out.print("Ce id sterg din cos:");
            Scanner sca = new Scanner(System.in);
            Long idCosDeSters = sca.nextLong();

            bms.deleteProductFromBasket(idCosDeSters);
            System.out.println("Produsul a fost sters. Cosul dumneavoastra este: ");

        } else {
            System.out.println("Nu aveti produse in cos");
        }
    }
    public static void clearAllBassket(Long idUser){
        BasketManagementService bms = new BasketManagementService();
        List<BasketDisplay> lb = bms.readBasket(idUser);
        if(!lb.isEmpty()){
            bms.deleteAllFromBasket(idUser);
            showMenuOptions(idUser);
        }else {
            System.out.println("Nu ai produse in cos");
            showMenuOptions(idUser);
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

    public static void addAddress(Long idUser){
        Scanner sca = new Scanner(System.in);
        System.out.println("Te rugam sa introduci adresa: ");
        String address = sca.nextLine();
        if(address!=null){
        Addresses address1 = new Addresses(address,idUser);
        AddressesManagementService ams = new AddressesManagementService();
        ams.insertInAddresses(address1);
            System.out.println("Adresa a fost adaugata.");
        } else {
            System.out.println("Acest camp nu poate fi gol.");
            showMenuOptions(idUser);
        }
    }

    public static void deleteAnAddress(Long idUser) {
        Scanner sca = new Scanner(System.in);
        readAddresses(idUser);
        System.out.println("Te rugam sa introduci id ul adresei pe care vrei sa o stergi: ");
        Long idAdresaDeSters = sca.nextLong();
        AddressesManagementService ams = new AddressesManagementService();
        ams.deleteAddress(idAdresaDeSters);
        System.out.println("Adresa a fost stearsa.");
        readAddresses(idUser);
        showMenuOptions(idUser);

    }
    public static void readAddresses(Long idUser){
        AddressesManagementService ams = new AddressesManagementService();
        List<AddressesDisplay> la = ams.readAddresses(idUser);
        if(!la.isEmpty()){
            System.out.println("Adresele dumneavoastra sunt: ");
            for (AddressesDisplay a : la) {
                System.out.println(a);
            }
        }else {
            System.out.println("Nu aveti adrese salvate.");
        }
    }

    public static void createAnOrder(Long idUser) {
        System.out.println("Doresti sa plasezi o comanda cu produsele pe care le ai in cos?");
        System.out.println("1. Da");
        System.out.println("2. Nu");
        Scanner sca = new Scanner(System.in);

        int raspuns = sca.nextInt();
        if (raspuns == 1 || raspuns == 2) {
            if (raspuns == 1) {
                boolean b = true;
                ProductManagementService pms = new ProductManagementService();
                BasketManagementService bms = new BasketManagementService();
                List<ProductDisplay> lpid = pms.idProductForOrder(idUser);
                OrdersManagementService oms = new OrdersManagementService();
                List<BasketDisplay> lb = bms.readBasket(idUser);

                if (!lb.isEmpty()) {
                    boolean delivery = true;

                    System.out.println("Doriti livrare?");
                    System.out.println("1. Da");
                    System.out.println("2. Nu");
                    int var = sca.nextInt();
                    if (var == 1 || var == 2){
                        if (var == 2){
                            delivery = false;
                        }
                    }else {
                        System.out.println("Nu ati ales un numar corect");
                        showMenuOptions(idUser);
                    }
                    boolean payment = true;
                    System.out.println("Plata cu cardul?");
                    System.out.println("1. Da");
                    System.out.println("2. Nu");
                    int variabila = sca.nextInt();
                    if (variabila == 1 || variabila == 2){
                        if (variabila == 2){
                            payment = false;
                        }
                    }else {
                        System.out.println("Nu ati ales un numar corect");
                        showMenuOptions(idUser);
                    }

                    for (BasketDisplay basket :lb) {

                        //aici pun datele pentru a crea un obiect order pe care sa il trimit mai departe

                        Timestamp creationDateTime = new Timestamp(System.currentTimeMillis());


                        Long idUserForOrder = idUser;

                        if(!lpid.isEmpty()) {
                            ProductDisplay primulElementDinListaDeIdProducts = lpid.remove(0);
                            Long idProductForOrder = primulElementDinListaDeIdProducts.getId();

                            Order order = new Order(creationDateTime, delivery, payment, idUserForOrder, idProductForOrder);
                            oms.insertInOrder(order);
                        }
                        System.out.println("dedebuug");
                    }
                    System.out.println("de debuug 2");

                    //aici facem o metoda care trimite la un fisier text toate datele din metoda elegant.

                    System.out.println("Comanda a fost plasata cu succes!");
                    bms.deleteAllFromBasket(idUser);
                    accessOrders(idUser);
                } else {
                    System.out.println("Nu ai produse in cos.");
                    showMenuOptions(idUser);
                }
            } else {
                showMenuOptions(idUser);
            }
        }
        else {
            System.out.println("Nu ai introdus un numar vlaid");
            showMenuOptions(idUser);
        }
    }

    public static void accessOrders(Long idUser){
        OrdersManagementService oms = new OrdersManagementService();
        List<OrderDisplay> lo = oms.showOrder(idUser);
        if(!lo.isEmpty()){
            System.out.println("comenzile dumneavoastra sunt sunt: ");
            for (OrderDisplay o : lo) {
                System.out.println(o);
            }
        }else {
            System.out.println("Nu ai o comanda plasata.");
        }

    }
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
