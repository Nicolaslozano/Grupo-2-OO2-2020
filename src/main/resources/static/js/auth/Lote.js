$(document).ready(function () {
    $("#form-lote-create").submit(function (event) {
        event.preventDefault();
        var loteModel = {};

        loteModel["cantidadInicial"] = $("#cantidadInicial").val();
        loteModel["idProducto"] = $("#producto").val();
        loteModel["idStock"] = $("#local").val();

        submitLote(loteModel);
    });
    $("#form-lote-update").submit(function (event) {
        event.preventDefault();
        var loteModel = {};

        loteModel["idLote"] = $("#idLote").val();
        loteModel["cantidadInicial"] = $("#cantidadInicial").val();
        loteModel["cantidadActual"] = $("#cantidadActual").val();
        loteModel["idProducto"] = $("#producto").val();
        loteModel["idStock"] = $("#local").val();

        submitLote(loteModel);
    });
});

function submitLote(loteModel) {

    var url;

    if (loteModel.idPersona) {
        url = "/api/lote/updateLote";
    } else {
        url = "/api/lote/createLote";
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(loteModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);

            if (json.success_created) {

                $("#cantidadInicial").val("0");

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

    $("#idLote").removeClass("is-invalid");
    $("#cantidadInicial").removeClass("is-invalid");
    $("#apellido").removeClass("is-invalid");
    $("#dni").removeClass("is-invalid");
    $("#fechaNacimiento").removeClass("is-invalid");
    $("#franjaHoraria").removeClass("is-invalid");

    $("#feedback > div").html("");
    $("#feedback > div").removeClass("alert alert-danger");
    $("#feedback > div").removeClass("alert alert-success");

    if (errors.local_required) {
        $("#local").addClass("is-invalid");
    }
    if (errors.quantity_required) {
        $("#cantidadInicial").addClass("is-invalid");
    }
    if (errors.product_required) {
        $("#producto").addClass("is-invalid");
    }
}