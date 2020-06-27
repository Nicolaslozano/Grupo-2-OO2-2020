package com.unla.grupo_2_oo2_2020.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Local;

import org.springframework.stereotype.Repository;

@Transactional
@Repository("empleadoRepository")
public interface IEmpleadoRepository extends IUserRepository<Empleado>{

    public abstract List<Empleado> findByLocal(Local local);

}