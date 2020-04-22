package modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Carrito {

	private int idCarrito;
	private Set<Pedido> listaPedidos;
	private LocalDate fecha;
	private float total;

	public Carrito() {
	}

	public Carrito(int idCarrito, LocalDate fecha) {
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.listaPedidos = new HashSet<Pedido>();
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	protected void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Set<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "\nCarrito [idCarrito=" + idCarrito + ", listaPedidos=" + listaPedidos + ", fecha=" + fecha + ", total="
				+ total + "]";
	}

}