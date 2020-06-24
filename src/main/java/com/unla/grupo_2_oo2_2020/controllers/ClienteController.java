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
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

	@Autowired
    @Qualifier("securityService")
	private ISecurityService securityService;
	
	@Autowired
    @Qualifier("userService")
    private IUserService userService;


    @GetMapping("")
	public ModelAndView index() {

		ModelAndView mAV;

		if(userService.hasRole(securityService.findLoggedInUsername(), "ROLE_CLIENTE")) {
			mAV = new ModelAndView(ViewRouteHelper.INDEX);
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
		mAV.addObject("clientes", clienteService.getAll());
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