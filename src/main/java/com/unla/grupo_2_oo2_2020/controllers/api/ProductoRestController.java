package com.unla.grupo_2_oo2_2020.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.services.IProductoService;

import java.util.ArrayList;
import java.util.List;

import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.entities.Producto;

@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {
    
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    @GetMapping("/getProductos")
    public ResponseEntity<List<ProductoModel>> getProductos() {

        List<ProductoModel> productos = new ArrayList<ProductoModel>();

        for (Producto producto : productoService.getAll()) {
            // why? porque sino no anda el JSON.parse cuando lo manda a la vista
            productos.add(productoConverter.entityToModel(producto));
        }

        return new ResponseEntity<List<ProductoModel>>(productos, HttpStatus.OK);
    }
}