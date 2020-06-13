package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;


@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {

	public abstract Pedido findByIdPedido(long idPedido);
	 
	public abstract List<Pedido> findAll();

	@Query("SELECT p FROM Pedido p WHERE p.estado="+StaticValuesHelper.PEDIDO_ACEPTADO)
	public abstract List<Pedido> findAccepted();

	public abstract List<Pedido> findByLocal(Local local);

}
