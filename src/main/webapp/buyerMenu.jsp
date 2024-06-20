<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Buyer Menu</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%--%>
<%--    //HttpSession s = request.getSession();--%>
<%--    Long idUser = (Long)session.getAttribute("idUser");--%>
<%--    if(idUser==null)--%>
<%--    {--%>
<%--        response.sendRedirect("login.html");--%>
<%--    }--%>
<%--%>--%>

<%--&lt;%&ndash;daca comentez asta login ul se poate face ok.&ndash;%&gt;--%>

<%--<h1>Menu pentru cumparator:</h1>--%>
<%--<ul>--%>
<%--    <li><a href="showProducts">Vizualizeaza lista de produse</a></li>--%>
<%--    <li><a href="addProduct.html">Adauga un produs in cos</a></li>--%>
<%--    <li><a href="viewBasket">Afiseaza cosul de cumparaturi</a></li>--%>
<%--    <li><a href="deleteProduct">Sterge un produs din cos</a></li>--%>
<%--    <li><a href="clearBasket">Goleste cosul de cumparaturi</a></li>--%>
<%--    <li><a href="viewCategories">Afiseaza categoriile</a></li>--%>
<%--    <li><a href="addAddress">Adauga o adresa</a></li>--%>
<%--    <li><a href="viewAddresses">Afiseaza adresele mele</a></li>--%>
<%--    <li><a href="deleteAddress">Sterge o adresa</a></li>--%>
<%--    <li><a href="placeOrder">Plaseaza o comanda</a></li>--%>
<%--    <li><a href="viewOrders">Afiseaza comenzile mele</a></li>--%>
<%--    <li><a href="viewUsers">Afiseaza lista userilor</a></li>--%>
<%--    <li><a href="logout">Exit</a></li>--%>

<%--</ul>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            text-decoration: none;
            color: #008CBA;
            font-size: 16px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
<%--    <%--%>
<%--        HttpSession session = request.getSession();--%>
<%--        Long idUser = (Long) session.getAttribute("idUser");--%>
<%--        if (idUser == null) {--%>
<%--            response.sendRedirect("login.html");--%>
<%--        } else {--%>
<%--    %>--%>
    <h1>Menu pentru cumparator:</h1>
    <ul>
        <li><a href="showProducts.jsp">Vizualizeaza lista de produse</a></li>
        <li><a href="showBasket.jsp">Afiseaza cosul de cumparaturi</a></li>
<%--        <li><a href="deleteProduct">Sterge un produs din cos</a></li>--%>
        <li><a href="viewCategories">Afiseaza categoriile</a></li>
<%--        <li><a href="addAddress">Adauga o adresa</a></li>--%>
<%--        <li><a href="viewAddresses">Afiseaza adresele mele</a></li>--%>
<%--        <li><a href="deleteAddress">Sterge o adresa</a></li>--%>
<%--        <li><a href="placeOrder">Plaseaza o comanda</a></li>--%>
        <li><a href="order.jsp">Afiseaza comenzile mele</a></li>
<%--        <li><a href="viewUsers">Afiseaza lista userilor</a></li>--%>
        <li><a href="logout">Exit</a></li>
    </ul>

</div>
</body>
</html>
