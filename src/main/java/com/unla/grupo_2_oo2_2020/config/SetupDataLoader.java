package com.unla.grupo_2_oo2_2020.config;

import java.time.LocalDate;
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
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

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
    // TODO ingresar privilegios correctos y demas
    if (alreadySetup)
      return;

    // cliente
    Privilege createClientePrivilege = createPrivilegeIfNotFound("CREATE_CLIENTE_PRIVILEGE");
    Privilege editClientePrivilege = createPrivilegeIfNotFound("EDIT_CLIENTE_PRIVILEGE");
    Privilege deleteClientePrivilege = createPrivilegeIfNotFound("DELETE_CLIENTE_PRIVILEGE");
    Privilege viewClientePrivilege = createPrivilegeIfNotFound("VIEW_CLIENTE_PRIVILEGE");

    // producto
    Privilege createProductoPrivilege = createPrivilegeIfNotFound("CREATE_PRODUCTO_PRIVILEGE");
    Privilege editProductoPrivilege = createPrivilegeIfNotFound("EDIT_PRODUCTO_PRIVILEGE");
    Privilege deleteProductoPrivilege = createPrivilegeIfNotFound("DELETE_PRODUCTO_PRIVILEGE");
    Privilege viewProductoPrivilege = createPrivilegeIfNotFound("VIEW_PRODUCTO_PRIVILEGE");

    // local
    Privilege createLocalPrivilege = createPrivilegeIfNotFound("CREATE_LOCAL_PRIVILEGE");
    Privilege editLocalPrivilege = createPrivilegeIfNotFound("EDIT_LOCAL_PRIVILEGE");
    Privilege deleteLocalPrivilege = createPrivilegeIfNotFound("DELETE_LOCAL_PRIVILEGE");
    Privilege viewLocalPrivilege = createPrivilegeIfNotFound("VIEW_LOCAL_PRIVILEGE");

    // empleado
    Privilege createEmpleadoPrivilege = createPrivilegeIfNotFound("CREATE_EMPLEADO_PRIVILEGE");
    Privilege editEmpleadoPrivilege = createPrivilegeIfNotFound("EDIT_EMPLEADO_PRIVILEGE");
    Privilege deleteEmpleadoPrivilege = createPrivilegeIfNotFound("DELETE_EMPLEADO_PRIVILEGE");
    Privilege viewEmpleadoPrivilege = createPrivilegeIfNotFound("VIEW_EMPLEADO_PRIVILEGE");

    // lote
    Privilege createLotePrivilege = createPrivilegeIfNotFound("CREATE_LOTE_PRIVILEGE");
    Privilege editLotePrivilege = createPrivilegeIfNotFound("EDIT_LOTE_PRIVILEGE");
    Privilege deleteLotePrivilege = createPrivilegeIfNotFound("DELETE_LOTE_PRIVILEGE");
    Privilege viewLotePrivilege = createPrivilegeIfNotFound("VIEW_LOTE_PRIVILEGE");

    // pedido
    Privilege sendPedidoPrivilege = createPrivilegeIfNotFound("SEND_PEDIDO_PRIVILEGE");
    Privilege handlePedidoPrivilege = createPrivilegeIfNotFound("HANDLE_PEDIDO_PRIVILEGE");
    Privilege viewPedidoPrivilege = createPrivilegeIfNotFound("VIEW_PEDIDO_PRIVILEGE");

    // reportes
    Privilege viewReportPrivilege = createPrivilegeIfNotFound("VIEW_REPORT_PRIVILEGE");

    List<Privilege> adminPrivileges = Arrays.asList(createClientePrivilege, editClientePrivilege,
        deleteClientePrivilege, viewClientePrivilege, createEmpleadoPrivilege, editEmpleadoPrivilege,
        deleteEmpleadoPrivilege, viewEmpleadoPrivilege, createLocalPrivilege, editLocalPrivilege, deleteLocalPrivilege,
        viewLocalPrivilege, createLotePrivilege, editLotePrivilege, deleteLotePrivilege, viewLotePrivilege,
        createProductoPrivilege, editProductoPrivilege, deleteProductoPrivilege, viewProductoPrivilege,
        sendPedidoPrivilege, handlePedidoPrivilege, viewPedidoPrivilege, viewReportPrivilege);

    List<Privilege> clientePrivileges = Arrays.asList(editClientePrivilege, viewLocalPrivilege, viewLotePrivilege,
        viewProductoPrivilege, sendPedidoPrivilege, viewPedidoPrivilege);

    List<Privilege> vendedorPrivileges = Arrays.asList(viewClientePrivilege, editEmpleadoPrivilege,
        viewEmpleadoPrivilege, viewLocalPrivilege, editLotePrivilege, viewLotePrivilege, viewProductoPrivilege,
        handlePedidoPrivilege, viewPedidoPrivilege, viewReportPrivilege);

    // NOTE para mod/edit/del productos, añadir columna origenLocal para verificar
    // si el producto es del local del gerente
    List<Privilege> gerentePrivileges = Arrays.asList(viewClientePrivilege, createEmpleadoPrivilege,
        editEmpleadoPrivilege, deleteEmpleadoPrivilege, viewEmpleadoPrivilege, editLocalPrivilege, viewLocalPrivilege,
        createLotePrivilege, editLotePrivilege, deleteLotePrivilege, viewLotePrivilege, createProductoPrivilege,
        editProductoPrivilege, deleteProductoPrivilege, viewProductoPrivilege, handlePedidoPrivilege,
        viewPedidoPrivilege, viewReportPrivilege);

    createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    createRoleIfNotFound("ROLE_CLIENTE", clientePrivileges);
    createRoleIfNotFound("ROLE_VENDEDOR", vendedorPrivileges);
    createRoleIfNotFound("ROLE_GERENTE", gerentePrivileges);

    Role adminRole = roleRepository.findByName("ROLE_ADMIN");

    User user = new User();

    user.setNombre("Administrator");
    user.setApellido("1");
    user.setDni(1111);
    user.setFechaNacimiento(LocalDate.now());
    user.setUsername("admin1");
    user.setPassword(bCryptPasswordEncoder.encode("admin123"));
    user.setRoles(Arrays.asList(adminRole));
    // set info admin

    if(userRepository.findByUsername(user.getUsername()) == null) {
      userRepository.save(user);
    }

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
  private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      role.setPrivileges(privileges);
      roleRepository.save(role);
    }
    return role;
  }
}