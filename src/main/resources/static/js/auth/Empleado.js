$(document).ready(function () {
    $("#form-empleado-create").submit(function (event) {
        event.preventDefault();
        var empleadoModel = {};

        empleadoModel["nombre"] = $("#nombre").val();
        empleadoModel["apellido"] = $("#apellido").val();
        empleadoModel["dni"] = $("#dni").val();
        empleadoModel["fechaNacimiento"] = $("#fechaNacimiento").val();
        empleadoModel["franjaHoraria"] = $("#franjaHoraria").val();
        empleadoModel["idLocal"] = $("#idLocal").val();

        submitEmpleado(empleadoModel);
    });
    $("#form-empleado-update").submit(function (event) {
        event.preventDefault();
        var empleadoModel = {};

        empleadoModel["idPersona"] = $("#idPersona").val();
        empleadoModel["nombre"] = $("#nombre").val();
        empleadoModel["apellido"] = $("#apellido").val();
        empleadoModel["dni"] = $("#dni").val();
        empleadoModel["idLocal"] = $("#idLocal").val();
        empleadoModel["fechaNacimiento"] = $("#fechaNacimiento").val();
        empleadoModel["franjaHoraria"] = $("#franjaHoraria").val();
        empleadoModel["tipoEmpleado"] = $("#tipoEmpleado").val();

        submitEmpleado(empleadoModel);
    });
});

function submitEmpleado(empleadoModel) {

    var url;

    //si llega el id es porque estamos actualizando un cliente
    if (empleadoModel.idPersona) {
        url = "/api/empleado/updateEmpleado";
    } else {
        url = "/api/empleado/createEmpleado";
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(empleadoModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);

            if (json.success_created) {

                $("#nombre").val("");
                $("#apellido").val("");
                $("#dni").val("");
                $("#fechaNacimiento").val("");
                $("#franjaHoraria").val("");

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

    $("#idLocal").removeClass("is-invalid");
    $("#nombre").removeClass("is-invalid");
    $("#apellido").removeClass("is-invalid");
    $("#dni").removeClass("is-invalid");
    $("#fechaNacimiento").removeClass("is-invalid");
    $("#franjaHoraria").removeClass("is-invalid");

    $("#feedback > div").html("");
    $("#feedback > div").removeClass("alert alert-danger");
    $("#feedback > div").removeClass("alert alert-success");


    if (errors.person_already_exists) {
        $("#dni").addClass("is-invalid");
        $("#feedback > div").addClass("alert alert-danger");
        $("#feedback > div").html(errors.person_already_exists);
    }
    if (errors.local_required) {
        $("#idLocal").addClass("is-invalid");
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
    if (errors.franjahoraria_required) {
        $("#franjaHoraria").addClass("is-invalid");
    }
}