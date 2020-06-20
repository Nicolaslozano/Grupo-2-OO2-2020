package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.entities.Role;
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

}