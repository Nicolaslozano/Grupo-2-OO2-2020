package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.grupo_2_oo2_2020.converters.StockConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.StockModel;
import com.unla.grupo_2_oo2_2020.repository.ILoteRepository;
import com.unla.grupo_2_oo2_2020.repository.IStockRepository;
import com.unla.grupo_2_oo2_2020.services.IStockService;

@Service("stockService")
public class StockService implements IStockService {

    
    @Autowired
    @Qualifier("stockRepository")
    private IStockRepository stockRepository;

    @Autowired
    @Qualifier("stockConverter")
    private StockConverter stockConverter;
    
    @Autowired
    @Qualifier("loteRepository")
    private ILoteRepository loteRepository;
    
    
    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findById(long idStock) {

        return stockRepository.findByIdStock(idStock);
    }



	@Override
	public StockModel insertOrUpdate(StockModel stockModel) {
		
		Stock stock;

		if (stockModel.getIdStock() > 0) {

			stock = findById(stockModel.getIdStock());
		
		} else {

			stock = stockConverter.modelToEntity(stockModel);
		}

		stockRepository.save(stock);
		return stockConverter.entityToModel(stock);
	}
	@Override
	public boolean comprobarStock(long idProducto, int cantidad) {
		boolean Disponibilidad = true;
		for (Lote l : loteRepository.findAll()) {
			if (idProducto == l.getProducto().getIdProducto() && l.getCantidadActual() < cantidad) {
				Disponibilidad = false;
			} else {
				Disponibilidad = true;
			}
		}
		return Disponibilidad;
	}

}