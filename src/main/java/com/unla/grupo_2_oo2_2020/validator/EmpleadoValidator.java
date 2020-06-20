package com.unla.grupo_2_oo2_2020.validator;

import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("empleadoValidator")
public class EmpleadoValidator implements Validator{

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmpleadoModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmpleadoModel empleado = (EmpleadoModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (empleado.getUsername().length() < 6 || empleado.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(empleado.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (empleado.getPassword().length() < 8 || empleado.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!empleado.getPasswordConfirm().equals(empleado.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "NotEmpty");
        if(userService.findByDni(empleado.getDni()) != null) {
            errors.rejectValue("dni", "Duplicate.userForm.dni");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idLocal", "NotEmpty");
        if (empleado.getIdLocal() < 1) {
            errors.rejectValue("idLocal", "Size.userForm.local");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "franjaHoraria", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty");
    }
}