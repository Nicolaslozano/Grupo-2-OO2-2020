package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;

import com.unla.grupo_2_oo2_2020.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Serializable> {
}