package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CarritoDao;
//import dao.ClienteDao;
import modelo.Carrito;
//import modelo.Cliente;
//import modelo.Producto;

public class CarritoABM {

	private static CarritoABM instance;
	private CarritoDao dao = CarritoDao.getInstance();

	protected CarritoABM() {}

	public static CarritoABM getInstance() {

		if(instance == null) {
			instance = new CarritoABM();
		}

		return instance;

	}

	public void agregar(LocalDate fecha) {

		Carrito c = new Carrito(fecha);
		dao.agregar(c);
	}

	public void modificar(long idCarrito,LocalDate fecha) {

		Carrito p= traerCarrito(idCarrito);
		p.setFecha(fecha);

		dao.actualizar(p);
	}

    public void eliminar(long idCarrito) {

    	Carrito c = traerCarrito(idCarrito);
    	dao.eliminar(c);
    }

    public Carrito traerCarrito(long idCarrito) {

        return dao.traer(idCarrito);
	}
	
	public List<Carrito> traerCarrito() {

		return dao.traer();
	}

}//end
