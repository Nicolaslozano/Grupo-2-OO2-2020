package negocio;

import java.time.LocalDate;

import dao.EmpleadoDao;
import modelo.*;
import negocio.PedidoABM;

public class EmpleadoABM {

	private static EmpleadoABM instance;
	private EmpleadoDao dao= EmpleadoDao.getInstance();
	private PedidoABM pedidoABM = PedidoABM.getInstance();
	
	protected EmpleadoABM() {}

	public static EmpleadoABM getInstance() {
		
		if(instance == null) {
			
			instance = new EmpleadoABM();
		}
		
		return instance;
	}
	
	public void agregar(String nombre,String apellido, LocalDate fechaNacimiento,int dni,
			String franjaHoraria,boolean tipoEmpleado,Local local) {
		
		Empleado e = new Empleado(nombre,apellido,fechaNacimiento,dni,franjaHoraria,tipoEmpleado,local);
		dao.agregar(e);
	}
	
	
	public Empleado traerEmpleado(int dni) {
		return dao.traer(dni);
	}
	
	public void eliminar(int dni) {
		Empleado e=traerEmpleado(dni);
		dao.eliminar(e);
	}
	
	public void modificar(String nombre,String apellido, LocalDate fechaNacimiento,int dni,
			String franjaHoraria,boolean tipoEmpleado,Local local) {
		
		Empleado e = traerEmpleado(dni);
		e.setNombre(nombre);
		e.setApellido(apellido);
		e.setFechaNacimiento(fechaNacimiento);
		e.setFranjaHoraria(franjaHoraria);
		e.setDni(dni);
		e.setTipoEmpleado(tipoEmpleado);
		e.setLocal(local);

		dao.actualizar(e);
	}

	public double calcularSueldo(Empleado empleado, int mes) {

		double sueldo = 0;
		double porcentajeSueldo = 0;

		for (Carrito carrito :) {

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
}//end
