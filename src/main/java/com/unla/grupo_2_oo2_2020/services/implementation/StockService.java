package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.grupo_2_oo2_2020.converters.StockConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.models.StockModel;
import com.unla.grupo_2_oo2_2020.repository.ILoteRepository;
import com.unla.grupo_2_oo2_2020.repository.IStockRepository;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
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
    @Qualifier("loteService")
    private ILoteService loteService;

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findById(long idLocal) {

        return stockRepository.findByIdStock(idLocal);
    }

    @Override
    public boolean comprobarStock(PedidoModel pedido) {

        boolean disponibleLocalmente = false;

        for (Lote lote : loteService.findByProductoAndStock(productoService.findById(pedido.getIdProducto()),
                findById(pedido.getIdLocal()))) {

            if(lote.getCantidadActual() >= pedido.getCantidad()) {

                disponibleLocalmente = true;
                loteService.consumirProductos(lote.getIdLote(), pedido.getCantidad());
            }
        }

        return disponibleLocalmente;
    }



}