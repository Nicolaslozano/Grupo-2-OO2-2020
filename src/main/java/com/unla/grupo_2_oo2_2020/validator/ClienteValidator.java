package com.unla.grupo_2_oo2_2020.validator;

import com.unla.grupo_2_oo2_2020.models.ClienteModel;
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
    }
}