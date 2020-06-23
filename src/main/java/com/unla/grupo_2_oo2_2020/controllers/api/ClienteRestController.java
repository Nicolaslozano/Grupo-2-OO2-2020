package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.HashMap;

import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;
import com.unla.grupo_2_oo2_2020.validator.ClienteValidator;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("securityService")
    private ISecurityService securityService;

    @Autowired
    @Qualifier("clienteValidator")
    private ClienteValidator clienteValidator;

    @PostMapping("/createCliente")
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteModel clienteModel, BindingResult bindingResult) {
        clienteValidator.validate(clienteModel, bindingResult);
        HashMap<String, String> result = new HashMap<String, String>();

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {

                result.put(error.getCode(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        } else {

            clienteService.insertOrUpdate(clienteModel);
            result.put(StaticValuesHelper.SUCCESS_CREATED, "Cliente creado");
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
                .findByEmail(clienteModel.getEmail()).getId() != clienteModel.getId())) {

            result.put(StaticValuesHelper.EMAIL_ALREADY_EXISTS, "Email ya esta en uso");
            return ResponseEntity.badRequest().body(result);
        } else {

            clienteService.insertOrUpdate(clienteModel);
            result.put(StaticValuesHelper.SUCCESS_UPDATED, "Cliente actualizado");
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove/{idPersona}")
    public ResponseEntity<?> removeCliente(@PathVariable("idPersona") long id) {

        HashMap<String, String> result = new HashMap<String, String>();
        clienteService.removeById(id);
        result.put(StaticValuesHelper.SUCCESS_REMOVED, "Cliente eliminado");
        result.put("redirect", ViewRouteHelper.CLIENTE_ROOT);

        return ResponseEntity.ok(result);
    }

}