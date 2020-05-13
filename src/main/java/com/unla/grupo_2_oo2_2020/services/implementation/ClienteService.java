package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.ClienteConverter;
import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.repository.IClienteRepository;
import com.unla.grupo_2_oo2_2020.services.IClienteService;



@Service("clienteService")
public class ClienteService implements IClienteService {

    @Autowired
    @Qualifier("clienteRepository")
    private IClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Override
    public List<Cliente> getAll() {
        // TODO Auto-generated method stub
        return clienteRepository.findAll();
    }
	@Override
	public Cliente findById(long idPersona) {

		return clienteRepository.findByIdPersona(idPersona);
	}
    @Override
    public ClienteModel insertOrUpdate(ClienteModel clienteModel) {
		// TODO Auto-generated method stub
		Cliente cliente = clienteConverter.modelToEntity(clienteModel);

		clienteRepository.save(cliente);
		return clienteConverter.entityToModel(cliente);
	}

    @Override
    public void removeById (long idPersona) {
    	clienteRepository.deleteById(idPersona);
    }
}