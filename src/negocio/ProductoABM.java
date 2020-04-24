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

    public void eliminar() {

    }

    public void modificar() {

    }

}