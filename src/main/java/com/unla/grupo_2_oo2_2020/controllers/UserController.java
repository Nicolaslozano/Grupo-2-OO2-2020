package com.unla.grupo_2_oo2_2020.controllers;

import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.grupo_2_oo2_2020.entities.User;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;
import com.unla.grupo_2_oo2_2020.helpers.ViewRouteHelper;
import com.unla.grupo_2_oo2_2020.models.UserModel;
import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;
import com.unla.grupo_2_oo2_2020.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("securityService")
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.REGISTRATION);
        mAV.addObject("user", new User());

        return mAV;
    }

    
    public String asd(@ModelAttribute("user") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return ViewRouteHelper.REGISTRATION;
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return ViewRouteHelper.INDEX;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult) {
        userValidator.validate(userModel, bindingResult);

        if (bindingResult.hasErrors()) {

            return new ModelAndView(ViewRouteHelper.REGISTRATION);
        }

        userService.save(userModel);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return new ModelAndView(ViewRouteHelper.INDEX);
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return ViewRouteHelper.LOGIN;
    }
}