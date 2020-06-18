package com.unla.grupo_2_oo2_2020.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import com.unla.grupo_2_oo2_2020.entities.Role;
import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.repository.IUserRepository;

/**
 * To implement login/authentication with Spring Security, we need to implement
 * org.springframework.security.core.userdetails.UserDetailsService interface
 */

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }

}