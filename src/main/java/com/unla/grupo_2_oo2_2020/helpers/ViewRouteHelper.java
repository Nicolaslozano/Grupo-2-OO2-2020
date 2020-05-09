package com.unla.grupo_2_oo2_2020.helpers;

public class ViewRouteHelper {

    /**** Views ****/
	//HOME
    public final static String INDEX = "dashboard/index";

    //CLIENTE
    public final static String CLIENTE_INDEX = "cliente/index";
    public final static String CLIENTE_NEW = "cliente/new";
    public final static String CLIENTE_UPDATE = "cliente/update";
    public final static String CLIENTE_REMOVE = "cliente/remove";

    //LOCAL
    public final static String LOCAL_INDEX = "local/index";
    public final static String LOCAL_NEW = "local/new";
    public final static String LOCAL_REMOVE = "local/remove";
    public final static String LOCAL_UPDATE = "local/update";

    //EMPLEADO
    public final static String EMPLEADO_INDEX = "empleado/index";
    public final static String EMPLEADO_NEW = "empleado/new";
    
    
	/**** Redirects ****/
    public final static String ROUTE = "/index";
    public final static String LOCAL_ROOT = "/local";
    public final static String CLIENTE_ROOT = "/cliente";
    public final static String EMPLEADO_ROOT = "/empleado";

}