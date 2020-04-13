package datos;

import java.time.LocalDate;

public class Lote {

	private long idLote;
	private Local local;
	private LocalDate fechaIngreso;
	private int cantidadInicial;
	private int CantActual;
	private Producto productos;

	public Lote() {

	}

	public Lote(Local local, LocalDate fechaIngreso, int cantidadInicial, int cantActual, Producto productos) {
		this.local = local;
		this.fechaIngreso = fechaIngreso;
		this.cantidadInicial = cantidadInicial;
		CantActual = cantActual;
		this.productos = productos;
	}

	public long getIdLote() {
		return idLote;
	}

	protected void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public int getCantActual() {
		return CantActual;
	}

	public void setCantActual(int cantActual) {
		CantActual = cantActual;
	}

	public Producto getProductos() {
		return productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

}
