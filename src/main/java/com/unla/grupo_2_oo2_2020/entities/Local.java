package com.unla.grupo_2_oo2_2020.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "comercio")
public class Local {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idLocal;

	@Column (name = "direccion")
	private String direccion;

	@Column	(name = "latitud")
	private double latitud;

	@Column	(name = "longitud")
	private double longitud;

	@Column	(name = "telefono")
	private long telefono;

	@OneToOne(mappedBy = "local", cascade = CascadeType.ALL)
	private Stock stock;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idLocal")
	private Set<SolicitudStock> listaSolicitudesStock;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<Empleado> listaEmpleados;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idLocal")
	private Set<Factura> listaFacturas;


	public Local() {
	}

	public Local(long idLocal, String direccion, double latitud, double longitud, long telefono) {

		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.listaEmpleados = new HashSet<Empleado>();
		this.listaFacturas = new HashSet<Factura>();
		this.listaSolicitudesStock = new HashSet<SolicitudStock>();
	}
	
	public Local(String direccion, double latitud, double longitud, long telefono) {

		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.listaEmpleados = new HashSet<Empleado>();
		this.listaFacturas = new HashSet<Factura>();
		this.listaSolicitudesStock = new HashSet<SolicitudStock>();
	}

	public long getIdLocal() {
		return idLocal;
	}

	protected void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public Set<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Set<SolicitudStock> getListaSolicitudesStock() {
		return listaSolicitudesStock;
	}

	public void setListaSolicitudesStock(Set<SolicitudStock> listaSolicitudesStock) {
		this.listaSolicitudesStock = listaSolicitudesStock;
	}

	public Set<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(Set<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + ", stock=" + stock + ", empleados=" + listaEmpleados + "]\n";
	}

}