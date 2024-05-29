<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Produse</title>
</head>
<body>
<h1>Aceasta este lista de produse:</h1>
<ul>
    <script>
        loadToDo();
    </script>
</ul>
<div class="form-container">
    <form action="buyerMenu.jsp" method="POST">
        <button type="submit" class="login-button">Inapoi la pagina principala</button>
    </form>
</div>
</body>
</html>
