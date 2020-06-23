package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Role;
import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.repository.IRoleRepository;
import com.unla.grupo_2_oo2_2020.services.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    @Qualifier("roleRepository")
    private IRoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findByUserType(User user) {

        Role role;

        if (user instanceof Cliente) {
            role = findByName("ROLE_CLIENTE");

        } else if (user instanceof Empleado) {

            Empleado aux = (Empleado) user;

            if (aux.isTipoEmpleado()) {
                role = findByName("ROLE_GERENTE");

            } else {
                role = findByName("ROLE_VENDEDOR");
            }
        } else {
            role = findByName("ROLE_ADMIN");
        }

        return role;
    }

}