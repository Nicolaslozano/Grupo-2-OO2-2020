package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.unla.grupo_2_oo2_2020.entities.Pedido;

public class CarritoModel {

	private long idCarrito;
	private LocalDate fecha;
	private float total; //eliminar, se calcula mediante metodo con los pedidos

	public CarritoModel() {
	}

	public CarritoModel(long idCarrito, LocalDate fecha) {
		
		this.idCarrito = idCarrito;
		this.fecha = fecha;
	}

	public long getIdCarrito() {
		return idCarrito;
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
		return "\nCarrito [idCarrito=" + idCarrito + ", fecha=" + fecha + ", total="
				+ total + "]";
	}

}