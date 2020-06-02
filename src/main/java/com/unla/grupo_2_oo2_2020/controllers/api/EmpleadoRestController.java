package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import java.util.HashMap;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unla.grupo_2_oo2_2020.converters.EmpleadoConverter;
import com.unla.grupo_2_oo2_2020.entities.Empleado;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRestController {

	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	@GetMapping("/getEmpleados")
	public ResponseEntity<List<EmpleadoModel>> getEmpleados() {

		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();

        for(Empleado empleado : empleadoService.getAll()) {
            //why? porque sino no anda el JSON.parse cuando lo manda a la vista
            empleados.add(empleadoConverter.entityToModel(empleado));
        }

        return new ResponseEntity<List<EmpleadoModel>>(empleados, HttpStatus.OK);
	}

	@GetMapping("getEmpleados/{idLocal}")
	public ResponseEntity<List<EmpleadoModel>> getEmpleadosByLocal(@PathVariable("idLocal") long id) {

		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();

        for(Empleado empleado : empleadoService.findByLocal(localService.findById(id))) {
            //why? porque sino no anda el JSON.parse cuando lo manda a la vista
            empleados.add(empleadoConverter.entityToModel(empleado));
        }

        return new ResponseEntity<List<EmpleadoModel>>(empleados, HttpStatus.OK);
	}

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
			result.put(StaticValuesHelper.SUCCESS_CREATED, "Empleado creado");
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
			result.put(StaticValuesHelper.SUCCESS_UPDATED, "Empleado actualizado");
		}

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/remove/{idPersona}")
	public ResponseEntity<?> removeEmpleado(@PathVariable("idPersona") long id) {

		HashMap<String, String> result = new HashMap<String, String>();
		empleadoService.removeById(id);
		result.put(StaticValuesHelper.SUCCESS_REMOVED, "Empleado eliminado");
		result.put("redirect", ViewRouteHelper.EMPLEADO_ROOT);

		return ResponseEntity.ok(result);
	}

}
