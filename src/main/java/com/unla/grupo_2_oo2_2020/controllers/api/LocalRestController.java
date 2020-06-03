package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.List;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local")
public class LocalRestController {

    @Autowired
    @Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
    @Qualifier("stockService")
	private IStockService stockService;
	
	@Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;

    @Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

    @GetMapping("/getLocales")
	public ResponseEntity<List<LocalModel>> getLocales() {

        List<LocalModel> locales = new ArrayList<LocalModel>();

        for(Local local : localService.getAll()) {
            //why? porque sino no anda el JSON.parse cuando lo manda a la vista
            locales.add(localConverter.entityToModel(local));
        }

        return new ResponseEntity<List<LocalModel>>(locales, HttpStatus.OK);
    }

    @PostMapping("/createLocal")
	public ResponseEntity<?> createLocal(@Valid @RequestBody LocalModel localModel, Errors errors) {

		HashMap<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);
		} else {

            localService.insertOrUpdate(localModel);
            result.put(StaticValuesHelper.SUCCESS_CREATED, "Local creado");
        }
		return ResponseEntity.ok(result);
	}

	@PostMapping("/updateLocal")
	public ResponseEntity<?> updateLocal(@Valid @RequestBody LocalModel localModel, Errors errors) {

		HashMap<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);

		} else {

            localService.insertOrUpdate(localModel);
            result.put(StaticValuesHelper.SUCCESS_UPDATED, "Local actualizado");
        }

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/remove/{idLocal}")
    public ResponseEntity<?> removeLocal(@PathVariable("idLocal") long id) {

		HashMap<String, String> result = new HashMap<String, String>();

		localService.removeById(id);

        result.put(StaticValuesHelper.SUCCESS_REMOVED, "Local eliminado");
        result.put("redirect", ViewRouteHelper.LOCAL_ROOT);

        return ResponseEntity.ok(result);
    }

}