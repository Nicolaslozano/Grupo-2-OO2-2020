package com.unla.grupo_2_oo2_2020.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.unla.grupo_2_oo2_2020.models.structlike.DateAndDateModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndCantidadModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndDateModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
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
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

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

    @GetMapping("/getRankingProductos")
    public ResponseEntity<?> getRankingProductos() {

        List<ProductoAndCantidadModel> result = new ArrayList<ProductoAndCantidadModel>();

        pedidoService.rankingProductos().entrySet().stream()
                .forEach(e -> result.add(new ProductoAndCantidadModel(e.getKey().getNombre(), e.getValue())));

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getProductosEntreFechas")
    public ResponseEntity<?> getProductosEntreFechas(@RequestBody DateAndDateModel fechasModel) {

        List<ProductoAndDateModel> result = new ArrayList<ProductoAndDateModel>();

        pedidoService.productosEntreFechas(fechasModel.getFecha1(), fechasModel.getFecha2()).entrySet().stream()
                .forEach(e -> result.add(new ProductoAndDateModel(e.getKey().getNombre(), e.getValue())));

        return ResponseEntity.ok(result);
    }
}