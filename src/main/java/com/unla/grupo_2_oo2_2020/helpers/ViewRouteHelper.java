package com.unla.grupo_2_oo2_2020.helpers;

public class ViewRouteHelper {

    /**** Views ****/
	//HOME
    public final static String INDEX = "dashboard/index";

    //CLIENTE
    public final static String CLIENTE_INDEX = "cliente/index";
    public final static String CLIENTE_NEW = "cliente/new";
    public final static String CLIENTE_UPDATE = "cliente/update";

    //LOCAL
    public final static String LOCAL_INDEX = "local/index";
    public final static String LOCAL_NEW = "local/new";
    public final static String LOCAL_UPDATE = "local/update";
    public final static String LOCAL_CALCULAR_DISTANCIA = "local/distance";
    

    //STOCK
    public final static String STOCK_INDEX = "stock/index";
    //LOTE
    public final static String LOTE_INDEX = "lote/index";
    public final static String LOTE_NEW = "lote/new";
    public final static String LOTE_UPDATE = "lote/update";
    
    //PEDIDO
    public final static String PEDIDO_INDEX = "pedido/index";
    public final static String PEDIDO_NEW = "pedido/new";

    //EMPLEADO
    public final static String EMPLEADO_INDEX = "empleado/index";
    public final static String EMPLEADO_NEW = "empleado/new";
    public final static String EMPLEADO_UPDATE = "empleado/update";

    //PRODUCTOS
    public final static String PRODUCTO_INDEX = "producto/index";
    public final static String PRODUCTO_NEW = "producto/new";
    public final static String PRODUCTO_UPDATE = "producto/update";
    public final static String PRODUCTO_RANKING = "producto/ranking";

	/**** Redirects ****/
    public final static String ROUTE = "/index";
    public final static String LOCAL_ROOT = "/local";
    public final static String CLIENTE_ROOT = "/cliente";
    public final static String EMPLEADO_ROOT = "/empleado";
    public final static String PRODUCTO_ROOT = "/producto";
    public final static String LOTE_ROOT = "/lote";
    public final static String STOCK_ROOT = "/stock";

}