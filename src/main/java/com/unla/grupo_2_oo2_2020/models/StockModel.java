package com.unla.grupo_2_oo2_2020.models;

public class StockModel {

	private long idStock;
	private int cantidad;

	public StockModel() {
	}

	public StockModel(long idStock,int cantidad) {
		super();
		this.idStock = idStock;
		this.cantidad = cantidad;
	}

	public long getIdStock() {
		return idStock;
	}

	protected void setIdStock(long idStock) {
		this.idStock = idStock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + idStock + ", cantidad=" + cantidad + "]";
	}

}