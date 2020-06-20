package com.unla.grupo_2_oo2_2020.services.implementation;

import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.repository.IClienteRepository;
import com.unla.grupo_2_oo2_2020.repository.IDefaultUserRepository;
import com.unla.grupo_2_oo2_2020.repository.IEmpleadoRepository;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase utilizada para buscar un usuario de cualquier tipo
 */

@Service("userService")
public class UserService implements IUserService{

    @Autowired
    @Qualifier("clienteRepository")
    private IClienteRepository clienteRepository;

    @Autowired
	@Qualifier("empleadoRepository")
    private IEmpleadoRepository empleadoRepository;
    
    @Autowired
	@Qualifier("userRepository")
	private IDefaultUserRepository userRepository;

    public User findByUsername(String username) {
        User user = null;

        if((user = clienteRepository.findByUsername(username)) != null) {
            return user;
        }else if((user = empleadoRepository.findByUsername(username)) != null){
            return user;
        } else {
            user = userRepository.findByUsername(username);
            return user;
        }
    }

    public User findByDni(int dni) {
        User user = null;

        if((user = clienteRepository.findByDni(dni)) != null) {
            return user;
        }else if((user = empleadoRepository.findByDni(dni)) != null){
            return user;
        } else {
            user = userRepository.findByDni(dni);
            return user;
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }
}