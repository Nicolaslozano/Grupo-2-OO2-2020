package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CarritoDao;
import modelo.Carrito;
import modelo.Pedido;

public class CarritoABM {

	private static CarritoABM instance;
	private CarritoDao dao = CarritoDao.getInstance();
	private PedidoABM pedidoABM = PedidoABM.getInstance();

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

	public double calcularTotal(long idCarrito) {

		Carrito c = traerCarrito(idCarrito);
		double total = 0;

		for(Pedido p : pedidoABM.traerPedido(c)) {

			total = total + p.getSubtotal();
		}

		return total;
	}

}//end
