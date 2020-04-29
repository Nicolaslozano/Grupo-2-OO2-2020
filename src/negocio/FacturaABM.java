package negocio;

import dao.FacturaDao;
import modelo.*;
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
		dao.Eliminar(f);
	}
	
	public void modificar(long idFactura,String factura) {
		Factura f = traerFactura(idFactura);
		f.setFactura(factura);
	}
		
	
	
	
	
	

}//end
