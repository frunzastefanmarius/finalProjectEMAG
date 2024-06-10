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

// function showBasket() {
//     $.ajax({
//         url: 'showBasket',
//         method: 'GET',
//         dataType: 'json'
//     }).done(function (response) {
//         displayBasket(response.listBasket);
//     }).fail(function(jqXHR, textStatus, errorThrown) {
//         console.error('Error loading products:', textStatus, errorThrown);
//     });
// }
// function displayBasket(lista) {
//     var randuri = "";
//     lista.forEach(function (obiect) {
//         randuri += "<tr>" +
//             "<td>" + obiect.id + "</td>" +
//             "<td>" + obiect.name + "</td>" +
//             "<td class='checkbox-cell'><input type='checkbox' class='delete-checkbox' data-id='" + obiect.id + "'></td>" +
//             "</tr>";
//     });
//     $("#obiect tbody").html(randuri);
// }

