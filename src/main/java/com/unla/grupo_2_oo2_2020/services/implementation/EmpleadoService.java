package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo_2_oo2_2020.converters.EmpleadoConverter;
import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.repository.IEmpleadoRepository;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;

	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Override
	public Empleado findById(long idPersona) {

		return empleadoRepository.findByIdPersona(idPersona);
	}

	@Override
	public Empleado findByIdFetchEagerly(long idPersona) {

		return empleadoRepository.findByIdPersona_wDependencies(idPersona);
	}

	@Override
	public List<Empleado> getAll() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll();
	}

	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		// TODO Auto-generated method stub
		Empleado empleado = empleadoConverter.modelToEntity(empleadoModel);

		empleado.setLocal(localService.findById(empleadoModel.getIdLocal()));

		empleadoRepository.save(empleado);
		return empleadoConverter.entityToModel(empleado);
	}

	@Override
	public void removeById(long idPersona) {
		empleadoRepository.deleteById(idPersona);
	}

}