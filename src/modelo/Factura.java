package modelo;

public class Factura {
	private int idFactura;
	private String factura;
	public Factura(int idFactura, String factura) {
		super();
		this.idFactura = idFactura;
		this.factura = factura;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", factura=" + factura + "]";
	}
	
	
	
	
}
