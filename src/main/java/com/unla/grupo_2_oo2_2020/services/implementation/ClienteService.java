package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.ClienteConverter;
import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.repository.IClienteRepository;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IRoleService;



@Service("clienteService")
public class ClienteService implements IClienteService {

    @Autowired
    @Qualifier("clienteRepository")
    private IClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Cliente> getAll() {
        // TODO Auto-generated method stub
        return clienteRepository.findAll();
    }

	@Override
	public Cliente findById(long id) {

        return clienteRepository.findById(id);
    }

    @Override
    public Cliente findByDni(int dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
	public Cliente findByUsername(String username) {
		return clienteRepository.findByUsername(username);
	}

    @Override
    public ClienteModel insertOrUpdate(ClienteModel clienteModel) {

        Cliente cliente = clienteConverter.modelToEntity(clienteModel);
        cliente.setPassword(bCryptPasswordEncoder.encode(cliente.getPassword()));
        cliente.setRoles(Arrays.asList(roleService.findByUserType(cliente)));

		clienteRepository.save(cliente);
		return clienteConverter.entityToModel(cliente);
	}

    @Override
    public void removeById (long id) {
    	clienteRepository.deleteById(id);
    }
}