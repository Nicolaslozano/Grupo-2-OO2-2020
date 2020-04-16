package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Local {

	private int idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private long telefono;
	private Stock stock;
	private ArrayList<Empleado> listaEmpleados;
	private ArrayList<Factura> listaFacturas;
	private ArrayList<SolicitudStock> listaSolicitudesStock;
	
	public Local(int idLocal, String direccion, double latitud, double longitud, long telefono) {
		super();
		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.stock = new Stock();
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaFacturas = new ArrayList<Factura>();
		this.listaSolicitudesStock = new ArrayList<SolicitudStock>();
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	

	public ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(ArrayList<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	
	

	public ArrayList<SolicitudStock> getListaSolicitudesStock() {
		return listaSolicitudesStock;
	}

	public void setListaSolicitudesStock(ArrayList<SolicitudStock> listaSolicitudesStock) {
		this.listaSolicitudesStock = listaSolicitudesStock;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + ", stock=" + stock + ", empleados=" + listaEmpleados + "]\n";
	}
	
	/**********************************************************************************************************************************************/
	public boolean altaEmpleado(String nombre, String apellido, LocalDate fechaNacimiento, long dni,
			String franjaHoraria, boolean tipoEmpleado) throws Exception {
		if (traerEmpleado(dni) != null)
			throw new Exception("ERROR: El empleado ya existe");
		return listaEmpleados.add(new Empleado(nombre, apellido, fechaNacimiento, dni, franjaHoraria, tipoEmpleado,this));
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
		if (idLocal != other.idLocal)
			return false;
		return true;
	}

	/**********************************************************************************************************************************************/
	public void modificarEmpleado(String nombre, String apellido, LocalDate fechaNacimiento, long dni,
			String franjaHoraria, boolean tipoEmpleado) throws Exception {
		if (traerEmpleado(dni) == null)
			throw new Exception("ERROR: No se encontro al empleado");
		Empleado e = traerEmpleado(dni);
		e.setApellido(apellido);
		e.setDni(dni);
		e.setFechaNacimiento(fechaNacimiento);
		e.setFranjaHoraria(franjaHoraria);
		e.setNombre(nombre);
		e.setTipoEmpleado(tipoEmpleado);
	}
	/**********************************************************************************************************************************************/
	public boolean bajaEmpleado(long dni) throws Exception {
		if (traerEmpleado(dni) == null)
			throw new Exception("ERROR: El DNI ingresado no pertenece a ningun empleado");
		return listaEmpleados.remove(traerEmpleado(dni));
	}

	/**********************************************************************************************************************************************/
	public Empleado traerEmpleado(long dni) {
		int i = 0;
		Empleado empleadoEncontrado = null;
		while (i < listaEmpleados.size() && empleadoEncontrado == null) {
			if (listaEmpleados.get(i).getDni() == dni) {
				empleadoEncontrado = listaEmpleados.get(i);
			}
			i++;
		}
		return empleadoEncontrado;
	}
	
	/**********************************************************************************************************************************************/
	public SolicitudStock traerSolicitud(int idSolicitud) {
		SolicitudStock solicitudEncontrada = null;
		int i = 0;
		
		while(i<listaSolicitudesStock.size() && solicitudEncontrada==null) {
			if(listaSolicitudesStock.get(i).getIdSolicitud()==idSolicitud) {
				solicitudEncontrada = listaSolicitudesStock.get(i);
			}
			
			i++;
		}
		return solicitudEncontrada;
	}
	
	/**********************************************************************************************************************************************/
	public boolean altaSolicitudStock(LocalDate fecha, Producto producto, int cantidad, Empleado vendedor) {
		//No hace falta lanzar excepcion porque de todas formas pueden existir dos solicitudes iguales. Solo estaran diferenciadas por la id
		int id = 1;
		if(!listaSolicitudesStock.isEmpty()) {
			id = listaSolicitudesStock.get(listaSolicitudesStock.size()-1).getIdSolicitud()+1;
		}
		
		return listaSolicitudesStock.add(new SolicitudStock(id,fecha,producto,cantidad,vendedor)); 

	}
	
	/**********************************************************************************************************************************************/
	public void actualizarSolicitudStock(Carrito c,SolicitudStock s,long dni_Colaborador,Cliente cliente,boolean aceptado)throws Exception {
		s.setColaborador(this.traerEmpleado(dni_Colaborador)) ;
		s.setAceptado(aceptado);
		
		//SI FUE ACEPTADA LA SOLICITUD AGREGA AL CARRITO EL PEDIDO
		
		if(aceptado) {
			c.altaPedido(s.getProducto(), s.getCantidad(), this,cliente,s.getVendedor() , s.getColaborador());
			this.getStock().consumoStock(s.getProducto(), s.getCantidad());
		}
		
	}
	
	/**********************************************************************************************************************************************/
	public boolean bajaSolicitud(int idSolicitud) throws Exception{
		if(traerSolicitud(idSolicitud)==null) throw new Exception("ERROR: No se encontro la solicitud a eliminar");
		return listaSolicitudesStock.remove(traerSolicitud(idSolicitud));
	}

	/**********************************************************************************************************************************************/
	public Factura traerFactura(int idFactura) {
		Factura facturaEncontrada = null;
		int i = 0;
		while(i<listaFacturas.size() && facturaEncontrada==null) {
			if(listaFacturas.get(i).getIdFactura()==idFactura) {
				facturaEncontrada = listaFacturas.get(i);
			}
			i++;
		}
		return facturaEncontrada;
	}
	/**********************************************************************************************************************************************/
	public Factura generarFactura( Carrito carrito){
		int id = 1;
		if(!listaFacturas.isEmpty()) {
			id = listaFacturas.get(listaFacturas.size()-1).getIdFactura()+1;
		}
		

		List<String> listaPedido = new ArrayList<String>();
		int index = 1;
		
		
		for (Pedido p : carrito.getListaPedidos()) {
			listaPedido.add("(" + (index++) + ")" + p.getProducto().getNombre() + " X " + p.getCantidad() + "=" + "$"
					+ p.CalcularSubtotal() + "\n");
		}
		
		Cliente c = carrito.getListaPedidos().get(0).getCliente();
		
		Factura f = new Factura(id,("Fecha: " + carrito.getFecha() + "\n" + "\n" + "Cliente: "+ c +"\n" + listaPedido + "\n" + "Total: " + carrito.calcularTotal()));
		
		listaFacturas.add(f);	
		
		
		
		return f;
	}

}