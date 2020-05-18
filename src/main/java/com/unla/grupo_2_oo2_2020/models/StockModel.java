package com.unla.grupo_2_oo2_2020.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockModel {

	private long idStock;
	private int cantidad;

	public StockModel(long idStock,int cantidad) {
		this.idStock = idStock;
		this.cantidad = cantidad;
	}

}