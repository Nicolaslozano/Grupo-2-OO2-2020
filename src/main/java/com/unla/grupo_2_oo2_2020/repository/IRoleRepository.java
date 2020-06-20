package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;

import com.unla.grupo_2_oo2_2020.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<Role, Serializable> {
    Role findByName(String name);
}