package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.repository.IProductoRepository;
import com.unla.grupo_2_oo2_2020.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(long idProducto) {
		return productoRepository.findByIdProducto(idProducto);
	}

	@Override
	public ProductoModel insertOrUpdate(ProductoModel productoModel) {
		Producto producto;

		if (productoModel.getIdProducto() > 0) {

			producto = findById(productoModel.getIdProducto());
			producto.setNombre(productoModel.getNombre());
			producto.setDescripcion(productoModel.getDescripcion());
			producto.setPrecio(productoModel.getPrecio());
		} else {

			producto = productoConverter.modelToEntity(productoModel);
		}

		productoRepository.save(producto);
		return productoConverter.entityToModel(producto);
	}

	@Override
	public void removeById(long idProducto) {
		productoRepository.deleteById(idProducto);
	}
}