package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

    @GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
		mAV.addObject("clientes", clienteService.getAll());
		return mAV;
	}

	@GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.REGISTRATION);
        return mAV;
    }

    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_NEW);
		mAV.addObject("cliente", new ClienteModel());
		return mAV;
	}

	@GetMapping("/{idPersona}")
	public ModelAndView get(@PathVariable("idPersona") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_UPDATE);
		mAV.addObject("cliente", clienteService.findById(id));
		return mAV;
	}

}