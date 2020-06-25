package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
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

		if (userService.hasRole(securityService.findLoggedInUsername(), StaticValuesHelper.ROLE_CLIENTE)) {
			mAV = new ModelAndView("error/403");
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
		mAV.addObject("clientes", clienteService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {

		ModelAndView mAV;

		if (userService.hasRole(securityService.findLoggedInUsername(), StaticValuesHelper.ROLE_CLIENTE)) {
			mAV = new ModelAndView(ViewRouteHelper.ERROR_FORBIDDEN);
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.CLIENTE_NEW);
		mAV.addObject("cliente", new ClienteModel());
		return mAV;
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") long id) {

		ModelAndView mAV;
		Cliente cliente = clienteService.findById(id);

		if ((userService.hasRole(securityService.findLoggedInUsername(), StaticValuesHelper.ROLE_CLIENTE))
				&& (userService.findByUsername(securityService.findLoggedInUsername()).getId() != id)) {
			mAV = new ModelAndView(ViewRouteHelper.ERROR_FORBIDDEN);
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.CLIENTE_UPDATE);
		mAV.addObject("cliente", cliente);
		return mAV;
	}

}