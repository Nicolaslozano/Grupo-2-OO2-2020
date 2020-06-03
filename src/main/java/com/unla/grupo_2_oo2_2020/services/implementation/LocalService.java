package com.unla.grupo_2_oo2_2020.services.implementation;

import java.util.List;

import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Stock;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.repository.ILocalRepository;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IStockService;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("localService")
public class LocalService implements ILocalService {

    @Autowired
    @Qualifier("localRepository")
    private ILocalRepository localRepository;

    @Autowired
    @Qualifier("stockService")
    private IStockService stockService;

    @Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;

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

            local.setStock(stockService.findById(localModel.getIdLocal()));

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

    @Override
    public Local getNearestValidLocal(PedidoModel pedidoModel) {

        Local nearestLocal = new Local();
        Local pedidoLocal = findById(pedidoModel.getIdLocal());
        boolean firstIteration = true;

        for (Local local : getAll()) {

            if (local.getIdLocal() == pedidoModel.getIdLocal())
                continue;

            else if (firstIteration) {

                nearestLocal = local;
                firstIteration = false;
            } else {

                if (pedidoLocal.calculateDistance(local) < pedidoLocal.calculateDistance(nearestLocal)) {
                    //JUST IF THAT LOCAL HAS ENOUGH STOCK TO SATISFY PEDIDO implement
                    nearestLocal = local;
                }
            }
        }

        return nearestLocal;
    }
}