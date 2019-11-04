function buscar() {
    $.ajax({
        url: "busca",
        type: "POST",
        data: {chave: $('#chave').val(), filtro: $('select[name=selector]').val()},
        success: function (form) {
            document.getElementById("resultado").innerHTML = form;
            //$("#teste").html(form)
        }
    });
}