package com.unla.grupo_2_oo2_2020.services;

import com.unla.grupo_2_oo2_2020.entities.User;

public interface IUserService {

    void save(User user);

    User findByUsername(String username);
}