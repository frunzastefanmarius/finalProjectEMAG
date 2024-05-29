<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buyer Menu</title>
</head>
<body>

<%
    HttpSession s = request.getSession();
    Long idUser = (Long)s.getAttribute("idUser");
    if(idUser==null)
    {
        response.sendRedirect("login.html");
    }
%>

<h1>Menu pentru cumparator:</h1>
<ul>
    <li><a href="showProducts">Vizualizeaza lista de produse</a></li>
    <li><a href="addProduct.html">Adauga un produs in cos</a></li>
    <li><a href="viewBasket">Afiseaza cosul de cumparaturi</a></li>
    <li><a href="deleteProduct">Sterge un produs din cos</a></li>
    <li><a href="clearBasket">Goleste cosul de cumparaturi</a></li>
    <li><a href="viewCategories">Afiseaza categoriile</a></li>
    <li><a href="addAddress">Adauga o adresa</a></li>
    <li><a href="viewAddresses">Afiseaza adresele mele</a></li>
    <li><a href="deleteAddress">Sterge o adresa</a></li>
    <li><a href="placeOrder">Plaseaza o comanda</a></li>
    <li><a href="viewOrders">Afiseaza comenzile mele</a></li>
    <li><a href="viewUsers">Afiseaza lista userilor</a></li>
    <li><a href="logout">Exit</a></li>

</ul>
</body>
</html>