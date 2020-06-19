package com.unla.grupo_2_oo2_2020.models.structlike;

import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EmpleadoSalarioModel {

    private EmpleadoModel empleado;
    private double sueldo;
}