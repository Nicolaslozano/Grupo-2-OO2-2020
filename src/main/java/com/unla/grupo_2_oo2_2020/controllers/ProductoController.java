package com.unla.grupo_2_oo2_2020.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo_2_oo2_2020.converters.ProductoConverter;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.ProductoModel;
import com.unla.grupo_2_oo2_2020.models.structlike.ProductoAndCantidadModel;
import com.unla.grupo_2_oo2_2020.services.IPedidoService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;


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

		@Autowired
		@Qualifier("securityService")
		private ISecurityService securityService;

		@GetMapping("")
		public ModelAndView index() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_INDEX);
			return mAV;
		}

		@GetMapping("/new")
		public ModelAndView create() {
			ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
				return mAV;

			} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

			mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_NEW);
			mAV.addObject("producto", new ProductoModel());
			return mAV;
		}

		@GetMapping("/{idProducto}")
		public ModelAndView get(@PathVariable("idProducto") long id) {
			ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
				return mAV;

			} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

			mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE);
			mAV.addObject("producto", productoService.findById(id));
			return mAV;
		}

		@GetMapping("/ranking")
		public ModelAndView ranking() {
			ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

			mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_RANKING);
			List<ProductoAndCantidadModel> result = new ArrayList<ProductoAndCantidadModel>();

	        pedidoService.rankingProductos().entrySet().stream()
	                .forEach(e -> result.add(new ProductoAndCantidadModel(e.getKey().getNombre(), e.getValue())));
				mAV.addObject("result",result);
	        return mAV;
		}

		@GetMapping("/ventas-entre-fechas")
		public ModelAndView ventaFechas() {
			ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

			mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_VENTAS);
			return mAV;
		}

		@GetMapping("/remove/{idProducto}")
		public ModelAndView remove(@PathVariable long id) {
			ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
				return mAV;

			} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

			mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_ROOT);
			productoService.removeById(id);
			return mAV;
		}

}