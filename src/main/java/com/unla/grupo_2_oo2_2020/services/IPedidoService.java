package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;


public interface IPedidoService {

    public List<Pedido> getAll();

    public List<Pedido> findByCliente(Cliente cliente);

    public List<Pedido> findByLocal(Local local);

    public Pedido findById(long idPedido);

	public PedidoModel insertOrUpdate(PedidoModel pedidoModel);

	public void removeById(long idPedido);

}