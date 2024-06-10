function showProductsJS() {
    $.ajax({
        url: 'showProducts',
        method: 'GET',
        dataType: 'json'
    }).done(function (response) {
        display(response.listFromBackend);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Error loading products:', textStatus, errorThrown);
    });
}

function display(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.id + "</td>" +
            "<td>" + obiect.name + "</td>" +
            "<td>" + obiect.description + "</td>" +
            "<td>" + obiect.price + "</td>" +
            "<td>" + obiect.vendorName + "</td>" +
            "<td>" + obiect.categoryName + "</td>" +
            "<td class='checkbox-cell'><input type='checkbox' class='delete-checkbox' data-id='" + obiect.id + "'></td>" +
            "</tr>";
    });
    $("#obiect tbody").html(randuri);
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

function showBasket() {
    $.ajax({
        url: 'showBasket',
        method: 'GET',
        dataType: 'json'
    }).done(function (response) {
        displayBasket(response.listBasket);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        console.error('Error loading products:', textStatus, errorThrown);
    });
}
function displayBasket(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.id + "</td>" +
            "<td>" + obiect.name + "</td>" +
            "<td class='checkbox-cell'><input type='checkbox' class='delete-checkbox' data-id='" + obiect.id + "'></td>" +
            "</tr>";
    });
    $("#obiect tbody").html(randuri);
}

function deleteSelected() {
    var selectedCheckboxes = $(".delete-checkbox:checked");
    var selectedidProduct = selectedCheckboxes.map(function () {
        return $(this).data("idProduct");
    }).get();

    if (selectedidProduct.length > 0) {
        var url = "deleteFromBasket"; // Updated with the correct URL for your delete servlet
        $.ajax({
            url: url,
            method: "POST",
            data: { idProduct: selectedidProduct.join(",") }, // Send selected idProducts to the server
            dataType: "json"
        }).done(function (response) {
            location.href = "showBasket.jsp"; // Refresh the page or update as needed
        });
    }
}

function addToBasket() {
    var selectedCheckboxes = $(".delete-checkbox:checked");
    var selectedidProduct = selectedCheckboxes.map(function () {
        return $(this).data("idProduct");
    }).get();

    if (selectedidProduct.length > 0) {
        var url = "addToBasket";
        $.ajax({
            url: url,
            method: "POST",
            data: { idProduct: selectedidProduct.join(",") },
            dataType: "json"
        }).done(function (response) {
            location.href = "showBasket.jsp";
        });
    }
}




