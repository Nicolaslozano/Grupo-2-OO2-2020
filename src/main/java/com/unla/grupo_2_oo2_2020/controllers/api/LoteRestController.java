package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;

import com.unla.grupo_2_oo2_2020.converters.LoteConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.models.LoteModel;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lote")
public class LoteRestController {

    @Autowired
	@Qualifier("loteService")
    private ILoteService loteService;

    @Autowired
	@Qualifier("stockService")
    private IStockService stockService;

    @Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;

    @GetMapping("/getLotes")
	public ResponseEntity<List<LoteModel>> getLotes() {

		List<LoteModel> lotes = new ArrayList<LoteModel>();

		for (Lote lote : loteService.getAll()) {
			// why? porque sino no anda el JSON.parse cuando lo manda a la vista
			lotes.add(loteConverter.entityToModel(lote));
		}

		return new ResponseEntity<List<LoteModel>>(lotes, HttpStatus.OK);
    }

    @GetMapping("/getLotes/{idLocal}")
	public ResponseEntity<List<LoteModel>> get(@PathVariable("idLocal") long id) {

		List<LoteModel> lotes = new ArrayList<LoteModel>();

		for (Lote lote : loteService.findByStock(stockService.findById(id))){
			// why? porque sino no anda el JSON.parse cuando lo manda a la vista
			lotes.add(loteConverter.entityToModel(lote));
		}

		return new ResponseEntity<List<LoteModel>>(lotes, HttpStatus.OK);
	}

	@PostMapping("/createLote")
	public ResponseEntity<?> createLote(@Valid @RequestBody LoteModel loteModel, Errors errors) {

		Map<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);
		} else {

			loteService.insertOrUpdate(loteModel);
			result.put(StaticValuesHelper.SUCCESS_CREATED, "Lote creado");
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/updateLote")
	public ResponseEntity<?> updateLote(@Valid @RequestBody LoteModel loteModel, Errors errors) {

		Map<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);

		} else {

			loteService.insertOrUpdate(loteModel);
			result.put(StaticValuesHelper.SUCCESS_UPDATED, "Lote actualizado");
		}

		return ResponseEntity.ok(result);
	}

}