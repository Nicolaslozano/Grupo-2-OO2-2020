package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.converters.PedidoConverter;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.repository.IPedidoRepository;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
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
    public double getTotal(PedidoModel pedidoModel) {

        return ((productoService.findById(pedidoModel.getIdProducto())).getPrecio() * pedidoModel.getCantidad());
    }

}