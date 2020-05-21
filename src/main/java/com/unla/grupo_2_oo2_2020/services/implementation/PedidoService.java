package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pedidoService")
public class PedidoService implements IPedidoService {

    @Autowired
    @Qualifier("localService")
    private ILocalService localService;

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @Autowired
    @Qualifier("stockService")
    private IStockService stockService;

    @Autowired
    @Qualifier("loteService")
    private ILoteService loteService;

    @Override
    public boolean validatePedido(PedidoModel pedidoModel) {
        // TODO

        // stock service validate stock (pedidomodel)

        // if true, then lote.cantidad - pedido.cantidad and save it
        // if local has a bit, then send solicitud of only needed stock to closest local
        // else if false, then send solicitud to closest local that has enough
        // if no one has, then IMSORRYBUDDY
        return stockService.comprobarStock(pedidoModel);
    }

    @Override
    public double getTotal(PedidoModel pedidoModel) {

        return ((productoService.findById(pedidoModel.getIdProducto())).getPrecio() * pedidoModel.getCantidad());
    }

}