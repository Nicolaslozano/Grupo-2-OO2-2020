package com.unla.grupo_2_oo2_2020.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.Privilege;
import com.unla.grupo_2_oo2_2020.entities.Role;
import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.LoteModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.repository.IDefaultUserRepository;
import com.unla.grupo_2_oo2_2020.repository.IPrivilegeRepository;
import com.unla.grupo_2_oo2_2020.repository.IRoleRepository;
import com.unla.grupo_2_oo2_2020.services.implementation.ClienteService;
import com.unla.grupo_2_oo2_2020.services.implementation.EmpleadoService;
import com.unla.grupo_2_oo2_2020.services.implementation.LocalService;
import com.unla.grupo_2_oo2_2020.services.implementation.LoteService;
import com.unla.grupo_2_oo2_2020.services.implementation.PedidoService;
import com.unla.grupo_2_oo2_2020.services.implementation.ProductoService;

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
  @Qualifier("empleadoService")
  private EmpleadoService empleadoService;
  
  @Autowired
  @Qualifier("clienteService")
  private ClienteService clienteService;
  
  @Autowired
  @Qualifier("productoService")
  private ProductoService productoService;
  
  @Autowired
  @Qualifier("localService")
  private LocalService localService;
  
  @Autowired
  @Qualifier("pedidoService")
  private PedidoService pedidoService;
  
  @Autowired
  @Qualifier("loteService")
  private LoteService loteService;
  

  @Autowired
  @Qualifier("privilegeRepository")
  private IPrivilegeRepository privilegeRepository;

  @Autowired
  @Qualifier("bCryptPasswordEncoder")
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
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

    
 // PRODUCTOS

 	ProductoModel producto = new ProductoModel();
 	producto.setNombre("Yizzi boost");
 	producto.setDescripcion("Zapatillas");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(1500);
 	productoService.insertOrUpdate(producto);
      
 	//////////////////////////////////////////////
 	
 	ProductoModel producto1 = new ProductoModel();
 	producto.setNombre("Chombadidas");
 	producto.setDescripcion("Remeras");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(500);
 	productoService.insertOrUpdate(producto1);
 	
 	//////////////////////////////////////////////

 	ProductoModel producto2 = new ProductoModel();
 	producto.setNombre("Jeans");
 	producto.setDescripcion("Pantalones");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(800);
 	productoService.insertOrUpdate(producto2);
 	
 	//////////////////////////////////////////////

 	ProductoModel producto3 = new ProductoModel();
 	producto.setNombre("Short");
 	producto.setDescripcion("Pantalones");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(600);
 	productoService.insertOrUpdate(producto3);
 	
 	//////////////////////////////////////////////

 	ProductoModel producto4 = new ProductoModel();
 	producto.setNombre("Cordones");
 	producto.setDescripcion("Indumentaria Calzado");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(150);
 	productoService.insertOrUpdate(producto4);
 	
 	//////////////////////////////////////////////

 	ProductoModel producto5 = new ProductoModel();
 	producto.setNombre("Boxer");
 	producto.setDescripcion("Ropa Interior");
 	producto.setFechaAlta(LocalDate.now());
 	producto.setPrecio(300);
 	productoService.insertOrUpdate(producto5);
 	
     
     //LOCALES
     
     LocalModel local1 = new LocalModel();
     local1.setDireccion("Av INdependencia 1127");
     local1.setLatitud(-34.61726);
     local1.setLongitud(-58.381843);
     local1.setTelefono(1111111);
     localService.insertOrUpdate(local1);
     
     //////////////////////////////////////////////
    
     LocalModel local2 = new LocalModel();
     local1.setDireccion("Av. Montes de Oca 1941");
     local1.setLatitud(-34.650165);
     local1.setLongitud(-58.372336);
     local1.setTelefono(222222222);
     localService.insertOrUpdate(local2);
     
     //////////////////////////////////////////////
     
     LocalModel local3 = new LocalModel();
     local1.setDireccion("Sta Magdalena 501-599");
     local1.setLatitud(-34.649291);
     local1.setLongitud(-58.380543);
     local1.setTelefono(333333);
     localService.insertOrUpdate(local3);
     
     
     //////////////////////////////////////////////
     ClienteModel cliente1 = new ClienteModel();

     cliente1.setNombre("Juan");
     cliente1.setApellido("Villareal");
     cliente1.setDni(40201020);
     cliente1.setEmail("cliente1@hotmail.com");
     cliente1.setFechaNacimiento(LocalDate.of(1996, 10, 15));
     cliente1.setUsername("cliente1");
     cliente1.setPassword(bCryptPasswordEncoder.encode("cliente1"));
     clienteService.insertOrUpdate(cliente1);
  
     //////////////////////////////////////////////
     
     ClienteModel cliente2 = new ClienteModel();

     cliente2.setNombre("Jose");
     cliente2.setApellido("Martinez");
     cliente2.setDni(37504060);
     cliente2.setEmail("cliente2@hotmail.com");
     cliente2.setFechaNacimiento(LocalDate.of(1993, 3, 15));
     cliente2.setUsername("cliente2");
     cliente2.setPassword(bCryptPasswordEncoder.encode("cliente2"));
     clienteService.insertOrUpdate(cliente2);
   
     //////////////////////////////////////////////
     
     ClienteModel cliente3 = new ClienteModel();

     cliente3.setNombre("Sebastian");
     cliente3.setApellido("Martinez");
     cliente3.setDni(38207060);
     cliente3.setEmail("cliente3@hotmail.com");
     cliente3.setFechaNacimiento(LocalDate.of(1997, 5, 10));
     cliente3.setUsername("cliente3");
     cliente3.setPassword(bCryptPasswordEncoder.encode("cliente3"));
     clienteService.insertOrUpdate(cliente3);
     
     //////////////////////////////////////////////
     
     ClienteModel cliente4 = new ClienteModel();

     cliente4.setNombre("Patricia");
     cliente4.setApellido("Vidal");
     cliente4.setDni(37406080);
     cliente4.setEmail("cliente4@hotmail.com");
     cliente4.setFechaNacimiento(LocalDate.of(1990, 3, 5));
     cliente4.setUsername("cliente4");
     cliente4.setPassword(bCryptPasswordEncoder.encode("cliente4"));
     clienteService.insertOrUpdate(cliente4);
     
     //EMPLOYEES USERS  LOCAL 1
     
     EmpleadoModel empleado1 = new  EmpleadoModel();

     empleado1.setNombre("Patricio");
     empleado1.setApellido("Benitez");
     empleado1.setDni(27406080);
     empleado1.setFechaNacimiento(LocalDate.of(1985, 3, 5));
     empleado1.setUsername("empleado1");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado1"));
     empleado1.setTipoEmpleado(true);
     empleado1.setIdLocal(local1.getIdLocal());
     empleadoService.insertOrUpdate(empleado1);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado2 = new  EmpleadoModel();

     empleado2.setNombre("Julian");
     empleado2.setApellido("Maruca");
     empleado2.setDni(3903080);
     empleado2.setFechaNacimiento(LocalDate.of(1990, 3, 20));
     empleado2.setUsername("empleado2");
     empleado2.setPassword(bCryptPasswordEncoder.encode("empleado2"));
     empleado2.setTipoEmpleado(false);
     empleado2.setIdLocal(local1.getIdLocal());
     empleadoService.insertOrUpdate(empleado2);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado3 = new  EmpleadoModel();

     empleado1.setNombre("Martin");
     empleado1.setApellido("Benitez");
     empleado1.setDni(39406080);
     empleado1.setFechaNacimiento(LocalDate.of(1997, 1, 5));
     empleado1.setUsername("empleado3");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado3"));
     empleado1.setTipoEmpleado(false);
     empleado1.setIdLocal(local1.getIdLocal());
     empleadoService.insertOrUpdate(empleado3);
     
     //EMPLOYEES USERS  LOCAL 2
     
     EmpleadoModel empleado4 = new  EmpleadoModel();

     empleado1.setNombre("Karen");
     empleado1.setApellido("Perez");
     empleado1.setDni(37406080);
     empleado1.setFechaNacimiento(LocalDate.of(1994, 4, 5));
     empleado1.setUsername("empleado4");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado4"));
     empleado1.setTipoEmpleado(true);
     empleado1.setIdLocal(local2.getIdLocal());
     empleadoService.insertOrUpdate(empleado4);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado5 = new  EmpleadoModel();

     empleado1.setNombre("Martina");
     empleado1.setApellido("Malaspina");
     empleado1.setDni(37409080);
     empleado1.setFechaNacimiento(LocalDate.of(1999, 3, 15));
     empleado1.setUsername("empleado5");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado5"));
     empleado1.setTipoEmpleado(false);
     empleado1.setIdLocal(local2.getIdLocal());
     empleadoService.insertOrUpdate(empleado5);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado6 = new  EmpleadoModel();

     empleado1.setNombre("Jose");
     empleado1.setApellido("Tellagori");
     empleado1.setDni(30446880);
     empleado1.setFechaNacimiento(LocalDate.of(1984, 10, 5));
     empleado1.setUsername("empleado6");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado6"));
     empleado1.setTipoEmpleado(false);
     empleado1.setIdLocal(local2.getIdLocal());
     empleadoService.insertOrUpdate(empleado6);
     
 //EMPLOYEES USERS  LOCAL 3
     
     EmpleadoModel empleado7 = new  EmpleadoModel();

     empleado1.setNombre("Joaquin");
     empleado1.setApellido("Rubio");
     empleado1.setDni(38506080);
     empleado1.setFechaNacimiento(LocalDate.of(1993, 5, 9));
     empleado1.setUsername("empleado7");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado7"));
     empleado1.setTipoEmpleado(true);
     empleado1.setIdLocal(local3.getIdLocal());
     empleadoService.insertOrUpdate(empleado7);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado8 = new  EmpleadoModel();

     empleado1.setNombre("Valeria");
     empleado1.setApellido("Benitez");
     empleado1.setDni(28604010);
     empleado1.setFechaNacimiento(LocalDate.of(1983, 3, 5));
     empleado1.setUsername("empleado8");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado8"));
     empleado1.setTipoEmpleado(false);
     empleado1.setIdLocal(local3.getIdLocal());
     empleadoService.insertOrUpdate(empleado8);
     
     //////////////////////////////////////////////
     
     EmpleadoModel empleado9 = new  EmpleadoModel();

     empleado1.setNombre("Jose");
     empleado1.setApellido("Tellagori");
     empleado1.setDni(30446880);
     empleado1.setFechaNacimiento(LocalDate.of(1990, 3, 5));
     empleado1.setUsername("empleado9");
     empleado1.setPassword(bCryptPasswordEncoder.encode("empleado9"));
     empleado1.setTipoEmpleado(false);
     empleado1.setIdLocal(local3.getIdLocal());	
     empleadoService.insertOrUpdate(empleado9);
     
     //////////////////////////////////////////////
     
     // LOTE
     
     LoteModel lote1local1 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local1.getIdLocal());
     loteService.insertOrUpdate(lote1local1);
     
 //////////////////////////////////////////////
     

     
     LoteModel lote2local1  =new LoteModel();
     lote2local1.setCantidadActual(50);
     lote2local1.setCantidadInicial(50);
     lote2local1.setFechaIngreso(LocalDate.now());
     lote2local1.setIdProducto(producto1.getIdProducto());
     lote2local1.setEstado(false);
     lote2local1.setIdStock(local1.getIdLocal());
     loteService.insertOrUpdate(lote2local1);
     
 //////////////////////////////////////////////
     
  
     
     LoteModel lote3local1 =new LoteModel();
     lote3local1.setCantidadActual(50);
     lote3local1.setCantidadInicial(50);
     lote3local1.setFechaIngreso(LocalDate.now());
     lote3local1.setIdProducto(producto2.getIdProducto());
     lote3local1.setEstado(false);
     lote3local1.setIdStock(local1.getIdLocal());
     loteService.insertOrUpdate(lote3local1);
     
 /////////////////LOCAL 2 /////////////////////////////
     
     LoteModel lote1local2 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto2.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote1local2);
     
 //////////////////////////////////////////////
     LoteModel lote2local2 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto1.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote2local2);
     
 //////////////////////////////////////////////
     LoteModel lote3local2 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto4.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote3local2);
     
 ////////////////////LOCAL 3 //////////////////////////
     
     LoteModel lote1local3 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto3.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote1local3);
     
 //////////////////////////////////////////////
     LoteModel lote2local3 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto1.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote2local3);
     
 //////////////////////////////////////////////
     LoteModel lote3local3 =new LoteModel();
     lote1local1.setCantidadActual(50);
     lote1local1.setCantidadInicial(50);
     lote1local1.setFechaIngreso(LocalDate.now());
     lote1local1.setIdProducto(producto5.getIdProducto());
     lote1local1.setEstado(false);
     lote1local1.setIdStock(local2.getIdLocal());
     loteService.insertOrUpdate(lote3local3);
     
 //////////////////////////////////////////////
     
  //Pedidos
     
    PedidoModel pedido1 =new PedidoModel();
    pedido1.setCantidad(30);
    pedido1.setEstado(2);
    pedido1.setFecha(LocalDate.now());
    pedido1.setIdCliente(cliente1.getId());
    pedido1.setIdLocal(local1.getIdLocal());
    pedido1.setIdProducto(producto.getIdProducto());
    pedido1.setIdVendedorOriginal(empleado1.getId());
    pedidoService.insertOrUpdate(pedido1);
    
    //Pedido2
   
    PedidoModel pedido2 =new PedidoModel();
    pedido1.setCantidad(30);
    pedido1.setEstado(1);
    pedido1.setFecha(LocalDate.now());
    pedido1.setIdCliente(cliente1.getId());
    pedido1.setIdLocal(local1.getIdLocal());
    pedido1.setIdProducto(producto1.getIdProducto());
    pedido1.setIdVendedorOriginal(empleado1.getId());
    pedidoService.insertOrUpdate(pedido2);
    
  //Pedido3
    
    PedidoModel pedido3 =new PedidoModel();
    pedido1.setCantidad(30);
    pedido1.setEstado(3);
    pedido1.setFecha(LocalDate.now());
    pedido1.setIdCliente(cliente1.getId());
    pedido1.setIdLocal(local1.getIdLocal());
    pedido1.setIdProducto(producto3.getIdProducto());
    pedido1.setIdVendedorOriginal(empleado1.getId());
    pedidoService.insertOrUpdate(pedido3);
     
    
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