package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.HashMap;

import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
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
@RequestMapping("/api/cliente")
public class ClienteRestController {

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

    @PostMapping("/createCliente")
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteModel clienteModel, Errors errors) {

        HashMap<String, String> result = new HashMap<String, String>();

        if (errors.hasErrors()) {

            for (ObjectError error : errors.getAllErrors()) {

                result.put(error.getDefaultMessage(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        } else if (clienteService.findByDni(clienteModel.getDni()) != null) {

            result.put(StaticValuesHelper.PERSON_ALREADY_EXISTS, "Persona ya existe");
            return ResponseEntity.badRequest().body(result);
        } else if (clienteService.findByEmail(clienteModel.getEmail()) != null) {

            result.put(StaticValuesHelper.EMAIL_ALREADY_EXISTS, "Email ya esta en uso");
            return ResponseEntity.badRequest().body(result);
        } else {

            clienteService.insertOrUpdate(clienteModel);
            result.put("success", "Cliente creado");
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateCliente")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody ClienteModel clienteModel, Errors errors) {

        HashMap<String, String> result = new HashMap<String, String>();

        if (errors.hasErrors()) {

            for (ObjectError error : errors.getAllErrors()) {

                result.put(error.getDefaultMessage(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        } else if ((clienteService.findByEmail(clienteModel.getEmail()) != null) && (clienteService
                .findByEmail(clienteModel.getEmail()).getIdPersona() != clienteModel.getIdPersona())) {

            result.put(StaticValuesHelper.EMAIL_ALREADY_EXISTS, "Email ya esta en uso");
            return ResponseEntity.badRequest().body(result);
        } else {

            clienteService.insertOrUpdate(clienteModel);
            result.put("success", "Cliente actualizado");
        }

        return ResponseEntity.ok(result);
    }

}