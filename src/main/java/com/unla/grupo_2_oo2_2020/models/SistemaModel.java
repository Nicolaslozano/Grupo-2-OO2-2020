package com.unla.grupo_2_oo2_2020.models;

import java.util.HashSet;
import java.util.Set;

public class SistemaModel {
	private Set<EmpleadoModel> listaEmpleados;
	private Set<ClienteModel> listaClientes;
	private Set<LocalModel> listaLocales;
	private Set<ProductoModel> listaProductos;
	private Set<CarritoModel> listaCarritos;

	public SistemaModel() {
		this.listaEmpleados = new HashSet<EmpleadoModel>();
		this.listaClientes = new HashSet<ClienteModel>();
		this.listaLocales = new HashSet<LocalModel>();
		this.listaProductos = new HashSet<ProductoModel>();
		this.listaCarritos = new HashSet<CarritoModel>();
	}

	public Set<EmpleadoModel> getListaEmpleados() {
		return listaEmpleados;
	}

	public Set<ClienteModel> getListaClientes() {
		return listaClientes;
	}

	public Set<LocalModel> getListaLocales() {
		return listaLocales;
	}

	public Set<ProductoModel> getListaProductos() {
		return listaProductos;
	}

	public Set<CarritoModel> getListaCarritos() {
		return listaCarritos;
	}

	@Override
	public String toString() {
		return "Sistema [listaEmpleados=" + listaEmpleados + ", listaClientes=" + listaClientes + ", listaLocales="
				+ listaLocales + ", listaProductos=" + listaProductos + ", listaCarritos=" + listaCarritos + "]";
	}

}