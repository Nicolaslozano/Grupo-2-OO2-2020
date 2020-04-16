package modelo;

import java.time.LocalDate;
import java.util.List;

public class Empleado extends Persona {
	private String franjaHoraria;
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.
	private Local local;

	public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String franjaHoraria,
			boolean tipoEmpleado, Local local) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
		this.local = local;
	}

	public String getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(String franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public boolean isTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	@Override
	public String toString() {
		return "Empleado = [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", dni=" + dni + " [ franjaHoraria=" + franjaHoraria + "]\n";
	}

	public boolean aceptarSolicitudStock() {
		return true;
	}

	public boolean rechazarSolicitudStock() {
		return false;
	}

	public float calcularSueldo(List<Carrito> listaCarrito, Empleado empleado, int mes) {
		float sueldo = 0;
		double porcentajeSueldo = 0;
		for (Carrito carrito : listaCarrito) {

			for (Pedido pedido : carrito.getListaPedidos()) {

				porcentajeSueldo = 0;

				if (carrito.getFecha().getMonthValue() == mes) {

					if (pedido.getVendedorOriginal().getDni() == empleado.getDni()
							&& pedido.getVendedorAuxiliar() == null) {
						porcentajeSueldo = pedido.getTotal() * 0.05;
						sueldo += porcentajeSueldo;
					} else if (pedido.getVendedorAuxiliar() != null) {

						if (pedido.getVendedorOriginal().getDni() == empleado.getDni()) {
							porcentajeSueldo = pedido.getTotal() * 0.03;
							sueldo += porcentajeSueldo;
						}

						if (pedido.getVendedorAuxiliar().getDni() == empleado.getDni()) {
							porcentajeSueldo = pedido.getTotal() * 0.02;
							sueldo += porcentajeSueldo;
						}
					}

				}
			}

		}
		return sueldo;
	}

}
