package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    public abstract Producto findByIdProducto(long idProducto);

    public abstract List<Producto> findAll();
}