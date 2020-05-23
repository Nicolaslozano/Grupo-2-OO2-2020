$(document).ready(function () {

    $("#contact_form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        ajaxSubmit();

    });

});

function ajaxSubmit() {

    var clienteModel = {};

    clienteModel["nombre"] = $("#nombre").val();
    clienteModel["apellido"] = $("#apellido").val();
    clienteModel["dni"] = $("#dni").val();
    clienteModel["email"] = $("#email").val();
    clienteModel["fechaNacimiento"] = $("#fechaNacimiento").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/cliente/createCliente",
        data: JSON.stringify(clienteModel),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data) {
            var json = "<h4>Ajax Response succ</h4>&lt;pre&gt;"
            + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);
        }
    });

}