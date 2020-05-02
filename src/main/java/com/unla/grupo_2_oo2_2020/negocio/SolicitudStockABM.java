package com.unla.grupo_2_oo2_2020.negocio;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.dao.SolicitudStockDao;
import com.unla.grupo_2_oo2_2020.modelo.Empleado;
import com.unla.grupo_2_oo2_2020.modelo.Local;
import com.unla.grupo_2_oo2_2020.modelo.Producto;
import com.unla.grupo_2_oo2_2020.modelo.SolicitudStock;

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

	public double calcularDistancia(Local local1, Local local2) throws Exception {
		double rad = Math.PI / 180; // Para convertir a Radianes
		double dlat = local1.getLatitud() - local2.getLatitud(); // Diferencia de latitudes
		double dlong = local1.getLongitud() - local2.getLongitud(); // Diferencia de longitudes

		if (local1.equals(local2))
			throw new Exception("La distancia es nula debido a que se  esta comparando el mismo local");
		double R = 6372.795477598;// Radio de la tierra
		double a = Math.pow(Math.sin(rad * dlat / 2), 2) + Math.cos(rad * local1.getLatitud())
				* Math.cos(rad * local2.getLatitud()) * Math.pow(rad * Math.sin(dlong / 2), 2);
		double distancia = 2 * R * Math.asin(Math.sqrt(a));

		return distancia;
	}
	
	
}
