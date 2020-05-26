package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.HashMap;

import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRestController {

	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@PostMapping("/createEmpleado")
	public ResponseEntity<?> createEmpleado(@Valid @RequestBody EmpleadoModel empleadoModel, Errors errors) {

		HashMap<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);
		} else if (empleadoService.findByDni(empleadoModel.getDni()) != null) {

			result.put(StaticValuesHelper.PERSON_ALREADY_EXISTS, "Persona ya existe");
			return ResponseEntity.badRequest().body(result);

		} else {

			empleadoService.insertOrUpdate(empleadoModel);
			result.put("success", "Empleado creado");
		}

		return ResponseEntity.ok(result);
	}

	@PostMapping("/updateEmpleado")
	public ResponseEntity<?> updateEmpleado(@Valid @RequestBody EmpleadoModel empleadoModel, Errors errors) {

		HashMap<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);

		} else {

			empleadoService.insertOrUpdate(empleadoModel);
			result.put("success", "Empleado actualizado");
		}

		return ResponseEntity.ok(result);
	}

}
