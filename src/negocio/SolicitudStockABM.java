package negocio;

import java.time.LocalDate;

import dao.SolicitudStockDao;
import modelo.Empleado;
import modelo.Local;
import modelo.Producto;
import modelo.SolicitudStock;

public class SolicitudStockABM {

	private static SolicitudStockABM instance;
	private SolicitudStockDao dao = SolicitudStockDao.getInstance();


	public static SolicitudStockABM getInstance() {

		if (instance == null) {

			instance = new SolicitudStockABM();
		}

		return instance;
	}

	public void agregar(LocalDate fecha, Producto producto, int cantidad, Empleado vendedor, Empleado colaborador) {

		SolicitudStock so = new SolicitudStock(fecha,producto,cantidad,vendedor,colaborador);
		dao.agregar(so);
	}

	public void eliminar(long idStock) {

		SolicitudStock so=traerSolicitud(idStock);
		dao.eliminar(so);
	}

	public void modificar(long idStock, int cantidad, Local local) {

		SolicitudStock so = traerSolicitud(idStock);
		
		dao.actualizar(so);
	}

	public SolicitudStock traerSolicitud(long idSolicitud) {

		return dao.traer(idSolicitud);
	}

}
