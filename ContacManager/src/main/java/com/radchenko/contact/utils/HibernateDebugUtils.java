package com.radchenko.contact.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author radchenko.as
 * @version 1.0
 */
public class HibernateDebugUtils {
	
	public static void turnOnShowSQL(){
		Logger sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.DEBUG);
        Logger descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.TRACE);
	}
	
	public static void turnOffShowSQL(){
		Logger sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.INFO);
        Logger descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.INFO);
	}
}
