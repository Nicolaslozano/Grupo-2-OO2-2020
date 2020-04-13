package datos;

import java.util.HashSet;
import java.util.Set;

public class Local {

	private long idLocal;
	private int telefono;
	private String direccion;
	private float latitud;
	private float altitud;
	private Set<Lote> lotes = new HashSet();
	private Set<Empleado> lstEmpleados = new HashSet();
	private Set<Lote> lstClientes = new HashSet();

	public Local() {

	}

	public Local(int telefono, String direccion, float latitud, float altitud, Set<Lote> lotes,
			Set<Empleado> lstEmpleados, Set<Lote> lstClientes) {

		this.telefono = telefono;
		this.direccion = direccion;
		this.latitud = latitud;
		this.altitud = altitud;
		this.lotes = lotes;
		this.lstEmpleados = lstEmpleados;
		this.lstClientes = lstClientes;
	}

	public long getIdLocal() {
		return idLocal;
	}

	protected void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getAltitud() {
		return altitud;
	}

	public void setAltitud(float altitud) {
		this.altitud = altitud;
	}

	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}

	public Set<Empleado> getLstEmpleados() {
		return lstEmpleados;
	}

	public void setLstEmpleados(Set<Empleado> lstEmpleados) {
		this.lstEmpleados = lstEmpleados;
	}

	public Set<Lote> getLstClientes() {
		return lstClientes;
	}

	public void setLstClientes(Set<Lote> lstClientes) {
		this.lstClientes = lstClientes;
	}

}
