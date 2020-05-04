package com.unla.grupo_2_oo2_2020.models;


import java.util.HashSet;
import java.util.Set;

public class StockModel {

	private long idStock;
	private Set<LoteModel> lotes;
	private int cantidad;
	private LocalModel local;

	public StockModel() {
	}

	public StockModel(int cantidad, LocalModel local) {
		super();
		this.lotes = new HashSet<LoteModel>();
		this.cantidad = cantidad;
		this.local = local;
	}

	public long getIdStock() {
		return idStock;
	}

	protected void setIdStock(long idStock) {
		this.idStock = idStock;
	}

	public Set<LoteModel> getLotes() {
		return lotes;
	}

	public void setLotes(Set<LoteModel> lotes) {
		this.lotes = lotes;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + idStock + ", lotes=" + lotes + ", cantidad=" + cantidad + "]";
	}

}