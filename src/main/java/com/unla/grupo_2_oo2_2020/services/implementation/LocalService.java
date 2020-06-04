package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.ArrayList;
import java.util.List;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
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

        Local local = localConverter.modelToEntity(localModel);

        if (findById(localModel.getIdLocal()) != null) {

            local.setStock(stockRepository.findByIdStock(localModel.getIdLocal()));

        } else {

            Stock stock = new Stock(0, local);
            local.setStock(stock);
        }

        localRepository.save(local);
        return localConverter.entityToModel(local);
    }

    @Override
    public void removeById(long idLocal) {
        localRepository.deleteById(idLocal);

    }
    //FALTA LA VISTA PARA PODER TESTEAR Y TENGO QUE HACER MAS VERIFICACIONES
    @Override
    public List<Local> getNearestValidLocal(PedidoModel pedidoModel) {

        List<Local> nearestLocal = new ArrayList<Local>();
        Local pedidoLocal = findById(pedidoModel.getIdLocal());
        
        for (Local local : getAll()) {

            if (local.getIdLocal() == pedidoModel.getIdLocal())
                continue;

             else if(pedidoLocal.calculateDistance(pedidoLocal) < pedidoLocal.calculateDistance(local)) {
            	 nearestLocal.add(local);
               
            }

    }
		return nearestLocal;
  }     
}