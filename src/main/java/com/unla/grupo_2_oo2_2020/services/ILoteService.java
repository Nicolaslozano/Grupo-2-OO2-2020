package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.LoteModel;


public interface ILoteService {
	public List<Lote> getAll();

	public Lote findById(long idLote);

	public List<Lote> findByStock(Stock stock);

	public List<Lote> findByProducto(Producto producto);

	public List<Lote> findByProductoAndStock(Producto producto, Stock stock);

	public LoteModel insertOrUpdate(LoteModel LoteModel);

	public void removeById(long idLote);

	public void consumirProductos(long idLote, int cantidad);
}

