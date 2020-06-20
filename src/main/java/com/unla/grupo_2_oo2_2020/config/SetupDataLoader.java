package com.unla.grupo_2_oo2_2020.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.Privilege;
import com.unla.grupo_2_oo2_2020.entities.Role;
import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.repository.IDefaultUserRepository;
import com.unla.grupo_2_oo2_2020.repository.IPrivilegeRepository;
import com.unla.grupo_2_oo2_2020.repository.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
    @Qualifier("userRepository")
    private IDefaultUserRepository userRepository;
  
    @Autowired
    @Qualifier("roleRepository")
    private IRoleRepository roleRepository;
  
    @Autowired
    @Qualifier("privilegeRepository")
    private IPrivilegeRepository privilegeRepository;
  
    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;
  
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //TODO ingresar privilegios correctos y demas
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();

        //set info user

        userRepository.save(user);
 
        alreadySetup = true;
    }
 
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    private Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}