package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;

@Component("empleadoConverter")
public class EmpleadoConverter {

	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(),
				empleado.getFechaNacimiento(), empleado.getDni(), empleado.getFranjaHoraria(),
				empleado.isTipoEmpleado(), empleado.getLocal().getIdLocal());
	}

	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(empleadoModel.getIdPersona(), empleadoModel.getNombre(), empleadoModel.getApellido(),
				empleadoModel.getFechaNacimiento(), empleadoModel.getDni(), empleadoModel.getFranjaHoraria(),
				empleadoModel.isTipoEmpleado());
	}
}