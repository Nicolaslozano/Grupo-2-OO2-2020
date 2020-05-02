package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CarritoDao;
import modelo.Empleado;
import modelo.Carrito;
import modelo.Pedido;

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

	public double calcularSueldo(Empleado empleado, int mes) {

		double sueldo = 0;
		double porcentajeSueldo = 0;

		for (Carrito carrito : traerCarrito()) {

			for (Pedido pedido : carrito.getListaPedidos()) {

				porcentajeSueldo = 0;

				if (carrito.getFecha().getMonthValue() == mes) {

					if (pedido.getVendedorOriginal().getDni() == empleado.getDni()
							&& pedido.getVendedorAuxiliar() == null) {
						porcentajeSueldo = pedido.getSubtotal() * 0.05;
						sueldo += porcentajeSueldo;
					} else if (pedido.getVendedorAuxiliar() != null) {

						if (pedido.getVendedorOriginal().getDni() == empleado.getDni()) {
							porcentajeSueldo = pedido.getSubtotal() * 0.03;
							sueldo += porcentajeSueldo;
						}

						if (pedido.getVendedorAuxiliar().getDni() == empleado.getDni()) {
							porcentajeSueldo = pedido.getSubtotal() * 0.02;
							sueldo += porcentajeSueldo;
						}
					}

				}
			}

		}
		return sueldo;
	}

}//end
