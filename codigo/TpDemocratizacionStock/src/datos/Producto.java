package datos;

import java.time.LocalDate;

public class Producto {
	private long idProducto;
	private float precioUnidad;
	private LocalDate fechaAlta;

	public Producto() {

	}

	public Producto(float precioUnidad, LocalDate fechaAlta) {
		this.precioUnidad = precioUnidad;
		this.fechaAlta = fechaAlta;
	}

	public long getIdProducto() {
		return idProducto;
	}

	protected void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public float getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
