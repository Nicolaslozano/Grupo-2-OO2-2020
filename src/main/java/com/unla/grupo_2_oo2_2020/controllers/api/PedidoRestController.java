package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.unla.grupo_2_oo2_2020.converters.PedidoConverter;
import com.unla.grupo_2_oo2_2020.entities.Pedido;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoRestController {

    @Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;

    @Autowired
    @Qualifier("pedidoConverter")
    private PedidoConverter pedidoConverter;

    @Autowired
    @Qualifier("localService")
    private ILocalService localService;

    @GetMapping("/getPedidos")
    public ResponseEntity<List<PedidoModel>> getPedidos() {

        List<PedidoModel> pedidos = new ArrayList<PedidoModel>();

        for (Pedido pedido : pedidoService.getAll()) {
            // why? porque sino no anda el JSON.parse cuando lo manda a la vista
            pedidos.add(pedidoConverter.entityToModel(pedido));
        }

        return new ResponseEntity<List<PedidoModel>>(pedidos, HttpStatus.OK);
    }

    @PostMapping("/sendPedido")
    public ResponseEntity<?> createLocal(@Valid @RequestBody PedidoModel pedidoModel, Errors errors) {

        HashMap<String, String> result = new HashMap<String, String>();

        if (errors.hasErrors()) {

            for (ObjectError error : errors.getAllErrors()) {

                result.put(error.getDefaultMessage(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(result);
        } else {

            if (pedidoService.validatePedido(pedidoModel)) {

                pedidoModel.setAceptado(true);
                pedidoService.insertOrUpdate(pedidoModel);
                result.put(StaticValuesHelper.ORDER_ACCEPTED,
                        "Pedido despachado correctamente a "
                                + localService.findById(pedidoModel.getIdLocal()).getDireccion() + "por un total de $"
                                + pedidoService.getTotal(pedidoModel));

                result.put("redirect", ViewRouteHelper.CLIENTE_ROOT);
            } else {

                pedidoModel.setAceptado(false);
                pedidoService.insertOrUpdate(pedidoModel);
                result.put(StaticValuesHelper.ORDER_REJECTED, "Pedido rechazado");
                result.put("redirect", ViewRouteHelper.CLIENTE_ROOT);
                result.put("retry", ViewRouteHelper.PEDIDO_NEW + "/" + pedidoModel.getIdCliente());
            }
        }

        return ResponseEntity.ok(result);
    }

}