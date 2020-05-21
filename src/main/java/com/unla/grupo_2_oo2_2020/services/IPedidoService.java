package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;


public interface IPedidoService {

    public boolean validatePedido(PedidoModel pedidoModel);

    public double getTotal(PedidoModel pedidoModel);

}