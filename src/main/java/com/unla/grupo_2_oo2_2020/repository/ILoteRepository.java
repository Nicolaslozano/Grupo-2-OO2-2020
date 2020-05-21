package com.unla.grupo_2_oo2_2020.repository;

import java.io.Serializable;
import java.util.List;

import com.unla.grupo_2_oo2_2020.entities.Lote;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.entities.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {

    public abstract Lote findByIdLote(long idLote);

    public abstract List<Lote> findByStock(Stock stock);

    public abstract List<Lote> findByProducto(Producto producto);

    @Query("SELECT l FROM Lote l WHERE l.stock=(:stock) AND l.producto=(:producto)")
    public abstract List<Lote> findByProductoAndStock(@Param("producto") Producto producto,
            @Param("stock") Stock stock);

    public abstract List<Lote> findAll();
    
}