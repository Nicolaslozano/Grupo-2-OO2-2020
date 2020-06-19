package com.unla.grupo_2_oo2_2020.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;

public interface IPedidoService {

	public List<Pedido> getAll();

	public List<Pedido> getAccepted();

	public void insertOrUpdate(PedidoModel pedidoModel);

	public Pedido findById(long idPedido);

	public double getTotal(Pedido pedido);

	public double getTotal(PedidoModel pedidoModel);

	public void removeById(long idPedido);

	public List<Pedido> findByLocal(Local local);

	public Map<ProductoModel, Integer> rankingProductos();

	Map<ProductoModel, Integer> productosEntreFechas(LocalDate fecha1, LocalDate fecha2, Local local);

}