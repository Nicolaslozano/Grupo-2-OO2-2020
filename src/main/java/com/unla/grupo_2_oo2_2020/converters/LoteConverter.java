package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;
import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.models.LoteModel;

@Component("loteConverter")
public class LoteConverter {

	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(), lote.getCantidadActual(),
				lote.getProducto().getIdProducto(), lote.getStock().getIdStock(), lote.isEstado());
	}

	public Lote modelToEntity(LoteModel loteModel) {
		return new Lote(loteModel.getIdLote(), loteModel.getCantidadInicial(), loteModel.getCantidadActual(), loteModel.isEstado());
	}
}
