function loadToDo() {
    $.ajax({
        url: 'showProducts'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
    });
}