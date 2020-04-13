package datos;

import java.time.LocalDate;

public class Comprobante {
	private long idComprobante;
	private LocalDate fecha;
	private int cantProducto;
	private Producto producto;
	private Empleado vendedor;

	public Comprobante() {
	}

	public Comprobante(LocalDate fecha, int cantProducto, Producto producto, Empleado vendedor) {

		this.fecha = fecha;
		this.cantProducto = cantProducto;
		this.producto = producto;
		this.vendedor = vendedor;
	}

	public long getIdComprobante() {
		return idComprobante;
	}

	protected void setIdComprobante(long idComprobante) {
		this.idComprobante = idComprobante;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCantProducto() {
		return cantProducto;
	}

	public void setCantProducto(int cantProducto) {
		this.cantProducto = cantProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Empleado getVendedor() {
		return vendedor;
	}

	public void setVendedor(Empleado vendedor) {
		this.vendedor = vendedor;
	}
	
}


