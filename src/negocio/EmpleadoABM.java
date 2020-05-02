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

}//end
