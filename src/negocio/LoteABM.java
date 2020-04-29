package negocio;

import java.time.LocalDate;

import dao.LoteDao;
import modelo.*;

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
	
	public void agregar(int cantidadInicial,int cantidadActual, LocalDate fechaIngreso,Producto producto,boolean estado) {
		
		Lote l= new Lote(cantidadInicial,cantidadActual,fechaIngreso,producto,estado);
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
		
		
	}
	
	
	
}//end
