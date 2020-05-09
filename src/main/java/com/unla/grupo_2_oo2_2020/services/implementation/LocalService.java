package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.repository.ILocalRepository;
import com.unla.grupo_2_oo2_2020.repository.IStockRepository;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("localService")
public class LocalService implements ILocalService {

    @Autowired
    @Qualifier("localRepository")
    private ILocalRepository localRepository;

    @Autowired
    @Qualifier("stockRepository")
    private IStockRepository stockRepository;

    @Autowired
    @Qualifier("localConverter")
    private LocalConverter localConverter;

    @Override
    public List<Local> getAll() {
        // TODO Auto-generated method stub
        return localRepository.findAll();
    }

    @Override
    public Local findById(long idLocal) {

        return localRepository.findByIdLocal(idLocal);
    }

    @Override
    public LocalModel insertOrUpdate(LocalModel localModel) {

        Local local;

        if(localModel.getIdLocal() > 0) {

            local = localRepository.getOne(localModel.getIdLocal());
            local.setDireccion(localModel.getDireccion());
            local.setLatitud(localModel.getLatitud());
            local.setLongitud(localModel.getLongitud());
            local.setTelefono(localModel.getTelefono());

        }else {

            local = localConverter.modelToEntity(localModel);
            Stock stock = new Stock(0, local);
            local.setStock(stock);
        }

        localRepository.save(local);
        return localConverter.entityToModel(local);
    }

    @Override
    public void remove(long idLocal) {
        // TODO Auto-generated method stub

    }
    
}