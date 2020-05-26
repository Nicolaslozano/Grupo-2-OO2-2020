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
import com.unla.grupo_2_oo2_2020.converters.EmpleadoConverter;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

    @GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
		mAV.addObject("empleados", empleadoService.getAll());
		return mAV;
	}

    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_NEW);
		mAV.addObject("empleado", new EmpleadoModel());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}

	@GetMapping("/{idPersona}")
	public ModelAndView get(@PathVariable("idPersona") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_UPDATE);
		mAV.addObject("empleado", empleadoConverter.entityToModel(empleadoService.findById(id)));
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/remove/{idPersona}")
	public RedirectView remove(@PathVariable("idPersona") long id) {
		empleadoService.removeById(id);
		return new RedirectView(ViewRouteHelper.EMPLEADO_ROOT);
	}
}