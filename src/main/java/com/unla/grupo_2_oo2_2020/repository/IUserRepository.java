package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;

import com.unla.grupo_2_oo2_2020.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Serializable>{

    User findByUsername(String username);

}