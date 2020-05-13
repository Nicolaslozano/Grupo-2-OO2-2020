package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.repository.IPedidoRepository;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.converters.PedidoConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pedidoService")
public class PedidoService implements IPedidoService{

    @Autowired
    @Qualifier("pedidoRepository")
    private IPedidoRepository pedidoRepository;

    @Autowired
    @Qualifier("pedidoConverter")
    private PedidoConverter pedidoConverter;

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
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }

    @Override
    public List<Pedido> findByLocal(Local local) {
        return pedidoRepository.findByLocal(local);
    }

    @Override
    public Pedido findById(long idPedido) {
        return pedidoRepository.findByIdPedido(idPedido);
    }

    @Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {
        //TODO verificar si hay stock y solicitud de stock a otros locales y todo eso
        
        Pedido pedido = pedidoConverter.modelToEntity(pedidoModel);

		pedido.setCliente(clienteService.findById(pedidoModel.getidCliente()));
        pedido.setLocal(localService.findById(pedidoModel.getidLocal()));
        pedido.setVendedorOriginal(empleadoService.findById(pedidoModel.getidVendedorOriginal()));
        pedido.setVendedorAuxiliar(empleadoService.findById(pedidoModel.getidVendedorAuxiliar()));

		pedidoRepository.save(pedido);
		return pedidoConverter.entityToModel(pedido);

    }

    @Override
	public void removeById(long idPedido) {

    }

}