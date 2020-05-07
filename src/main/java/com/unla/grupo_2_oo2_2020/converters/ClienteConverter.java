package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;

@Component("clienteConverter")
public class ClienteConverter {

    public ClienteModel entityToModel(Cliente cliente) {
        return new ClienteModel(cliente.getIdPersona(), cliente.getEmail(), cliente.getNombre(), cliente.getApellido(),
                cliente.getFechaNacimiento(), cliente.getDni());
    }

    public Cliente modelToEntity(ClienteModel clienteModel) {
        return new Cliente(clienteModel.getIdPersona(), clienteModel.getEmail(), clienteModel.getNombre(), clienteModel.getApellido(),
        clienteModel.getFechaNacimiento(), clienteModel.getDni());
    }
}