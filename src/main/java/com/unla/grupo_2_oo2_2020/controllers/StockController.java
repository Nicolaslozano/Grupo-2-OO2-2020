package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IStockService;

@Controller
@RequestMapping("/stock")
public class StockController {
	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("securityService")
	private ISecurityService securityService;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		mAV.addObject("lotes", loteService.getAll());
		mAV.addObject("locales",localService.getAll());
		return mAV;
	}

	@GetMapping("/{idLocal}")
	public ModelAndView get(@PathVariable("idLocal") long id) {
		ModelAndView mAV;

			if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
				return mAV;

			} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
				return mAV;
			}

		mAV = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		mAV.addObject("lotes",loteService.findByStock(stockService.findById(id)));
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}

}
