package com.unla.grupo_2_oo2_2020.services;

import com.unla.grupo_2_oo2_2020.entities.Role;
import com.unla.grupo_2_oo2_2020.entities.User;

public interface IRoleService {

    public Role findByName(String name);

    public Role findByUserType(User user);

}