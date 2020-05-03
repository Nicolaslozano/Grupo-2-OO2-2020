package com.unla.grupo_2_oo2_2020.entidades;

import java.time.LocalDate;

public class SolicitudStock {

	private long idSolicitud;
	private LocalDate fecha;
	private Producto producto;
	private int cantidad;
	private Empleado vendedor;
	private Empleado colaborador;
	private boolean aceptado;

	public SolicitudStock() {
	}

	public SolicitudStock(LocalDate fecha, Producto producto, int cantidad, Empleado vendedor, Empleado colaborador) {

		super();
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.vendedor = vendedor;
		this.colaborador = colaborador;

		this.aceptado = false;
	}

	public long getIdSolicitud() {
		return idSolicitud;
	}

	protected void setIdSolicitud(long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Empleado getVendedor() {

		return this.vendedor;
	}

	public void setVendedor(Empleado vendedor) {

		this.vendedor = vendedor;
	}

	public Empleado getColaborador() {

		return this.colaborador;
	}

	public void setColaborador(Empleado colaborador) {

		this.colaborador = colaborador;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	@Override
	public String toString() {
		return "SolicitudStock [fecha=" + fecha + ", producto=" + producto + ", cantidad=" + cantidad +
		 "empleados: "+ vendedor + colaborador + ", aceptado=" + aceptado + "]";
	}
}