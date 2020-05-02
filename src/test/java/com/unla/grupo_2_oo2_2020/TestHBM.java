package com.unla.grupo_2_oo2_2020;

import org.hibernate.Session;

import com.unla.grupo_2_oo2_2020.dao.HibernateUtil;

public class TestHBM {

	public static void main(String[] args) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    session.close();
	    System.out.println("OK");
	}
}
