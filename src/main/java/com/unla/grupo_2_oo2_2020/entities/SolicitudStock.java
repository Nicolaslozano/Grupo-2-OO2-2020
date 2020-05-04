package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class SolicitudStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSolicitud;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column private LocalDate fecha;
	@OneToOne 
	@JoinColumn(name = "producto") 
	private Producto producto;
	@Column private int cantidad;
	@ManyToOne 
	@JoinColumn(name = "id_vendedor")
	private Empleado vendedor;
	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private Empleado colaborador;
	@Column private boolean aceptado;

	public SolicitudStock() {
	}

	public SolicitudStock(LocalDate fecha, Producto producto, int cantidad, Empleado vendedor, Empleado colaborador) {
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