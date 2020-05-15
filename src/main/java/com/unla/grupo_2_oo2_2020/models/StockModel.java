package com.unla.grupo_2_oo2_2020.models;

import com.unla.grupo_2_oo2_2020.entities.Local;

public class StockModel {

	private long idStock;
	private int cantidad;
	private Local local;
		
	public StockModel() {
	}

	public StockModel(long idStock,int cantidad,Local local) {
		this.idStock = idStock;
		this.cantidad = cantidad;
		this.local= local;
	}
	public StockModel(int cantidad,Local local) {
		this.cantidad = cantidad;
		this.local = local;
	}

	public long getIdStock() {
		return idStock;
	}

	public void setIdStock(long idStock) {
		this.idStock = idStock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + idStock + ", cantidad=" + cantidad + "]";
	}

}