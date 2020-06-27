package com.unla.grupo_2_oo2_2020.services;

import org.springframework.web.servlet.ModelAndView;

/**
 * We create SecurityService to provide current logged-in user and auto login
 * user after registration
 */

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

    ModelAndView redirectAccessForbidden(String role);
}