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

import com.unla.grupo_2_oo2_2020.converters.LoteConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.models.LoteModel;
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
		return mAV;
	}


    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_NEW);
		mAV.addObject("lote", new LoteModel());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("lote") LoteModel loteModel) {
		loteService.insertOrUpdate(loteModel);
		return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
	}

	@GetMapping("/{idLote}")
	public ModelAndView get(@PathVariable("idLote") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_UPDATE);
		mAV.addObject("lote", loteConverter.entityToModel(loteService.findById(id)));
		return mAV;
	}

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("lote") LoteModel loteModel) {
		loteService.insertOrUpdate(loteModel);
		return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
	}
	
	@PostMapping("/remove/{idLote}")
	public RedirectView remove(@PathVariable("idLote") long id) {
		loteService.removeById(id);
		return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
	}
}

