package com.unla.grupo_2_oo2_2020.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.models.structlike.DateAndDateModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndCantidadModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndDateModel;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;


	@Controller
	@RequestMapping("/producto")
	public class ProductoController {

		@Autowired
		@Qualifier("productoService")
		private IProductoService productoService;
		
		@Autowired
		@Qualifier("pedidoService")
		private IPedidoService pedidoService;

		@Autowired
		@Qualifier("productoConverter")
		private ProductoConverter productoConverter;

		@GetMapping("")
		public ModelAndView index() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_INDEX);
			//mAV.addObject("productos", productoService.getAll());
			return mAV;
		}

		@GetMapping("/new")
		public ModelAndView create() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_NEW);
			mAV.addObject("producto", new ProductoModel());
			return mAV;
		}

		@GetMapping("/{idProducto}")
		public ModelAndView get(@PathVariable("idProducto") long id) {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE);
			mAV.addObject("producto", productoService.findById(id));
			return mAV;
		}

		@GetMapping("/ranking")
		public ModelAndView ranking() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_RANKING);
			
			List<ProductoAndCantidadModel> result = new ArrayList<ProductoAndCantidadModel>();

	        pedidoService.rankingProductos().entrySet().stream()
	                .forEach(e -> result.add(new ProductoAndCantidadModel(e.getKey().getNombre(), e.getValue())));
				mAV.addObject("result",result);
	        return mAV;
		}

		@GetMapping("/daterequiered")
		public ModelAndView askdate() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_BETWEENDATE);
			mAV.addObject("dates_models", new DateAndDateModel());

			return mAV;
		}

		@PostMapping("/ventas-entre-fechas")
		public ModelAndView ventas(@ModelAttribute("dates_models") DateAndDateModel date) {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_VENTAS);

			List<ProductoAndDateModel> result = new ArrayList<ProductoAndDateModel>();

			pedidoService.productosEntreFechas(date.getFecha1(), date.getFecha2()).entrySet().stream()
					.forEach(e -> result.add(new ProductoAndDateModel(e.getKey().getNombre(), e.getValue())));

			mAV.addObject("result", result);
			return mAV;
		}
		

		@PostMapping("/create")
		public RedirectView create(@ModelAttribute("producto") ProductoModel productoModel) {
			productoService.insertOrUpdate(productoModel);
			return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
		}

		@PostMapping("/update")
		public RedirectView update(@ModelAttribute("producto") ProductoModel productoModel) {
			productoService.insertOrUpdate(productoModel);
			return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
		}

		@PostMapping("/remove/{idProducto}")
		public RedirectView remove(@PathVariable("idProducto") long id) {
			productoService.removeById(id);
			return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
		}
	}

