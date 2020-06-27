package com.unla.grupo_2_oo2_2020.services;

import java.util.List;


import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;

public interface IEmpleadoService {

    public Empleado findById(long id);
	
    public Empleado findByDni(int dni);

    public List<Empleado> getAll();

    public List<Empleado> findByLocal(Local local);

    public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);

	public void removeById(long id);

	double calcularSueldo(int mes, EmpleadoModel empleadoModel);
	
    public Empleado findByUsername(String username);

}