package modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SolicitudStock {

	private long idSolicitud;
	private LocalDate fecha;
	private Producto producto;
	private int cantidad;
	private Set<Empleado> empleados = new HashSet<>();
	//colaborador tmb
	private boolean aceptado;

	public SolicitudStock() {
	}

	public SolicitudStock(LocalDate fecha, Producto producto, int cantidad, Empleado vendedor) {

		super();
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.empleados.add(vendedor);

		this.aceptado = false;
	}

	public long getIdSolicitud() {
		return idSolicitud;
	}

	protected void setIdSolicitud(long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Set<Empleado> getEmpleados() {

		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {

		this.empleados = empleados;
	}

	//ADD EMPLEADO EN ABM

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	@Override
	public String toString() {
		return "SolicitudStock [fecha=" + fecha + ", producto=" + producto + ", cantidad=" + cantidad +
		 "empleados: "+ empleados + ", aceptado=" + aceptado + "]";
	}
}