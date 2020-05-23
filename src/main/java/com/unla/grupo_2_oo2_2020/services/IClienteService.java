package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;


public interface IClienteService {

    public List<Cliente> getAll();

    public ClienteModel insertOrUpdate(ClienteModel ClienteModel);

    public void removeById(long idPersona);

    public Cliente findById(long idPersona);
    
    public Cliente findByDni(int dni);

    public Cliente findByEmail(String email);

}