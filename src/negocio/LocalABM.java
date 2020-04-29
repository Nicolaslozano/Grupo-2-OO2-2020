package negocio;
import modelo.Local;
import dao.LocalDao;
public class LocalABM {

	private static LocalABM instance;
	private LocalDao dao=LocalDao.getInstance();

	protected LocalABM() {}
	
	public static LocalABM getInstance() {
		
		if(instance == null) {
			
			instance = new LocalABM();
			
		}
		
		return instance;
	}
	
	public void agregar(String direccion,double latitud,double longitud,long telefono) {
		
		Local l = new Local(direccion,latitud,longitud,telefono);
		dao.agregar(l);
	}

	
	public Local traerLocal(long idLocal) {
		
		return dao.traer(idLocal);
	}
	
	public void eliminar(long idLocal) {

		Local l = traerLocal(idLocal);
		dao.eliminar(l);
	}

	public void modificar(long idLocal,String direccion,double latitud,double longitud,long telefono) {

		Local l = traerLocal(idLocal);
		l.setDireccion(direccion);
		l.setLatitud(latitud);
		l.setLongitud(longitud);
		l.setTelefono(telefono);

		dao.actualizar(l);
	}

}//end
