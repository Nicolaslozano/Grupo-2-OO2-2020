package com.unla.grupo_2_oo2_2020.modelo;

import java.util.HashSet;
import java.util.Set;

public class Sistema {
	private Set<Empleado> listaEmpleados;
	private Set<Cliente> listaClientes;
	private Set<Local> listaLocales;
	private Set<Producto> listaProductos;
	private Set<Carrito> listaCarritos;

	public Sistema() {
		this.listaEmpleados = new HashSet<Empleado>();
		this.listaClientes = new HashSet<Cliente>();
		this.listaLocales = new HashSet<Local>();
		this.listaProductos = new HashSet<Producto>();
		this.listaCarritos = new HashSet<Carrito>();
	}

	public Set<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public Set<Cliente> getListaClientes() {
		return listaClientes;
	}

	public Set<Local> getListaLocales() {
		return listaLocales;
	}

	public Set<Producto> getListaProductos() {
		return listaProductos;
	}

	public Set<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	@Override
	public String toString() {
		return "Sistema [listaEmpleados=" + listaEmpleados + ", listaClientes=" + listaClientes + ", listaLocales="
				+ listaLocales + ", listaProductos=" + listaProductos + ", listaCarritos=" + listaCarritos + "]";
	}

}
