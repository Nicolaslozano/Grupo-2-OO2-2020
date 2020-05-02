package com.unla.grupo_2_oo2_2020.negocio;

import com.unla.grupo_2_oo2_2020.dao.FacturaDao;
import com.unla.grupo_2_oo2_2020.modelo.Factura;


public class FacturaABM {
	
	private static FacturaABM instance;
	private FacturaDao dao=FacturaDao.getInstance();
	
	protected FacturaABM() {}
	
	public static FacturaABM getInstance() {
		
		
		if(instance==null) {
			
			instance  = new FacturaABM();
		}
		
		return instance;
	}

	public void agregar(String factura) {
		
		Factura f = new Factura(factura);
		dao.agregar(f);
	}
	
	public Factura traerFactura(long idFactura) {
		
		return dao.traer(idFactura);
		}
	public void eliminar(long idFactura) {
		Factura f = traerFactura(idFactura);
		dao.eliminar(f);
	}

	public void modificar(long idFactura,String factura) {
		Factura f = traerFactura(idFactura);
		f.setFactura(factura);

		dao.actualizar(f);
	}

}//end
