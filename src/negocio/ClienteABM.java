package negocio;

import java.time.LocalDate;

import dao.ClienteDao;
import dao.ProductoDao;
import modelo.Cliente;
import modelo.Producto;

public class ClienteABM {
	
	    
		private static ClienteABM instance;
	    private ClienteDao dao = ClienteDao.getInstance();

	    protected ClienteABM() {}

	    public static ClienteABM getInstance() {

	        if(instance == null) {

	            instance = new ClienteABM();
	        }

	        return instance;
	    }

	    public void agregar(int dni,String nombre,String apellido,LocalDate fechaNacimiento,String email) {

	        Cliente c = new Cliente(email, nombre, apellido, fechaNacimiento, dni);

	        dao.agregar(c);
	    }

	    public void eliminar(int idPersona) {
	    	Cliente c=traerCliente(idPersona);
	    	dao.Eliminar(c);
	    }

	    public void modificar(int idPersona,String nombre,String apellido,String email,int dni,LocalDate fechaNacimiento) {
	    	Cliente c= traerCliente(idPersona);
	    	c.setNombre(nombre);
	    	c.setApellido(apellido);
	    	c.setEmail(email);
	    	c.setDni(dni);
	    	c.setFechaNacimiento(fechaNacimiento);
	    	
	    }
	    
	    public Cliente traerCliente(int idPersona) {
	    return dao.traer(idPersona);
	    }

}
