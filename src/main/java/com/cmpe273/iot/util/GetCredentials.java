package com.cmpe273.iot.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Milind
 *
 */
public class GetCredentials {

	public static GetCredentials getCredentials;
	public static String APP_KEY;
	public static String APP_SECRET;
	public static String ACCESS_TOKEN;
    
	private GetCredentials(){
		
		
	}
	
	
	public static GetCredentials getInstance() {
		getCredentials=new GetCredentials();
		return getCredentials;
		
	}
	
	public   void setCredentials() throws IOException{
         Properties properties=new Properties();
		
		String propertyFileName="application.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
		}
		
		APP_KEY=properties.getProperty("APP_KEY");
		APP_SECRET=properties.getProperty("APP_SECRET");
		ACCESS_TOKEN=properties.getProperty("ACCESS_TOKEN");
		
	}
}

