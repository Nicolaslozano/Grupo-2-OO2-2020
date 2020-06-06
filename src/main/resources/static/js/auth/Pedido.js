$(document).ready(function () {
    $("#form-pedido-create").submit(function (event) {
        event.preventDefault();
        var pedidoModel = {};

        pedidoModel["idCliente"] = $("#idCliente").val();
        pedidoModel["idProducto"] = $("#producto").val();
        pedidoModel["cantidad"] = $("#cantidad").val();
        pedidoModel["idLocal"] = $("#local").val();
        pedidoModel["idVendedorOriginal"] = $("#vendedorOriginal").val();

        submitPedido(pedidoModel);
    });
});

function submitPedido(pedidoModel) {

    pedidoJson = JSON.stringify(pedidoModel);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/pedido/sendPedido",
        data: pedidoJson,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;

            if (json.order_accepted) {

                $("#feedback").html(json.order_accepted);
                $("#feedback").addClass("alert alert-success");

                $("#feedback-buttons").append($("<a></a>",
                    { "href": json.redirect, "text": "Volver", "class": "btn btn-primary" }));

            } else if (json.order_rejected) {

                $("#feedback").html(json.order_rejected);
                $("#feedback").addClass("alert alert-danger");

                $("#feedback-buttons").append($("<a></a>",
                    { "href": json.redirect, "text": "Volver", "class": "btn btn-primary" }));

                $.ajax({

                    type: "POST",
                    contentType: "application/json",
                    url: "/api/local/getValidLocals",
                    data: pedidoJson,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        var locales = data;
                        if (typeof (data) == "string") {
                            locales = JSON.parse(data);
                        }
                        $("#modalValidLocals").modal('show');

                        $.each(locales, function (index, local) {
                            $("#modalValidLocals-list").append($("<li></li>", { "text": local.direccion + " - " + local.distancia + "Km." }));
                        });

                    },
                    error: function (result) {
                        console.log(["error getting locales validos", result]);
                    }
                });
            }

        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            console.log(json);
        },
    });

}