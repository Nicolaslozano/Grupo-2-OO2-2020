package com.unla.grupo_2_oo2_2020.models.structlike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class LocalAndDistanceModel {

    private long idLocal;
    private String direccion;
    private double distancia;

    public LocalAndDistanceModel(String direccion, double distancia) {
        this.direccion = direccion;
        this.distancia = distancia;
    }
}