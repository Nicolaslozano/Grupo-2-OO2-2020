package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.PedidoConverter;
import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.repository.IPedidoRepository;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;
import java.util.LinkedHashMap;

import static java.util.stream.Collectors.*;

import java.time.LocalDate;

@Service("pedidoService")
public class PedidoService implements IPedidoService {

    @Autowired
    @Qualifier("pedidoRepository")
    private IPedidoRepository pedidoRepository;

    @Autowired
    @Qualifier("localService")
    private ILocalService localService;

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @Autowired
    @Qualifier("stockService")
    private IStockService stockService;

    @Autowired
    @Qualifier("pedidoConverter")
    private PedidoConverter pedidoConverter;

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(long idPedido) {
        return pedidoRepository.findByIdPedido(idPedido);
    }

    @Override
    public void insertOrUpdate(PedidoModel pedidoModel) {

        Pedido pedido = pedidoConverter.modelToEntity(pedidoModel);

        pedido.setCliente(clienteService.findById(pedidoModel.getIdCliente()));
        pedido.setLocal(localService.findById(pedidoModel.getIdLocal()));
        pedido.setProducto(productoService.findById(pedidoModel.getIdProducto()));
        pedido.setVendedorOriginal(empleadoService.findById(pedidoModel.getIdVendedorOriginal()));
        if (pedidoModel.getIdVendedorAuxiliar() > 0)
            pedido.setVendedorAuxiliar(empleadoService.findById(pedidoModel.getIdVendedorAuxiliar()));

        pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> findByLocal(Local local) {
        return pedidoRepository.findByLocal(local);
    }

    @Override
    public void removeById(long idPedido) {
        pedidoRepository.deleteById(idPedido);
    }

    @Override
    public Map<ProductoModel, Integer> rankingProductos() {

        Map<ProductoModel, Integer> rankingmap = new HashMap<>();
        ProductoModel productoAuxiliar = new ProductoModel();

        for (Pedido pedidos : getAll()) {
            if (pedidos.getEstado() != StaticValuesHelper.PEDIDO_ACEPTADO)
                continue;

            productoAuxiliar = productoConverter
                    .entityToModel(productoService.findById(pedidos.getProducto().getIdProducto()));

            if (rankingmap.get(productoAuxiliar) != null) { // si el producto ya se contó, que sume lo que ya estaba

                rankingmap.put(productoAuxiliar, pedidos.getCantidad() + rankingmap.get(productoAuxiliar));
            } else {

                rankingmap.put(productoAuxiliar, pedidos.getCantidad());
            }

        }
        Map<ProductoModel, Integer> sorted = rankingmap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        return sorted;
    }

    @Override
    public Map<ProductoModel, Integer> productosEntreFechas(LocalDate fecha1, LocalDate fecha2) {

        Map<ProductoModel, Integer> entreFechas = new HashMap<>();
        ProductoModel productoAuxiliar = new ProductoModel();

        for (Pedido pedidos : getAll()) {
            if (pedidos.getEstado() != StaticValuesHelper.PEDIDO_ACEPTADO)
                continue;
            if ((pedidos.getFecha().isAfter(fecha1) || pedidos.getFecha().isEqual(fecha1))
                    && (pedidos.getFecha().isBefore(fecha2) || pedidos.getFecha().isEqual(fecha2))) {

                productoAuxiliar = productoConverter
                        .entityToModel(productoService.findById(pedidos.getProducto().getIdProducto()));

                if (entreFechas.get(productoAuxiliar) != null) {

                    entreFechas.put(productoAuxiliar, pedidos.getCantidad() + entreFechas.get(productoAuxiliar));
                } else {

                    entreFechas.put(productoAuxiliar, pedidos.getCantidad());
                }

            }

        }

        return entreFechas;
    }

    @Override
    public double getTotal(PedidoModel pedidoModel) {

        return ((productoService.findById(pedidoModel.getIdProducto())).getPrecio() * pedidoModel.getCantidad());
    }

}