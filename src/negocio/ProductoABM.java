package negocio;

import java.time.LocalDate;

import dao.ProductoDao;
import modelo.Producto;

public class ProductoABM {

    private static ProductoABM instance;
    private ProductoDao dao = ProductoDao.getInstance();

    protected ProductoABM() {}

    public static ProductoABM getInstance() {

        if(instance == null) {

            instance = new ProductoABM();
        }

        return instance;
    }

    public void agregar(String nombre, String descripcion, float precio, LocalDate fechaAlta) {

        Producto p = new Producto(nombre, descripcion, precio, fechaAlta);
        dao.agregar(p);
    }

    public void eliminar(long idProducto) {

    	Producto p=  traerProducto(idProducto);
    	dao.eliminar(p);
    }

    public void modificar(long idProducto,String descripcion,LocalDate fechaAlta,String nombre,float precio) {

    	Producto p= traerProducto(idProducto);
    	p.setDescripcion(descripcion);
    	p.setFechaAlta(fechaAlta);
    	p.setNombre(nombre);
        p.setPrecio(precio);

        dao.actualizar(p);
    }

    public Producto traerProducto(long idProducto) {

        return dao.traerProducto(idProducto);
    }

}