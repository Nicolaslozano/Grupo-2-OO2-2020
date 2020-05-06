package com.unla.grupo_2_oo2_2020.controllers;

import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.LocalModel;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/local")
public class LocalController {

    @Autowired
	@Qualifier("localService")
	private ILocalService localService;

    @GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_INDEX);

		return mAV;
	}

    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOCAL_NEW);
		mAV.addObject("local", new LocalModel());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("local") LocalModel localModel) {
		localService.insertOrUpdate(localModel);
		return new RedirectView(ViewRouteHelper.LOCAL_ROOT);
    }
    
}