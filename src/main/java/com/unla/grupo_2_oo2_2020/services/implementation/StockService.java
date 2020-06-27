package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.grupo_2_oo2_2020.converters.StockConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.repository.IStockRepository;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

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
    @Qualifier("localService")
    private ILocalService localService;

    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findById(long id) {

        return stockRepository.findByIdStock(id);
    }

    @Override
    public void removeById(long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public boolean comprobarStock(PedidoModel pedido, boolean persistChanges) {

        boolean disponibleLocalmente = false;
        int cantidadAlcanzada = 0;
        int cantidadPorConseguir = pedido.getCantidad();
        List<Lote> lotesVaciados = new ArrayList<Lote>();

        // antes que nada se toman en cuenta los pedidos pendientes restandole la
        // cantidad total de productos pendientes de entrega al total que se puede
        // ofrecer

        if (pedido.getEstado() == StaticValuesHelper.PEDIDO_PENDIENTE) {

            /*
             * Si el pedido fue tercerizado entonces se chequean los pedidos de aquel local
             */

            for (Pedido p : pedidoService.findPendingByLocalAndProducto(
                    localService.findById(empleadoService.findById(pedido.getIdVendedorAuxiliar()).getLocal().getIdLocal()),
                    productoService.findById(pedido.getIdProducto()))) {

                if(p.getIdPedido() == pedido.getIdPedido()) continue;

                cantidadAlcanzada -= p.getCantidad();
            }

        } else {

            /*
             * Si es un pedido comun, se chequea el propio local
             */

            for (Pedido p : pedidoService.findPendingByLocalAndProducto(localService.findById(pedido.getIdLocal()),
                    productoService.findById(pedido.getIdProducto()))) {

                cantidadAlcanzada -= p.getCantidad();
            }
        }

        for (Lote lote : loteService.findByProductoAndStock(productoService.findById(pedido.getIdProducto()),
                findById(pedido.getIdLocal()))) {

            cantidadAlcanzada += lote.getCantidadActual();

            if (cantidadAlcanzada >= cantidadPorConseguir) {

                disponibleLocalmente = true;

                if (persistChanges)
                    loteService.consumirProductos(lote.getIdLote(), cantidadPorConseguir);

                break;

            } else {
                cantidadPorConseguir -= cantidadAlcanzada;
                lotesVaciados.add(lote);
            }
        }

        if (disponibleLocalmente && persistChanges) {

            for (Lote loteVaciado : lotesVaciados) {

                loteService.consumirProductos(loteVaciado.getIdLote(), loteVaciado.getCantidadActual());
            }
        }

        return disponibleLocalmente;
    }

}