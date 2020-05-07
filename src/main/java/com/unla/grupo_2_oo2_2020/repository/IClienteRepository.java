package com.unla.grupo_2_oo2_2020.repository;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.Cliente;

import org.springframework.stereotype.Repository;
@Transactional
@Repository("clienteRepository")
public interface IClienteRepository extends IPersonaBaseRepository<Cliente>{

    public abstract Cliente findByEmail(String email);

}