package com.unla.grupo_2_oo2_2020.models;


public class LocalFormModel {

    private long idLocal_1;
    private long idLocal_2;

    public LocalFormModel(long idLocal_1,long idLocal_2) {

        this.idLocal_1 = idLocal_1;
        this.idLocal_2 = idLocal_2;
    }

    public LocalFormModel() {

    }

    public long getidLocal_1() {
        return idLocal_1;
    }

    public void setidLocal_1(long idLocal_1) {
        this.idLocal_1 = idLocal_1;
    }

    public long getidLocal_2() {
        return idLocal_2;
    }

    public void setidLocal_2(long idLocal_2) {
        this.idLocal_2 = idLocal_2;
    }
}