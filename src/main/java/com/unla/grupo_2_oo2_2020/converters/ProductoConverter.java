package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {

	public ProductoModel entityToModel(Producto producto) {
		return new ProductoModel(producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(),
				producto.getPrecio(), producto.getFechaAlta());
	}

	public Producto modelToEntity(ProductoModel productoModel) {
		return new Producto(productoModel.getIdProducto(), productoModel.getNombre(), productoModel.getDescripcion(),
				productoModel.getPrecio(), productoModel.getFechaAlta());
	}
}