$(document).ready(function () {
    $("#form-pedido-create").submit(function (event) {
        event.preventDefault();
        var pedidoModel = {};

        pedidoModel["idCliente"] = $("#idCliente").val();
        pedidoModel["idProducto"] = $("#producto").val();
        pedidoModel["cantidad"] = $("#cantidad").val();
        pedidoModel["idLocal"] = $("#local").val();
        pedidoModel["idVendedorOriginal"] = $("#vendedorOriginal").val();
        pedidoModel["idVendedorAuxiliar"] = $("#idVendedorAuxiliar").val();

        submitPedido(pedidoModel);
    });
    $("#form-pedido-create-pending").submit(function (event) {
        event.preventDefault();

        var pedidoModel = {};

        pedidoModel["idCliente"] = $("#idCliente").val();
        pedidoModel["idProducto"] = $("#producto").val();
        pedidoModel["cantidad"] = $("#cantidad").val();
        pedidoModel["idLocal"] = $("#local").val();
        pedidoModel["idVendedorOriginal"] = $("#vendedorOriginal").val();
        pedidoModel["idVendedorAuxiliar"] = $("#vendedorAuxiliar").val();
        pedidoModel["estado"] = $("#estado").val();

        submitPedido(pedidoModel);
        $("#modalStockExterno").modal("hide");
    });
    $("#form-pedido-update").submit(function (event) {
        event.preventDefault();
        var pedidoModel = {};

        pedidoModel["idPedido"] = $("#idPedido").val();
        pedidoModel["idCliente"] = $("#idCliente").val();
        pedidoModel["idProducto"] = $("#producto").val();
        pedidoModel["cantidad"] = $("#cantidad").val();
        pedidoModel["idLocal"] = $("#local").val();
        pedidoModel["idVendedorOriginal"] = $("#vendedorOriginal").val();
        pedidoModel["idVendedorAuxiliar"] = $("#vendedorAuxiliar").val();
        pedidoModel["estado"] = $("#estado").val();

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

                $("#feedback-buttons").append(
                    $("<a></a>", {
                        href: json.redirect,
                        text: "Volver",
                        class: "btn btn-primary",
                    })
                );
            } else if (json.order_rejected) {
                $("#feedback").html(json.order_rejected);
                $("#feedback").addClass("alert alert-danger");

                $("#feedback-buttons").append(
                    $("<a></a>", {
                        href: json.redirect,
                        text: "Volver",
                        class: "btn btn-primary",
                    })
                );
            } else if (json.order_pending) {

                $("#feedback").html(json.order_pending);
                $("#feedback").addClass("alert alert-warning");

                $("#feedback-buttons").append(
                    $("<a></a>", {
                        href: json.redirect,
                        text: "Volver",
                        class: "btn btn-primary",
                    })
                );

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/api/local/getValidLocals",
                    data: pedidoJson,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        var locales = data;
                        if (typeof data == "string") {
                            locales = JSON.parse(data);
                        }
                        $("#modalStockExterno").modal("show");

                        $.each(locales, function (index, local) {
                            $("#localAuxiliar").append(
                                $("<option></option>", {
                                    value: local.idLocal,
                                    text: local.direccion + " - " + local.distancia + "Km.",
                                })
                            );
                        });
                    },
                    error: function (result) {
                        console.log(["error getting locales validos", result]);
                    },
                });
            }
        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            console.log(json);
            controlError(json);
        },
    });
}

function updatePedido(pedidoModel) {

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/pedido/handlePedido",
        data: JSON.stringify(pedidoModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;

        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            console.log(json);
        },
    });


}

function controlError(errors) {
    $("#local").removeClass("is-invalid");
    $("#producto").removeClass("is-invalid");
    $("#cantidad").removeClass("is-invalid");
    $("#vendedorOriginal").removeClass("is-invalid");

    if (errors.local_required) {
        $("#local").addClass("is-invalid");
    }
    if (errors.product_required) {
        $("#producto").addClass("is-invalid");
    }
    if (errors.seller_required) {
        $("#vendedorOriginal").addClass("is-invalid");
    }
    if (errors.quantity_required) {
        $("#cantidad").addClass("is-invalid");
    }
}
