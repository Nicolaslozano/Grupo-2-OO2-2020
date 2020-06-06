package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;


public interface IPedidoService {

   public List<Pedido> getAll();
	
	public void insertOrUpdate(PedidoModel pedidoModel);

    public double getTotal(PedidoModel pedidoModel);

    public void removeById(long idPedido);

	public List<Pedido> findByLocal(Local local);

}