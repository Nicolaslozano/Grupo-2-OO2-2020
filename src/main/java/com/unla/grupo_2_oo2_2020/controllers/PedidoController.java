package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.unla.grupo_2_oo2_2020.converters.ClienteConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
	@Qualifier("clienteService")
    private IClienteService clienteService;
    
    @Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;

    @GetMapping("/new/{idPersona}")
	public ModelAndView create(@PathVariable("idPersona") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);
        mAV.addObject("cliente", clienteService.findById(id));
        mAV.addObject("pedido", new PedidoModel());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.insertOrUpdate(pedidoModel);
		return new RedirectView(ViewRouteHelper.CLIENTE_INDEX);
	}

}