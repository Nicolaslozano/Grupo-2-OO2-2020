package com.unla.grupo_2_oo2_2020.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService; 
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.REPORTE_INDEX);
		mAV.addObject("pedidos", pedidoService.ProductosEntreFechas(LocalDate.of(2020,2 ,13),LocalDate.of(2020,2 ,13)));
		mAV.addObject("pedidos", pedidoService.RankingProductos());
		return mAV; 
	}	
}
