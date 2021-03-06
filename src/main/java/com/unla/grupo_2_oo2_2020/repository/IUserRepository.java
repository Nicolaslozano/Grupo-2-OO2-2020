package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IUserRepository<T>extends JpaRepository<T, Serializable>{

    public abstract T findById(long id);

    public abstract List<T> findAll();

    public abstract T findByDni(int dni);

    public abstract T findByUsername(String username);
}