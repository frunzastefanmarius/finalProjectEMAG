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
            "<td class='delete-checkbox'><input type='checkbox' class='delete-checkbox' data-id='" + obiect.id + "'></td>" +

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
            "<td>" + obiect.productName + "</td>" +
            "<td class='checkbox-cell'><input type='checkbox' class='delete-checkbox' data-id='" + obiect.id + "'></td>" +
            "</tr>";
    });
    $("#obiect tbody").html(randuri);
}

function deleteSelected() {
    //poate nu ia acel id product de aici de unde trebuie
    //dar nici nu afiseaza alertul de mai sus deci nu intra deloc aici?????

    var selectedCheckboxes = $(".delete-checkbox:checked");
    var selectedidProduct = selectedCheckboxes.map(function () {
        return $(this).data("id");//aici trebuie sa ramana id, de ce? nu stiu.
    }).get();
    if (selectedidProduct.length > 0) {
        alert("am selectat urmatoarele id uri de produs: " + selectedidProduct);
        var url = "deleteBasketProducts"; // Updated with the correct URL for your delete servlet
        $.ajax({
            url: url,
            method: "POST",//DELETE in loc de post
            data: { idProduct: selectedidProduct.join(",") }, // Send selected idProducts to the server
            dataType: "json"
        }).done(function (response) {
            location.href = "showBasket.jsp"; // am pus alta pagina sa vad daca intra si nu intra
                                             // pe ea cum o face in codul de mai jos de la metoda addToBasket;
        });
    }
}

function addToBasket() {
    var selectedCheckboxes = $(".delete-checkbox:checked");
    var selectedidProduct = selectedCheckboxes.map(function () {
        return $(this).data("id");
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




