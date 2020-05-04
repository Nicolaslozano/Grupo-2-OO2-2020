package com.unla.grupo_2_oo2_2020.models;

import java.util.HashSet;
import java.util.Set;

public class LocalModel {

	private long idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private long telefono;
	private StockModel stock;
	private Set<EmpleadoModel> listaEmpleados;
	private Set<FacturaModel> listaFacturas;
	private Set<SolicitudStockModel> listaSolicitudesStock;

	public LocalModel() {
	}

	public LocalModel(String direccion, double latitud, double longitud, long telefono) {

		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.stock = new StockModel();
		this.listaEmpleados = new HashSet<EmpleadoModel>();
		this.listaFacturas = new HashSet<FacturaModel>();
		this.listaSolicitudesStock = new HashSet<SolicitudStockModel>();
	}

	public long getIdLocal() {
		return idLocal;
	}

	protected void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public StockModel getStock() {
		return stock;
	}

	public void setStock(StockModel stock) {
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

	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Set<SolicitudStockModel> getListaSolicitudesStock() {
		return listaSolicitudesStock;
	}

	public void setListaSolicitudesStock(Set<SolicitudStockModel> listaSolicitudesStock) {
		this.listaSolicitudesStock = listaSolicitudesStock;
	}

	public Set<EmpleadoModel> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(Set<EmpleadoModel> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + ", stock=" + stock + ", empleados=" + listaEmpleados + "]\n";
	}
}
