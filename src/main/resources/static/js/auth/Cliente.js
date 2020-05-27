$(document).ready(function () {
    $("#form-cliente-create").submit(function (event) {
        event.preventDefault();
        var clienteModel = {};

        clienteModel["nombre"] = $("#nombre").val();
        clienteModel["apellido"] = $("#apellido").val();
        clienteModel["dni"] = $("#dni").val();
        clienteModel["email"] = $("#email").val();
        clienteModel["fechaNacimiento"] = $("#fechaNacimiento").val();

        submitCliente(clienteModel);
    });
    $("#form-cliente-update").submit(function (event) {
        event.preventDefault();
        var clienteModel = {};

        clienteModel["idPersona"] = $("#idPersona").val();
        clienteModel["nombre"] = $("#nombre").val();
        clienteModel["apellido"] = $("#apellido").val();
        clienteModel["dni"] = $("#dni").val();
        clienteModel["email"] = $("#email").val();
        clienteModel["fechaNacimiento"] = $("#fechaNacimiento").val();

        submitCliente(clienteModel);
    });
});

function submitCliente(clienteModel) {
    var url;

    //si llega el id es porque estamos actualizando un cliente
    if (clienteModel.idPersona) {
        url = "/api/cliente/updateCliente";
    } else {
        url = "/api/cliente/createCliente";
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(clienteModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);

            if (json.success_created) {

                $("#nombre").val("");
                $("#apellido").val("");
                $("#dni").val("");
                $("#email").val("");
                $("#fechaNacimiento").val("");
                $("#feedback > div").html(json.success_created);
            } else if (json.success_updated) {

                $("#feedback > div").html(json.success_updated);
            }

            $("#feedback > div").addClass("alert alert-success");
        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            controlError(json);
        },
    });
}

function controlError(errors) {
    $("#email").removeClass("is-invalid");
    $("#nombre").removeClass("is-invalid");
    $("#apellido").removeClass("is-invalid");
    $("#dni").removeClass("is-invalid");
    $("#fechaNacimiento").removeClass("is-invalid");

    $("#feedback > div").html("");
    $("#feedback > div").removeClass("alert alert-danger");
    $("#feedback > div").removeClass("alert alert-success");

    if (errors.email_already_exists) {
        $("#email").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.email_already_exists);
    }
    if (errors.person_already_exists) {
        $("#dni").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.person_already_exists);
    }
    if (errors.email_required) {
        $("#email").addClass("is-invalid");
    }
    if (errors.surname_required) {
        $("#apellido").addClass("is-invalid");
    }
    if (errors.name_required) {
        $("#nombre").addClass("is-invalid");
    }
    if (errors.dni_required) {
        $("#dni").addClass("is-invalid");
    }
    if (errors.birthdate_required) {
        $("#fechaNacimiento").addClass("is-invalid");
    }
}
