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


    @GetMapping("/new/{idPersona}")
	public ModelAndView create(@PathVariable("idPersona") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);

		PedidoModel pedido = new PedidoModel();
		pedido.setIdCliente(id);
        mAV.addObject("pedido", pedido);
		mAV.addObject("locales", localService.getAll());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("empleados", empleadoService.getAll());

		return mAV;
	}

	@PostMapping("/send")
	public ModelAndView send(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_NEW);

		if(pedidoService.validatePedido(pedidoModel)) {

			pedidoModel.setAceptado(true);
			pedidoService.insertOrUpdate(pedidoModel);
			mAV.addObject("result", "Pedido aceptado");
			mAV.addObject("total", pedidoService.getTotal(pedidoModel));
		}
		else {

			pedidoModel.setAceptado(false);
			pedidoService.insertOrUpdate(pedidoModel);
			mAV.addObject("result", "Pedido rechazado");
		}

		mAV.addObject("locales", localService.getAll());
		mAV.addObject("productos", productoService.getAll());

		return mAV;
	}

}