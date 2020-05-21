package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;

public interface IProductoService {

	public List<Producto> getAll();

	public Producto findById(long idProducto);

	public ProductoModel insertOrUpdate(ProductoModel ProductoModel);

	public void removeById(long idProducto);
}