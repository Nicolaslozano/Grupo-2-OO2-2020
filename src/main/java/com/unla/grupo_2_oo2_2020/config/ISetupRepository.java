package com.unla.grupo_2_oo2_2020.config;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("setupRepository")
public interface ISetupRepository extends JpaRepository<DataAlreadySetupVariable, Serializable>{
    public abstract DataAlreadySetupVariable findById(long id);
}