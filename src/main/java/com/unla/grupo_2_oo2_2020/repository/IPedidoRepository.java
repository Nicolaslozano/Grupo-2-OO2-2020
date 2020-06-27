package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {

	public abstract Pedido findByIdPedido(long idPedido);

	public abstract List<Pedido> findAll();

	@Query("SELECT p FROM Pedido p WHERE p.estado=" + StaticValuesHelper.PEDIDO_ACEPTADO)
	public abstract List<Pedido> findAccepted();

	public abstract List<Pedido> findByLocal(Local local);

	@Query("SELECT p FROM Pedido p WHERE p.local=(:local) AND p.vendedorAuxiliar=null")
	public abstract List<Pedido> findByLocalNotExternal(Local local);

	public abstract List<Pedido> findByCliente(Cliente cliente);

	public abstract List<Pedido> findByProducto(Producto producto);

	@Query("SELECT p FROM Pedido p WHERE p.local=(:local) AND p.producto=(:producto)")
	public abstract List<Pedido> findByLocalAndProducto(@Param("local") Local local,
			@Param("producto") Producto producto);

	@Query("SELECT p FROM Pedido p WHERE p.vendedorAuxiliar.local=(:local) AND p.producto=(:producto) AND p.estado="
			+ StaticValuesHelper.PEDIDO_PENDIENTE)
	public abstract List<Pedido> findPendingByLocalAndProducto(@Param("local") Local local,
			@Param("producto") Producto producto);

	@Query("SELECT p FROM Pedido p WHERE p.vendedorAuxiliar.local=(:local)")
	public abstract List<Pedido> findPendingByLocal(@Param("local") Local local);
}
