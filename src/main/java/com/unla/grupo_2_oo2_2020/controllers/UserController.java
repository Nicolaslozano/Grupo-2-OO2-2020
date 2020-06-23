package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.ClienteModel;
import com.unla.grupo_2_oo2_2020.models.UserModel;
import com.unla.grupo_2_oo2_2020.services.IClienteService;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;
//import com.unla.grupo_2_oo2_2020.validator.UserValidator;
import com.unla.grupo_2_oo2_2020.validator.ClienteValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

    @Autowired
    @Qualifier("securityService")
    private ISecurityService securityService;

   @Autowired
   @Qualifier("clienteValidator")
   private ClienteValidator clienteValidator;

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.REGISTRATION);
        mAV.addObject("user", new UserModel());

        return mAV;
    }

    @PostMapping("/registration") //SOLO CLIENTES
    public ModelAndView registration(@ModelAttribute("clienteModel") ClienteModel clienteModel, BindingResult bindingResult) {
        clienteValidator.validate(clienteModel, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView mAV = new ModelAndView(ViewRouteHelper.REGISTRATION);
            mAV.addObject("errors", bindingResult.getAllErrors());
            return mAV;
        }

        clienteService.insertOrUpdate(clienteModel);

        securityService.autoLogin(clienteModel.getUsername(), clienteModel.getPasswordConfirm());

        return new ModelAndView(ViewRouteHelper.INDEX);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOGIN);

        return mAV;
    }

    @PostMapping("/loginValidation")
    public ModelAndView logged(@ModelAttribute String username, @ModelAttribute String password) {

        securityService.autoLogin(username, password);

        return new ModelAndView(ViewRouteHelper.INDEX);
    }

    @GetMapping("/requestLogout")
    public ModelAndView logout() {
        return new ModelAndView(ViewRouteHelper.LOGIN);
    }
}
