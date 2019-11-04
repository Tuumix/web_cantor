function buscar() {
    $.ajax({
        url: "busca",
        type: "POST",
        data: {chave: $('#chave').val(), filtro: $('select[name=selector]').val()},
        success: function (form) {
            $("#teste").html(form)
        }
    });
}