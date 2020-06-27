package com.unla.grupo_2_oo2_2020.validator;

import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("clienteValidator")
public class ClienteValidator implements Validator{

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ClienteModel cliente = (ClienteModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (cliente.getUsername().length() < 6 || cliente.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(cliente.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (cliente.getPassword().length() < 8 || cliente.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!cliente.getPasswordConfirm().equals(cliente.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if(clienteService.findByEmail(cliente.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "NotEmpty");
        if(userService.findByDni(cliente.getDni()) != null) {
            errors.rejectValue("dni", "Duplicate.userForm.dni");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty");
    }

    public void validateUpdate(Object o, Errors errors) {
        ClienteModel cliente = (ClienteModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (cliente.getUsername().length() < 6 || cliente.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if ((userService.findByUsername(cliente.getUsername()) != null) && (userService.findByUsername(cliente.getUsername()).getId() != cliente.getId())) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if((clienteService.findByEmail(cliente.getEmail()) != null) && (clienteService.findByEmail(cliente.getEmail()).getId() != cliente.getId())) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty");
    }
}