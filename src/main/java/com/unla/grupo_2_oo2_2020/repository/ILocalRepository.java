package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Local;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Local, Serializable> {

    public abstract Local findByIdLocal(long idLocal);

    public abstract List<Local> findAll();
}