package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.HashSet;

import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.repository.IRoleRepository;
import com.unla.grupo_2_oo2_2020.repository.IUserRepository;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService{
    
    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @Autowired
    @Qualifier("roleRepository")
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}