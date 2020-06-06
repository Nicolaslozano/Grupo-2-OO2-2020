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
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.PedidoModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;


@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("empleadoService")
    private IEmpleadoService empleadoService;
    
    @Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;


	
	@GetMapping("")
		public ModelAndView index() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_INDEX);
			mAV.addObject("pedidos", pedidoService.getAll());
			
			return mAV;
		}

    @GetMapping("/new/{idPersona}")
	public ModelAndView create(@PathVariable("idPersona") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);

		PedidoModel pedido = new PedidoModel();
		pedido.setIdCliente(id);
        mAV.addObject("pedido", pedido);

		return mAV;
	}

}