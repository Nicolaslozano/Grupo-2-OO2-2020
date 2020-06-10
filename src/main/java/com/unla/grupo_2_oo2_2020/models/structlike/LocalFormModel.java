package com.unla.grupo_2_oo2_2020.models.structlike;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalFormModel {

    private long idLocal_1;
    private long idLocal_2;

    public LocalFormModel(long idLocal_1,long idLocal_2) {

        this.idLocal_1 = idLocal_1;
        this.idLocal_2 = idLocal_2;
    }

}