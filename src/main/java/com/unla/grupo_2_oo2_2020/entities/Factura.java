package com.unla.grupo_2_oo2_2020.entities;

import javax.persistence.*;

 @Entity
 @Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFactura;
	@Column (name = "factura")
	private String factura;

	public Factura() {
	}

	public Factura(String factura) {

		this.factura = factura;
	}

	public long getIdFactura() {
		return idFactura;
	}

	protected void setIdFactura(long idFactura) {
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
