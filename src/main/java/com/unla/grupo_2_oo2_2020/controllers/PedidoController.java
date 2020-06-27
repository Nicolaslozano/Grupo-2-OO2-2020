package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo_2_oo2_2020.entities.Cliente;
import com.unla.grupo_2_oo2_2020.entities.Empleado;
import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;

	@Autowired
	@Qualifier("securityService")
	private ISecurityService securityService;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@GetMapping("")
	public ModelAndView index() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
		User currentUser = userService.findByUsername(securityService.findLoggedInUsername());

		if ((userService.hasRole(currentUser.getUsername(), StaticValuesHelper.ROLE_GERENTE))
				|| (userService.hasRole(currentUser.getUsername(), StaticValuesHelper.ROLE_VENDEDOR))) {

			mAV.addObject("pedidos", pedidoService.findByLocal(((Empleado) currentUser).getLocal()));
			mAV.addObject("isHandlingAccessGranted", true);

		} else if ((userService.hasRole(currentUser.getUsername(), StaticValuesHelper.ROLE_CLIENTE))) {

			mAV.addObject("pedidos", pedidoService.findByCliente(((Cliente) currentUser)));
			mAV.addObject("isHandlingAccessGranted", false);

		} else {

			mAV.addObject("isHandlingAccessGranted", true);
			mAV.addObject("pedidos", pedidoService.getAll());
		}

		return mAV;
	}

	@GetMapping("/new/{userId}")
	public ModelAndView create(@PathVariable("userId") long id) {
		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_GERENTE)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);
		PedidoModel pedido = new PedidoModel();
		pedido.setIdCliente(id);
		mAV.addObject("pedido", pedido);

		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_GERENTE)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
			return mAV;

		}

		mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);
		PedidoModel pedido = new PedidoModel();
		pedido.setIdCliente(userService.findByUsername(securityService.findLoggedInUsername()).getId());
		mAV.addObject("pedido", pedido);

		return mAV;
	}

}