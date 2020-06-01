$(document).ready(function () {

    $("#form-local-create").submit(function (event) {
        event.preventDefault();
        var localModel = {};

        localModel["direccion"] = $("#direccion").val();
        localModel["longitud"] = $("#longitud").val();
        localModel["latitud"] = $("#latitud").val();
        localModel["telefono"] = $("#telefono").val();

        submitLocal(localModel);
    });
    $("#form-local-update").submit(function (event) {
        event.preventDefault();
        var localModel = {};

        localModel["idLocal"] = $("#idLocal").val();
        localModel["direccion"] = $("#direccion").val();
        localModel["longitud"] = $("#longitud").val();
        localModel["latitud"] = $("#latitud").val();
        localModel["telefono"] = $("#telefono").val();

        submitLocal(localModel);
    });
});

function submitLocal(localModel) {
    var url;

    //si llega el id es porque estamos actualizando un cliente
    if (localModel.idLocal) {
        url = "/api/local/updateLocal";
    } else {
        url = "/api/local/createLocal";
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(localModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);

            if (json.success_created) {

                $("#direccion").val("");
                $("#longitud").val("");
                $("#latitud").val("");
                $("#telefono").val("");
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
    $("#direccion").removeClass("is-invalid");
    $("#latitud").removeClass("is-invalid");
    $("#longitud").removeClass("is-invalid");
    $("#telefono").removeClass("is-invalid");

    $("#feedback > div").html("");
    $("#feedback > div").removeClass("alert alert-danger");
    $("#feedback > div").removeClass("alert alert-success");

    if (errors.address_required) {
        $("#direccion").addClass("is-invalid");
    }

    if (errors.latitude_error) {
        $("#latitud").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.latitude_error);
    }

    if (errors.longitude_error) {
        $("#longitud").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.longitude_error);
    }
    if (errors.telephone_required) {
        $("#telefono").addClass("is-invalid");
    }

}