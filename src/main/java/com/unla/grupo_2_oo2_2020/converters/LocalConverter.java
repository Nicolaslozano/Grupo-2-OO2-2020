package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.LocalModel;

@Component("localConverter")
public class LocalConverter {

	public LocalModel entityToModel(Local local) {
		return new LocalModel(local.getIdLocal(), local.getDireccion(), local.getLatitud(), local.getLongitud(),
				local.getTelefono());
	}

	public Local modelToEntity(LocalModel localModel) {
		return new Local(localModel.getIdLocal(), localModel.getDireccion(), localModel.getLatitud(),
				localModel.getLongitud(), localModel.getTelefono());
	}
}