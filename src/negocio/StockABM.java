package negocio;

import dao.StockDao;
import modelo.Local;
import modelo.Stock;

public class StockABM {

	private static StockABM instance;
	private StockDao dao = StockDao.getInstance();

	protected StockABM() {
	}

	public static StockABM getInstance() {

		if (instance == null) {

			instance = new StockABM();
		}

		return instance;
	}

	public void agregar(int cantidad, Local local) {

		Stock s = new Stock(cantidad, local);

		dao.agregar(s);
	}

	public void eliminar(int idStock) {

		Stock s = traerStock(idStock);
		dao.eliminar(s);
	}

	public void modificar(long idStock,int cantidad,Local local) {

	    	Stock s= traerStock(idStock);
	    	s.setCantidad(cantidad);
	    	s.setLocal(local);
	    	dao.actualizar(s);
	    }

	public Stock traerStock(long idStock) {

		return dao.traer(idStock);
	}

}
