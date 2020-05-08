package com.unla.grupo_2_oo2_2020.services;

import java.util.List;


import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;

public interface IEmpleadoService {

    public List<Empleado> getAll();

    public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);

    public void remove(long idPersona);

}