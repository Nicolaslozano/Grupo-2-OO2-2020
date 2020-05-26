$(document).ready(function () {
    $("#form-empleado-create").submit(function (event) {
        event.preventDefault();
        createEmpleado();
    });
    $("#form-empleado-update").submit(function (event) {
        event.preventDefault();
        updateEmpleado();
    });
});

function createEmpleado() {
    var empleadoModel = {};

    empleadoModel["nombre"] = $("#nombre").val();
    empleadoModel["apellido"] = $("#apellido").val();
    empleadoModel["dni"] = $("#dni").val();
    empleadoModel["fechaNacimiento"] = $("#fechaNacimiento").val();
    empleadoModel["franjaHoraria"] = $("#franjaHoraria").val();
    empleadoModel["local"]= $("#local").val();


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/empleado/createEmpleado",
        data: JSON.stringify(empleadoModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);
            $("#feedback > div").html(json.success);
            $("#feedback > div").addClass("alert alert-success");
        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            controlError(json);
        },
    });
}

function updateEmpleado() {
    var EmpleadoModel = {};

    empleadoModel["idPersona"] = $("#idPersona").val();
    empleadoModel["nombre"] = $("#nombre").val();
    empleadoModel["apellido"] = $("#apellido").val();
    empleadoModel["dni"] = $("#dni").val();
    empleadoModel["local"] = $("#local").val();
    empleadoModel["fechaNacimiento"] = $("#fechaNacimiento").val();
    empleadoModel["franjaHoraria"] = $("#franjaHoraria").val();


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/empleado/updateEmpleado",
        data: JSON.stringify(empleadoModel),
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = data;
            controlError(json);
            $("#feedback > div").html(json.success);
            $("#feedback > div").addClass("alert alert-success");
        },
        error: function (e) {
            var json = JSON.parse(e.responseText);
            controlError(json);
        },
    });
}

function controlError(errors) {

	$("#local").removeClass("is-invalid");
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
		$("#local").addClass("is-invalid");
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