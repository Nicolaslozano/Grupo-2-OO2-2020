package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.models.structlike.EmpleadoSalarioModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.validator.EmpleadoValidator;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	@Qualifier("empleadoValidator")
	private EmpleadoValidator empleadoValidator;

	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	@GetMapping("/getEmpleados")
	public ResponseEntity<List<EmpleadoModel>> getEmpleados() {

		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();

		for (Empleado empleado : empleadoService.getAll()) {
			// why? porque sino no anda el JSON.parse cuando lo manda a la vista
			empleados.add(empleadoConverter.entityToModel(empleado));
		}

		return new ResponseEntity<List<EmpleadoModel>>(empleados, HttpStatus.OK);
	}

	@GetMapping("getEmpleados/{idLocal}")
	public ResponseEntity<List<EmpleadoModel>> getEmpleadosByLocal(@PathVariable("idLocal") long id) {

		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();

		for (Empleado empleado : empleadoService.findByLocal(localService.findById(id))) {
			// why? porque sino no anda el JSON.parse cuando lo manda a la vista
			empleados.add(empleadoConverter.entityToModel(empleado));
		}

		return new ResponseEntity<List<EmpleadoModel>>(empleados, HttpStatus.OK);
	}

	@GetMapping("getEmpleados/{idLocal}/except/{id}")
	public ResponseEntity<List<EmpleadoModel>> getEmpleadosByLocalExcept(@PathVariable("idLocal") long idLocal,
			@PathVariable("id") long id) {

		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();

		for (Empleado empleado : empleadoService.findByLocal(localService.findById(idLocal))) {

			if (empleado.getId() == id)
				continue;
			empleados.add(empleadoConverter.entityToModel(empleado));
		}

		return new ResponseEntity<List<EmpleadoModel>>(empleados, HttpStatus.OK);
	}

	@GetMapping("getEmpleados/sueldo/{month}/{idLocal}")
	public ResponseEntity<?> getSueldos(@PathVariable("month") int month, @PathVariable("idLocal") long idLocal) {

		List<EmpleadoSalarioModel> empleados = new ArrayList<EmpleadoSalarioModel>();
		EmpleadoModel empleadoAux;

		if (idLocal > 0) {

			for (Empleado empleado : empleadoService.findByLocal(localService.findById(idLocal))) {

				empleadoAux = empleadoConverter.entityToModel(empleado);
				empleados
						.add(new EmpleadoSalarioModel(empleadoAux, empleadoService.calcularSueldo(month, empleadoAux)));
			}
		} else {

			for (Empleado empleado : empleadoService.getAll()) {

				empleadoAux = empleadoConverter.entityToModel(empleado);
				empleados
						.add(new EmpleadoSalarioModel(empleadoAux, empleadoService.calcularSueldo(month, empleadoAux)));
			}
		}

		return new ResponseEntity<List<EmpleadoSalarioModel>>(empleados, HttpStatus.OK);
	}

	@PostMapping("/createEmpleado")
	public ResponseEntity<?> createEmpleado(@Valid @RequestBody EmpleadoModel empleadoModel, BindingResult bindingResult) {
        empleadoValidator.validate(empleadoModel, bindingResult);
        HashMap<String, String> result = new HashMap<String, String>();

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {

                result.put(((FieldError) error).getField(), error.getCode());
            }

            return ResponseEntity.badRequest().body(result);
        } else {

            empleadoService.insertOrUpdate(empleadoModel);
            result.put(StaticValuesHelper.SUCCESS_CREATED, "Empleado creado");
        }

        return ResponseEntity.ok(result);
    }

	@PostMapping("/updateEmpleado")
	public ResponseEntity<?> updateCliente(@Valid @RequestBody EmpleadoModel empleadoModel, BindingResult bindingResult) {

        empleadoModel.setPassword(empleadoService.findByDni(empleadoModel.getDni()).getPassword());

        empleadoValidator.validateUpdate(empleadoModel, bindingResult);
        HashMap<String, String> result = new HashMap<String, String>();

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {

                result.put(((FieldError) error).getField(), error.getCode());
            }

            return ResponseEntity.badRequest().body(result);
        } else {

            empleadoService.insertOrUpdate(empleadoModel);
            result.put(StaticValuesHelper.SUCCESS_UPDATED, "Empleado actualizado");
        }

        return ResponseEntity.ok(result);
    }

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeEmpleado(@PathVariable("id") long id) {

		HashMap<String, String> result = new HashMap<String, String>();
		empleadoService.removeById(id);
		result.put(StaticValuesHelper.SUCCESS_REMOVED, "Empleado eliminado");
		result.put("redirect", ViewRouteHelper.EMPLEADO_ROOT);

		return ResponseEntity.ok(result);
	}

}
