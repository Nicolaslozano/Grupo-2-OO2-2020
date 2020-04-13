package datos;

import java.util.HashSet;
import java.util.Set;

public class SistemaStock {

	private Set<Producto> lstProductos = new HashSet();
	private Set<Comprobante> lstComprobante = new HashSet();
	private Set<Local> lstLocal = new HashSet();

	public SistemaStock(Set<Producto> lstProductos, Set<Comprobante> lstComprobante, Set<Local> lstLocal) {
		this.lstProductos = lstProductos;
		this.lstComprobante = lstComprobante;
		this.lstLocal = lstLocal;
	}

	public Set<Producto> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(Set<Producto> lstProductos) {
		this.lstProductos = lstProductos;
	}

	public Set<Comprobante> getLstComprobante() {
		return lstComprobante;
	}

	public void setLstComprobante(Set<Comprobante> lstComprobante) {
		this.lstComprobante = lstComprobante;
	}

	public Set<Local> getLstLocal() {
		return lstLocal;
	}

	public void setLstLocal(Set<Local> lstLocal) {
		this.lstLocal = lstLocal;
	}

}
