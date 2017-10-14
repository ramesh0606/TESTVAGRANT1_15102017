package com.vagrant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseProperity {
	public static FileInputStream CONFIGFILE = null;
	public static Properties CONFIGPRO = null;
	
	public static FileInputStream XPATHFILE = null;
	public static Properties XPATHPRO = null;

	public static void loadProperity() throws Exception {

		// Loading config.properity file
		CONFIGFILE = new FileInputStream(System.getProperty("user.dir") + "//config.properities");
		CONFIGPRO = new Properties();
		CONFIGPRO.load(CONFIGFILE);
		
		// Loading OR.properity file
		XPATHFILE = new FileInputStream(System.getProperty("user.dir") + "//OR.properities");
		XPATHPRO = new Properties();
		XPATHPRO.load(XPATHFILE);

	}

	
}
