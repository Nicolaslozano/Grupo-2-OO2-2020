package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.LoteConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.LoteModel;
import com.unla.grupo_2_oo2_2020.repository.ILoteRepository;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;

@Service("loteService")
public class LoteService implements ILoteService {

	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;

	@Override
	public Lote findById(long idLote) {

		return loteRepository.findByIdLote(idLote);
	}

	@Override
	public List<Lote> getAll() {
		// TODO Auto-generated method stub
		return loteRepository.findAll();
	}

	@Override
	public List<Lote> findByStock(Stock stock) {
		return loteRepository.findByStock(stock);
	}

	@Override
	public List<Lote> findByProducto(Producto producto) {
		return loteRepository.findByProducto(producto);
	}

	@Override
	public List<Lote> findByProductoAndStock(Producto producto, Stock stock) {
		return loteRepository.findByProductoAndStock(producto, stock);
	}

	@Override
	public LoteModel insertOrUpdate(LoteModel loteModel) {
		// TODO Auto-generated method stub
		Lote lote = loteConverter.modelToEntity(loteModel);
		lote.setProducto(productoService.findById(loteModel.getIdProducto()));
		lote.setStock(stockService.findById(loteModel.getIdStock()));
		if (lote.getIdLote() == 0)
			lote.setCantidadActual(lote.getCantidadInicial());

		loteRepository.save(lote);
		return loteConverter.entityToModel(lote);
	}

	@Override
	public void removeById(long idLote) {
		loteRepository.deleteById(idLote);
	}

	@Override
	public void consumirProductos(long idLote, int cantidad) {

		Lote loteObjetivo = findById(idLote);

		loteObjetivo.setCantidadActual(loteObjetivo.getCantidadActual() - cantidad);
		if (loteObjetivo.getCantidadActual() == 0)
			loteObjetivo.setEstado(false);

		insertOrUpdate(loteConverter.entityToModel(loteObjetivo));
	}

}
