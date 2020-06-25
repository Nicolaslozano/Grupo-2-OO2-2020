package com.unla.grupo_2_oo2_2020.services;

import com.unla.grupo_2_oo2_2020.entities.Privilege;

public interface IPrivilegeService {

    public Privilege findByName(String name);

}