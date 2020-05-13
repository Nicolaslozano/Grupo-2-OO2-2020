package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;

@Component("pedidoConverter")
public class PedidoConverter {

    public PedidoModel entityToModel(Pedido pedido) {
        return new PedidoModel(pedido.getIdPedido(), pedido.getProducto().getIdProducto(), pedido.getCantidad(),
                pedido.getLocal().getIdLocal(), pedido.getCliente().getIdPersona(),
                pedido.getVendedorOriginal().getIdPersona(), pedido.getVendedorAuxiliar().getIdPersona(),
                pedido.isAceptado());
    }

    public Pedido modelToEntity(PedidoModel pedidoModel) {
        return new Pedido(pedidoModel.getIdPedido(), pedidoModel.getCantidad(), pedidoModel.isAceptado());
    }
}