package com.unla.grupo_2_oo2_2020.models.structlike;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SalesByLocalModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate fecha1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate fecha2;
}