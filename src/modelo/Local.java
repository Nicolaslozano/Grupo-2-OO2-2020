package modelo;

import java.util.HashSet;
import java.util.Set;

public class Local {

	private long idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private long telefono;
	private Stock stock;
	private Set<Empleado> listaEmpleados;
	private Set<Factura> listaFacturas;
	private Set<SolicitudStock> listaSolicitudesStock;

	public Local() {
	}

	public Local(String direccion, double latitud, double longitud, long telefono, Stock stock) {

		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.stock = stock;
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