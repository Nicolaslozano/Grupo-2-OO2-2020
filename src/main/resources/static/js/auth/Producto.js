$(document).ready(function () {
    $(".form-producto-remove").submit(function (event) {
        event.preventDefault();

        var dataArray = $(this).serializeArray(),
        productoModel = {};

        $(dataArray).each(function(i, field){
        productoModel[field.name] = field.value;
        });

        removeProducto(prouctoModel);
    });
    $("#form-producto-create").submit(function (event) {
        event.preventDefault();
        var productoModel = {};

        productoModel["nombre"] = $("#nombre").val();
        productoModel["descripcion"] = $("#descripcion").val();
        productoModel["precio"] = $("#precio").val();
       

        submitProducto(productoModel);
    });
    $("#form-producto-update").submit(function (event) {
        event.preventDefault();
        var productoModel = {};

        productoModel["idProducto"] = $("#idProducto").val();
        productoModel["nombre"] = $("#nombre").val();
        productoModel["descripcion"] = $("#descripcion").val();
        productoModel["precio"] = $("#precio").val();

        submitProducto(productoModel);
    });
});

function removeProducto(productoModel) {

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify(empleadoModel),
        url: "/api/producto/remove/" + productoModel.idProducto,
        cache: false,
        timeout: 600000,
        success: function (data) {

            if(data.success_removed) {

                window.location.replace(data.redirect);
            }
        },
    });
}

function submitProducto(productoModel) {

    var url;

    //si llega el id es porque estamos actualizando un cliente
    if (productoModel.idProducto) {
        url = "/api/producto/updateProducto";
    } else {
        url = "/api/producto/createProducto";
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(productoModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);

            if (json.success_created) {

                $("#nombre").val("");
                $("#descripcion").val("");
                $("#precio").val("");

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

    $("#idProducto").removeClass("is-invalid");
    $("#nombre").removeClass("is-invalid");
    $("#decripcion").removeClass("is-invalid");
    $("#precio").removeClass("is-invalid");

    $("#feedback > div").html("");
    $("#feedback > div").removeClass("alert alert-danger");
    $("#feedback > div").removeClass("alert alert-success");


    if (errors.product_already_exists) {
        $("#nombre").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.product_already_exists);
    }
   
    if (errors.price_required) {
        $("#precio").addClass("is-invalid");
    }
    if (errors.name_required) {
        $("#nombre").addClass("is-invalid");
    }
    if (errors.description_required) {
        $("#descripcion").addClass("is-invalid");
    }
}