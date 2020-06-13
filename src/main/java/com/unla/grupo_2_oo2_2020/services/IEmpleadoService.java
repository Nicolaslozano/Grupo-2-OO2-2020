package com.unla.grupo_2_oo2_2020.services;

import java.util.List;


import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;

public interface IEmpleadoService {

    public Empleado findById(long idPersona);
	
    public Empleado findByDni(int dni);

    public Empleado findByIdFetchEagerly(long idPersona);

    public List<Empleado> getAll();

    public List<Empleado> findByLocal(Local local);

    public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);

	public void removeById(long idPersona);

	double calcularSueldo(int mes, EmpleadoModel empleadoModel);
	


}