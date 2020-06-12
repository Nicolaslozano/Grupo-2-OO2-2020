package com.unla.grupo_2_oo2_2020.models.structlike;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DateAndDateModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha2;
}