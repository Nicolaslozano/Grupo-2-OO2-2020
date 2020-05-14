package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pedidoService")
public class PedidoService implements IPedidoService{

    @Autowired
    @Qualifier("localService")
    private LocalService localService;

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;

    @Autowired
    @Qualifier("clienteService")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    @Override
    public PedidoModel validatePedido(PedidoModel pedidoModel) {
        // TODO

        //stock service validate stock (pedidomodel)

        //if true, then lote.cantidad - pedido.cantidad and save it
        //if local has a bit, then send solicitud of only needed stock to closest local
        //else if false, then send solicitud to closest local that has enough
        //if no one has, then IMSORRYBUDDY
        return null;
    }

    @Override
    public double getTotal(PedidoModel pedidoModel) {

        return ((productoService.findById(pedidoModel.getIdProducto())).getPrecio() *pedidoModel.getCantidad());
    }


}