/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sdk.common.util;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;



/**
 *
 * @author Raveendra
 */
public class HibernateUtil {

    static  Logger logger = Logger.getLogger("HibernateUtil");
    private static SessionFactory sessionFactory = null;
    private static Object lock = new Object();
    private static SessionFactory getSessionFactory() {
    if(sessionFactory == null) {
	      synchronized(lock){
    if(sessionFactory == null)
                  {
                    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
                    }
                    }
		}
	return sessionFactory;
	}

/**
* Gets the current Session.
 * @return Session Object
 */
   public static Session getSession() {

                Session session = getSessionFactory().openSession();
                 try {
			if (session != null)
                        {
				return session;
			} else {
				throw new HibernateException("Session not found.");
			}
		} catch(Exception hbx) {

                   // logger.error("getSession() - Problem with session factory: check if hbm files are having correct mapping and are exists at its appropriate location.", hbx );

		}
                System.out.println("hibernate session is making");
              return session;
	}



}
