package com.unla.grupo_2_oo2_2020.services;

import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;

public interface ILocalService {

    public List<Local> getAll();

    public Local findById(long idLocal);

    public LocalModel insertOrUpdate(LocalModel localModel);

    public List<Local> getNearestValidLocal(PedidoModel pedidoModel);

    public void removeById(long idLocal);
}