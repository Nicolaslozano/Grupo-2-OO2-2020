package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.structlike.LocalAndDistanceModel;
import com.unla.grupo_2_oo2_2020.models.structlike.LocalFormModel;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
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

		for (Local local : localService.getAll()) {
			// why? porque sino no anda el JSON.parse cuando lo manda a la vista
			locales.add(localConverter.entityToModel(local));
		}

		return new ResponseEntity<List<LocalModel>>(locales, HttpStatus.OK);
	}

	@GetMapping("getLocales/except/{idLocal}")
	public ResponseEntity<List<LocalModel>> getLocalesExcept(@PathVariable("idLocal") long idLocal) {

		List<LocalModel> locales = new ArrayList<LocalModel>();

		for(Local local : localService.getAll()) {

			if(local.getIdLocal() == idLocal) continue;

			locales.add(localConverter.entityToModel(local));
		}

		return new ResponseEntity<List<LocalModel>>(locales, HttpStatus.OK);
	}

	@PostMapping("/getValidLocals")
	public ResponseEntity<?> getValidLocals(@RequestBody PedidoModel pedidoModel) {

		List<LocalAndDistanceModel> result = new ArrayList<LocalAndDistanceModel>();

		localService.getValidLocals(pedidoModel).entrySet().stream().forEach(e -> result
				.add(new LocalAndDistanceModel(e.getKey().getIdLocal(), e.getKey().getDireccion(), e.getValue())));

		return ResponseEntity.ok(result);
	}

	@PostMapping("/createLocal")
	public ResponseEntity<?> createLocal(@Valid @RequestBody LocalModel localModel, Errors errors) {

		Map<String, String> result = new HashMap<String, String>();

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

		Map<String, String> result = new HashMap<String, String>();

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

		Map<String, String> result = new HashMap<String, String>();

		localService.removeById(id);

		result.put(StaticValuesHelper.SUCCESS_REMOVED, "Local eliminado");
		result.put("redirect", ViewRouteHelper.LOCAL_ROOT);

		return ResponseEntity.ok(result);
	}

	@PostMapping("/distance")
	public ResponseEntity<?> calculateDistance(@RequestBody LocalFormModel localModels) {

		Map<String, String> result = new HashMap<String, String>();
		Local localSelected1 = localService.findById(localModels.getIdLocal_1());
		Local localSelected2 = localService.findById(localModels.getIdLocal_2());

		result.put("distance", String.valueOf(localSelected1.calculateDistance(localSelected2)));
		result.put("local1", localSelected1.getDireccion());
		result.put("local2", localSelected2.getDireccion());

		return ResponseEntity.ok(result);
	}

}