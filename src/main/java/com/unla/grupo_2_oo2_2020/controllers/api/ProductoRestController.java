package com.unla.grupo_2_oo2_2020.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import com.unla.grupo_2_oo2_2020.models.structlike.SalesByLocalModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndCantidadModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.entities.Local;
import com.unla.grupo_2_oo2_2020.entities.Producto;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

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
    @Qualifier("localService")
    private ILocalService localService;

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

    @PostMapping("/getProductosEntreFechas/{idLocal}")
    public ResponseEntity<?> getProductosEntreFechas(@Valid @RequestBody SalesByLocalModel dateModels,
            @PathVariable("idLocal") long idLocal, Errors errors) {

        List<ProductoAndCantidadModel> result = new ArrayList<ProductoAndCantidadModel>();
        Local local = localService.findById(idLocal);
        HashMap<String, String> errorResult = new HashMap<String, String>();

        if (errors.hasErrors()) {

            for (ObjectError error : errors.getAllErrors()) {

                errorResult.put(error.getDefaultMessage(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        }

        pedidoService.productosEntreFechas(dateModels.getFecha1(), dateModels.getFecha2(), local).entrySet().stream()
                .forEach(e -> result.add(new ProductoAndCantidadModel(e.getKey().getNombre(), e.getValue())));

        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/createProducto")
    public ResponseEntity<?> createProducto(@Valid @RequestBody ProductoModel productoModel, Errors errors) {

        HashMap<String, String> result = new HashMap<String, String>();

        if (errors.hasErrors()) {

            for (ObjectError error : errors.getAllErrors()) {

                result.put(error.getDefaultMessage(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        }else {

			productoService.insertOrUpdate(productoModel);
			result.put(StaticValuesHelper.SUCCESS_CREATED, "Producto creado");
		}

        return ResponseEntity.ok(result);
    }

	@PostMapping("/updateProducto")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody ProductoModel productoModel, Errors errors) {

		Map<String, String> result = new HashMap<String, String>();

		if (errors.hasErrors()) {

			for (ObjectError error : errors.getAllErrors()) {

				result.put(error.getDefaultMessage(), error.getDefaultMessage());
			}

			return ResponseEntity.badRequest().body(result);

		} else {

			productoService.insertOrUpdate(productoModel);
			result.put(StaticValuesHelper.SUCCESS_UPDATED, "Producto actualizado");
		}

		return ResponseEntity.ok(result);
	}

}