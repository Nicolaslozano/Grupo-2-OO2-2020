package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.PedidoConverter;
import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.entities.Producto;
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
    public List<Pedido> getAccepted() {
        return pedidoRepository.findAccepted();
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

        List<Pedido> pedidos = new ArrayList<Pedido>();

        for(Pedido p : pedidoRepository.findByLocalNotExternal(local)) {

            pedidos.add(p);
        }

        for(Pedido p : pedidoRepository.findPendingByLocal(local)) {

            pedidos.add(p);
        }
        return pedidos;
    }

    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }

    @Override
    public List<Pedido> findPendingByLocalAndProducto(Local local, Producto producto) {
        return pedidoRepository.findPendingByLocalAndProducto(local, producto);
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
    public Map<ProductoModel, Integer> productosEntreFechas(LocalDate fecha1, LocalDate fecha2, Local local) {

        Map<ProductoModel, Integer> entreFechas = new HashMap<>();
        ProductoModel productoAuxiliar = new ProductoModel();
        List<Pedido> pedidos;
        if (local != null) {
            pedidos = findByLocal(local);
        } else {
            pedidos = getAll();
        }

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() != StaticValuesHelper.PEDIDO_ACEPTADO)
                continue;
            if ((pedido.getFecha().isAfter(fecha1) || pedido.getFecha().isEqual(fecha1))
                    && (pedido.getFecha().isBefore(fecha2) || pedido.getFecha().isEqual(fecha2))) {

                productoAuxiliar = productoConverter
                        .entityToModel(productoService.findById(pedido.getProducto().getIdProducto()));

                if (entreFechas.get(productoAuxiliar) != null) {

                    entreFechas.put(productoAuxiliar, pedido.getCantidad() + entreFechas.get(productoAuxiliar));
                } else {

                    entreFechas.put(productoAuxiliar, pedido.getCantidad());
                }

            }

        }

        return entreFechas;
    }

    @Override
    public double getTotal(Pedido pedido) {
        return ((productoService.findById(pedido.getProducto().getIdProducto())).getPrecio() * pedido.getCantidad());
    }

    @Override
    public double getTotal(PedidoModel pedidoModel) {
        return ((productoService.findById(pedidoModel.getIdProducto())).getPrecio() * pedidoModel.getCantidad());
    }

}