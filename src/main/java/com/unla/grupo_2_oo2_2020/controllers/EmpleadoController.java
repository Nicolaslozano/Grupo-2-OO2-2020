package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo_2_oo2_2020.converters.EmpleadoConverter;
import com.unla.grupo_2_oo2_2020.converters.LocalConverter;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.EmpleadoModel;
import com.unla.grupo_2_oo2_2020.services.IEmpleadoService;
import com.unla.grupo_2_oo2_2020.services.ILocalService;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;

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

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	@Autowired
	@Qualifier("securityService")
	private ISecurityService securityService;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@GetMapping("")
	public ModelAndView index() {

		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_INDEX);
		mAV.addObject("empleados", empleadoService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {

		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_GERENTE)) != null) {
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_NEW);
		mAV.addObject("empleado", new EmpleadoModel());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}

	@GetMapping("/sueldos")
	public ModelAndView sueldos() {

		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_GERENTE)) != null) {
			return mAV;
		}

		mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_SUELDO);
		return mAV;
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") long id) {
		ModelAndView mAV;

		if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_CLIENTE)) != null) {
			return mAV;

		} else if ((mAV = securityService.redirectAccessForbidden(StaticValuesHelper.ROLE_VENDEDOR)) != null) {
			return mAV;
		}
		mAV = new ModelAndView(ViewRouteHelper.EMPLEADO_UPDATE);
		EmpleadoModel empleado = empleadoConverter.entityToModel(empleadoService.findById(id));
		mAV.addObject("empleado", empleado);
		mAV.addObject("local", localConverter.entityToModel(localService.findById(empleado.getIdLocal())));
		return mAV;
	}

}