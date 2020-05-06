package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.unla.grupo_2_oo2_2020.entities.Pedido;

public class CarritoModel {

	private long idCarrito;
	private Set<Pedido> listaPedidos;
	private LocalDate fecha;
	private float total; //eliminar, se calcula mediante metodo con los pedidos

	public CarritoModel() {
	}

	public CarritoModel(LocalDate fecha) {

		this.fecha = fecha;
		this.listaPedidos = new HashSet<Pedido>();
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	protected void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Set<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "\nCarrito [idCarrito=" + idCarrito + ", listaPedidos=" + listaPedidos + ", fecha=" + fecha + ", total="
				+ total + "]";
	}

}