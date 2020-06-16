package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo_2_oo2_2020.converters.LoteConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.ILoteService;
import com.unla.grupo_2_oo2_2020.services.IProductoService;

@Controller
@RequestMapping("/lote")
public class LoteController {


    @Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
    
    @Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

    @GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_INDEX);
		mAV.addObject("lotes", loteService.getAll());
		mAV.addObject("locales",localService.getAll());
		return mAV;
	}

    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_NEW);
		return mAV;
	}

	@GetMapping("/{idLote}")
	public ModelAndView get(@PathVariable("idLote") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_UPDATE);
		mAV.addObject("lote", loteService.findById(id));
		mAV.addObject("locales", localService.getAll());
		//mAV.addObject("productos", productoService.getAll());
		return mAV;
	}

	@PostMapping("/remove/{idLote}")
	public RedirectView remove(@PathVariable("idLote") long id) {
		loteService.removeById(id,true);
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}
}

