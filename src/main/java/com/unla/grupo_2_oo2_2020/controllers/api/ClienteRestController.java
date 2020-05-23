package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

        String result = "Cliente Creado";

        if (errors.hasErrors()) {

            result = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(result);
        }

        if (clienteService.findByDni(clienteModel.getDni()) != null)
            result = "Ya existe un cliente con ese DNI";

        else if (clienteService.findByEmail(clienteModel.getEmail()) != null)
            result = "Ya existe un cliente con ese Email";

        else clienteService.insertOrUpdate(clienteModel);

        return ResponseEntity.ok(result);
    }
}