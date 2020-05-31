package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.List;
import java.util.ArrayList;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local")
public class LocalRestController {

    @Autowired
    @Qualifier("localService")
    private ILocalService localService;

    @Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

    @GetMapping("/getLocales")
	public ResponseEntity<List<LocalModel>> getLocales() {
        //return ResponseEntity.ok(localService.getAll());
        List<LocalModel> locales = new ArrayList<LocalModel>();

        for(Local local : localService.getAll()) {
            //why? porque sino no anda el JSON.parse cuando lo manda a la vista
            locales.add(localConverter.entityToModel(local));
        }

        return new ResponseEntity<List<LocalModel>>(locales, HttpStatus.OK);
    }
}