package com.unla.grupo_2_oo2_2020.negocio;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.dao.LoteDao;
import com.unla.grupo_2_oo2_2020.modelo.Lote;
import com.unla.grupo_2_oo2_2020.modelo.Producto;
import com.unla.grupo_2_oo2_2020.modelo.Stock;

public class LoteABM {
	
	private static LoteABM instance;
	private LoteDao dao = LoteDao.getInstance();
	
	protected LoteABM() {}

	public static LoteABM getInstance() {
		
		if(instance == null) {
			
			instance = new LoteABM();
		}
		
		return instance;
	}

	public void agregar(int cantidadInicial,int cantidadActual, LocalDate fechaIngreso,Producto producto,Stock stock) {
		
		Lote l= new Lote(cantidadInicial, cantidadActual, fechaIngreso, producto, stock);
		dao.agregar(l);
	}

	public Lote traerLote(int idLote) {

		return dao.traerLote(idLote);
	}

	public void eliminar(int idLote) {

		Lote l = traerLote(idLote);
		dao.eliminar(l);
	}
	
	public void modificar (int idLote,int cantidadInicial,int cantidadActual, LocalDate fechaIngreso,Producto producto,boolean estado,Stock stock) {

		Lote l = traerLote(idLote);
		l.setCantidadActual(cantidadActual);
		l.setCantidadInicial(cantidadInicial);
		l.setEstado(estado);
		l.setFechaIngreso(fechaIngreso);
		l.setProducto(producto);
		l.setStock(stock);

		dao.actualizar(l);
	}

}//end
