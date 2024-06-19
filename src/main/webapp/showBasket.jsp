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
        #showBasket {
            margin-bottom: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>

<h1>Cosul de cumparaturi</h1>

<div id="showBasket"></div>
<table id="obiect">
    <thead>
    <tr>
        <th>ID ul produsului</th>
        <th>Denumirea Produsului</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script>
    $(document).ready(function() {
        showBasket();
    });
</script>

<%--<input type="button" id="delete" value="Delete all" onClick="deleteAll()" />--%>
<p>
    <input type="button" id="delete" value="Delete Selected" onClick="deleteSelected()" />
</p>
<p>
<a href ="buyerMenu.jsp">Inapoi la meniul principal</a>
</p>
</body>
</html>
