package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;

import com.unla.grupo_2_oo2_2020.entities.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("privilegeRepository")
public interface IPrivilegeRepository extends JpaRepository<Privilege, Serializable> {
    Privilege findByName(String name);
}