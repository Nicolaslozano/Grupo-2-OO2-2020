package com.unla.grupo_2_oo2_2020.config;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataAlreadySetupVariable {

    @Id
    private long id;
    private boolean isAlreadySetup;

}