package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>{

    public abstract Stock findByIdStock(long idLocal);

    @Query("SELECT s FROM Stock s JOIN FETCH s.local l "+
                                "JOIN FETCH s.lotes "+
                                "WHERE s.idStock=(:idStock)")
    public abstract Stock findByIdStock_wDependencies(@Param("idStock") long idStock);

    public abstract Stock findByLocal(Local local);

    public abstract List<Stock> findAll();
}