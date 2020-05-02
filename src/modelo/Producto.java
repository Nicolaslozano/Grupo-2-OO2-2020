package modelo;

import java.time.LocalDate;

public class Producto {

	private long idProducto;
	private String nombre;
	private String descripcion;
	private float precio;
	private LocalDate fechaAlta;

	public Producto() {
	}

	public Producto(String nombre, String descripcion, float precio, LocalDate fechaAlta) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaAlta = fechaAlta;
	}

	public long getIdProducto() {
		return idProducto;
	}

	protected void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", fechaAlta=" + fechaAlta + "\n";
	}

}
