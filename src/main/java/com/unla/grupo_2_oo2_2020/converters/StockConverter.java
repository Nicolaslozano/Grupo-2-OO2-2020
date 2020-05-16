package com.unla.grupo_2_oo2_2020.converters;

import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.StockModel;

import org.springframework.stereotype.Component;

@Component("stockConverter")
public class StockConverter {

    public StockModel entityToModel(Stock stock) {
		return new StockModel(stock.getIdStock(),stock.getCantidad());
	}

	public Stock modelToEntity(StockModel stockModel) {
		return new Stock(stockModel.getIdStock(),stockModel.getCantidad());
	}
    
}