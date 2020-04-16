package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stock {

	private List<Lote> lotes;
	private int cantidad;

	public Stock() {
		super();
		this.lotes = new ArrayList<Lote> ();
		this.cantidad = 0;

	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Stock [lotes=" + lotes + ", cantidad=" + cantidad + "]\n";
	}

	public List<Lote> lotesDelProducto(Producto producto){
		List<Lote> lotesActivos = new ArrayList<Lote>();
		for(Lote l : lotes) {
			if(l.getProducto().equals(producto) && l.isEstado()) {
				lotesActivos.add(l);
				
			}
		}
		return lotesActivos;
	}
	
	public int calcularStock(Producto producto) {
		int total = 0;
		for(Lote l : lotesDelProducto(producto)) {
			total += l.getCantidadActual();
		}
		return total;
	}
	

	public boolean stockValido(Producto producto, int cantidad) {
		return (calcularStock(producto)>=cantidad)? true:false; //SI EL STOCK DISPONIBLE ES MAYOR O IGUAL A LA CANTIDAD
	}															//SOLICITADA RETORNA TRUE
	
	public Lote traerLote(int idLote) {
        int i = 0;
        Lote loteEncontrado = null;

        while(i<lotes.size() && loteEncontrado == null) {
            Lote l = lotes.get(i);
            if(l.getIdLote()==idLote) {
                loteEncontrado = l;
            }
            i++;
        }

        return loteEncontrado;
    }

    /**/

    public void altaStock(int idLote) throws Exception {

        if(traerLote(idLote)==null) throw new Exception("Lote inexistente");

        setCantidad(cantidad+traerLote(idLote).getCantidadInicial());


    }
    /**/

    public boolean agregarLote(int cantidadInicial, LocalDate fechaIngreso, Producto producto) {
        int id =1;
        if(!lotes.isEmpty()) {
            id = lotes.get(lotes.size()-1).getIdLote()+1;
        }

        return lotes.add(new Lote(id,cantidadInicial,cantidadInicial,fechaIngreso,producto));
    }
	public boolean consumoStock(Producto producto, int cantidad) throws Exception{
		if (!stockValido(producto, cantidad)) throw new Exception("No alcanza el stock");
		
		int aux = cantidad;
		int x = 0;
	
			while (x < lotesDelProducto(producto).size() && aux > 0) {
				Lote l = lotesDelProducto(producto).get(x);
				
				if (l.getCantidadActual() > aux) {
					l.setCantidadActual(l.getCantidadActual() - cantidad);
					aux = 0;
				}
				else if (l.getCantidadActual() < aux) {
					aux -= l.getCantidadActual();
					l.setCantidadActual(0);
					l.setEstado(false);
				}
				else if (l.getCantidadActual() == aux) {
					aux = 0;
					l.setCantidadActual(0);
					l.setEstado(false);
				}
				x++;
				
			}
			 
			return true;   // hay suficiente stock 
			
	}
	
	
	
	
	

}