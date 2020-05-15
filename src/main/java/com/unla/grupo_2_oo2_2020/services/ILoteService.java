package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.models.LoteModel;


public interface ILoteService {
	public List<Lote> getAll();

	public Lote findById(long idLote);

	public LoteModel insertOrUpdate(LoteModel LoteModel);

	public void removeById(long idLote);
}

