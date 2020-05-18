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

import com.unla.grupo_2_oo2_2020.converters.StockConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.StockModel;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
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
	@Qualifier("stockConverter")
	private StockConverter stockConverter;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_INDEX);
		mAV.addObject("stocks", stockService.getAll());
		return mAV;
	}

	@GetMapping("/{idLocal}")
	public ModelAndView get(@PathVariable("idLocal") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK_INDEX);
		mAV.addObject("lotes",loteService.findByStock(stockService.findById(id)));
		return mAV;
	}

}
