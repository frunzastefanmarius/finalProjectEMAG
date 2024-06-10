<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List my stuff</title>
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script src="actiuni.js" type="text/javascript"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .checkbox-cell {
            text-align: center;
        }
        #listOfToDo {
            margin-bottom: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>

<h1>Product List</h1>

<div id="listOfToDo"></div>
<table id="obiect">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Vendor Name</th>
        <th>Category Name</th>
        <th>Select</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script>
    $(document).ready(function() {
        showProductsJS();
    });
</script>

<p>
    <label for="name"></label>
    <input type="text" id="name" placeholder="Cauta un produs" />
    <input type="button" id="search" value="Cauta" onClick="search()" />
</p>
<p>
    <input type="button" id="add" value="Adauga in cos" onClick="addToBasket()" />
    <script>
        $(document).ready(function() {
            addToBasket();
        });
    </script>
</p>

<a href ="buyerMenu.jsp">Inapoi la meniul principal</a>
</body>
</html>
