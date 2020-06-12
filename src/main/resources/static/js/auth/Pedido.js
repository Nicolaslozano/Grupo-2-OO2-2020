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
        $("#feedback").html("Pedido pendiente de revision");
        $("#feedback").addClass("alert alert-warning");
    });
    $(".form-pedido-handle").submit(function (event) {
        event.preventDefault();

        var dataArray = $(this).serializeArray(),
        pedidoModel = {};

        $(dataArray).each(function(i, field){
            pedidoModel[field.name] = field.value;
        });

        $.ajax({
            url: "/api/pedido/getPedido/" + pedidoModel["idPedido"],
            type: 'GET',
            success: function (data) {
                var pedido = data;
                if (typeof (data) == "string") {
                    pedido = JSON.parse(data);
                }

                $("#idPedido").val(pedido["idPedido"]);
                $("#idCliente").val(pedido["idCliente"]);
                $("#idProducto").val(pedido["idProducto"]);
                $("#cantidad").val(pedido["cantidad"]);
                $("#idLocal").val(pedido["idLocal"]);
                $("#idVendedorOriginal").val(pedido["idVendedorOriginal"]);
                $("#idVendedorAuxiliar").val(pedido["idVendedorAuxiliar"]);

                $("#modalPedidoHandle").modal('show');

            },
            error: function (result) { console.log(["error", result]); },
        });

    });
    $("#form-pedido-update").submit(function (event) {
        event.preventDefault();

        var dataArray = $(this).serializeArray(),
        pedidoModel = {};

        $(dataArray).each(function(i, field){
            pedidoModel[field.name] = field.value;
        });

        updatePedido(pedidoModel);
        $("#modalPedidoHandle").modal('hide');
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

            controlError(json);

            if (json.order_accepted) {
                $("#feedback").html(json.order_accepted);
                $("#feedback").addClass("alert alert-success");

            } else if (json.order_rejected) {
                $("#feedback").html(json.order_rejected);
                $("#feedback").addClass("alert alert-danger");

            } else if (json.order_pending) {

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

                        $("#localAuxiliar").empty();

                        $("#localAuxiliar").append($("<option></option>",
                            { "value": 0, "text": "--" }));

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

            if (json.order_accepted) {
                $("#feedback").html(json.order_accepted);
                $("#feedback").addClass("alert alert-success");

            } else if (json.order_rejected) {
                $("#feedback").html(json.order_rejected);
                $("#feedback").addClass("alert alert-danger");

            }

            $("#modalPedidoResult").modal('show');

        },
        error: function (e) {
            console.log(e);
        },
    });


}

function controlError(errors) {
    $("#local").removeClass("is-invalid");
    $("#localAuxiliar").removeClass("is-invalid");
    $("#producto").removeClass("is-invalid");
    $("#cantidad").removeClass("is-invalid");
    $("#vendedorOriginal").removeClass("is-invalid");

    if (errors.local_required) {
        $("#local").addClass("is-invalid");
        $("#localAuxiliar").addClass("is-invalid");
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
