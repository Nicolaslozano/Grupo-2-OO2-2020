package modelo;

import java.util.HashSet;
import java.util.Set;

public class Stock {

	private Set<Lote> lotes;
	private int cantidad;

	public Stock() {

		this.lotes = new HashSet<Lote>();
		this.cantidad = 0;

	}

	public Set<Lote> getLotes() {
		return lotes;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Stock [lotes=" + lotes + ", cantidad=" + cantidad + "]\n";
	}

}