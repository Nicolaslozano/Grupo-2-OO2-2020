package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.LoteConverter;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.models.LoteModel;
import com.unla.grupo_2_oo2_2020.repository.ILoteRepository;
import com.unla.grupo_2_oo2_2020.services.ILoteService;

	
	@Service("loteService")
	public class LoteService implements ILoteService {

		@Autowired
		@Qualifier("loteRepository")
		private ILoteRepository loteRepository;

		@Autowired
		@Qualifier("loteConverter")
		private LoteConverter loteConverter;
		
		@Autowired
		@Qualifier("loteService")
		private ILoteService loteService;

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
		public LoteModel insertOrUpdate(LoteModel loteModel) {
			// TODO Auto-generated method stub
			Lote lote = loteConverter.modelToEntity(loteModel);
			lote.setCantidadActual(loteModel.getCantidadActual());
			lote.setCantidadInicial(loteModel.getCantidadInicial());
			lote.setFechaIngreso(loteModel.getFechaIngreso());
			
			return loteConverter.entityToModel(lote);
		}

		@Override
		public void removeById(long idLote) {
			loteRepository.deleteById(idLote);
		}

}
