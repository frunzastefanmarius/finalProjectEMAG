function newToDo() {
    var name = document.getElementById('name').value;
    var urlEnc = encodeURI('addfood?productName=' + name);
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "showProducts.jsp";
    });
}

function deleteSelected() {
    var selectedCheckboxes = $(".delete-checkbox:checked");
    var selectedproductNames = selectedCheckboxes.map(function () {
        return $(this).data("productName");
    }).get();

    if (selectedproductNames.length > 0) {
        var url = "deletefood"; // Update with the correct URL for your delete servlet
        $.ajax({
            url: url,
            method: "POST",
            data: { productNames: selectedproductNames.join(",") }, // Send selected food names to the server
            dataType: "json"
        }).done(function (response) {
            location.href = "showProducts.jsp"; // Refresh the page or update as needed
        });
    }
}


function loadToDo() {
    $.ajax({
        url: 'showProducts'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }

function display(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.productName + "</td>" +
            "<td>" + obiect.productPrice + "</td>" +
            "<td><input type='checkbox' class='delete-checkbox' data-productName='" + obiect.productName + "'></td>" +
            "</tr>";
    });
    $("#obiect").html(randuri);
}

function search(myText) {
    $.ajax("showProducts", {
        cache: false,
        dataType: "json",
        data: {
            // order: ordinea,
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


function printOnDiv(listFromBackend) {
    var listHtml = '';

    var list = document.getElementById('listOfToDo');

    for (var i = 0; i < listFromBackend.length; i++) {
        var elemC = listFromBackend[i];
        var el = '<li>'+elemC.productName+' '+elemC.productPrice+'</li>';
        listHtml=listHtml+el;
    }
    list.innerHTML = '<ol>'+listHtml+'</ol>';
}


