package modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Carrito {

	private long idCarrito;
	private Set<Pedido> listaPedidos;
	private LocalDate fecha;

	public Carrito() {
	}

	public Carrito(LocalDate fecha) {

		this.fecha = fecha;
		this.listaPedidos = new HashSet<Pedido>();
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	protected void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Set<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(Set<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "\nCarrito [idCarrito=" + idCarrito + ", listaPedidos=" + listaPedidos + ", fecha=" + fecha + ", total="
				 + "]";
	}

}