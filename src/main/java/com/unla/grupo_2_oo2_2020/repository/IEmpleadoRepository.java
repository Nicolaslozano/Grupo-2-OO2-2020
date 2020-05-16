package com.unla.grupo_2_oo2_2020.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.Local;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository("empleadoRepository")
public interface IEmpleadoRepository extends IPersonaBaseRepository<Empleado>{

    public abstract List<Empleado> findByLocal(Local local);

    @Query("SELECT e FROM Empleado e JOIN FETCH e.local l where e.idPersona=(:idPersona)")
    public abstract Empleado findByIdPersona_wDependencies(@Param("idPersona") long idPersona);

}