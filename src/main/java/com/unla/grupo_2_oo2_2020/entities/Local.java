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
@Table(name = "comercio") // 'local' esta reservado en mysql
public class Local {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLocal;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "latitud")
	private double latitud;

	@Column(name = "longitud")
	private double longitud;

	@Column(name = "telefono")
	private long telefono;

	@OneToOne(mappedBy = "local", cascade = CascadeType.ALL)
	private Stock stock;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<SolicitudStock> solicitudesStock;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<Pedido> pedidos;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<Empleado> empleados;

	
	public Local() {
	}

	public Local(long idLocal, String direccion, double latitud, double longitud, long telefono) {

		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.empleados = new HashSet<Empleado>();
		this.solicitudesStock = new HashSet<SolicitudStock>();
		this.pedidos = new HashSet<Pedido>();
	}

	public Local(String direccion, double latitud, double longitud, long telefono) {

		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.empleados = new HashSet<Empleado>();
		this.solicitudesStock = new HashSet<SolicitudStock>();
		this.pedidos = new HashSet<Pedido>();
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
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
	
	public Set<SolicitudStock> getSolicitudesStock() {
		return solicitudesStock;
	}

	public void setSolicitudesStock(Set<SolicitudStock> solicitudesStock) {
		this.solicitudesStock = solicitudesStock;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + ", stock=" + stock + ", empleados=" + empleados + "solicitudes"
				+ solicitudesStock + "pedidos"+ pedidos + "]\n";
	}

}