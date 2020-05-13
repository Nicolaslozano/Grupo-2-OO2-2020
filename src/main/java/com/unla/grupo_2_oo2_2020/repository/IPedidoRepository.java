package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable> {

    public abstract Pedido findByIdPedido(long idPedido);

    public abstract List<Pedido> findAll();

    public abstract List<Pedido> findByCliente(Cliente cliente);

    public abstract List<Pedido> findByLocal(Local local);
}