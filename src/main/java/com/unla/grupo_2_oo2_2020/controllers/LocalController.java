package com.unla.grupo_2_oo2_2020.controllers;

import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.structlike.LocalFormModel;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/local")
public class LocalController {

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_INDEX);
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_NEW);
		mAV.addObject("local", new LocalModel());
		return mAV;
	}

	@GetMapping("/{idLocal}")
	public ModelAndView get(@PathVariable("idLocal") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_UPDATE);
		mAV.addObject("local", localService.findById(id));
		return mAV;
	}

	@GetMapping("/distance")
	public ModelAndView distance() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_CALCULAR_DISTANCIA);
		mAV.addObject("local_models", new LocalFormModel());

		return mAV;
	}

}