package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "solicitudStock")
public class SolicitudStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSolicitud;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;
	@Column
	private int cantidad;
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Empleado vendedor;
	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private Empleado colaborador;
	@Column(name = "aceptado")
	private boolean aceptado;

	public SolicitudStock() {
	}

	public SolicitudStock(Local local,LocalDate fecha, Producto producto, int cantidad, Empleado vendedor, Empleado colaborador) {

		this.local = local;
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

	public Local getLocal() {

		return this.local;
	}

	public void setLocal(Local local) {

		this.local = local;
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
		 "empleados: "+ vendedor + colaborador + ", aceptado=" + aceptado + local + "]";
	}
}