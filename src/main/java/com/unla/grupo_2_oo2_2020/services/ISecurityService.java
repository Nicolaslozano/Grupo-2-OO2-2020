package com.unla.grupo_2_oo2_2020.services;

/**
 * We create SecurityService to provide current logged-in user and auto login
 * user after registration
 */

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}