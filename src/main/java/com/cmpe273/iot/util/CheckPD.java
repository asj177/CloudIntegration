package com.cmpe273.iot.util;

import java.io.File;

/**
 * 
 * @author mangesh yadav
 *
 */
public class CheckPD {
	
	
	public boolean checkPD(){
		
		boolean isUSB=false;
		File file = new File("/media/");
		String[] directories =file.list();
		
		
		
		if(directories.length>0){
			
			isUSB=true;
		}
		
		return isUSB;
	}
	
	public String getPath(){
		File file = new File("/media/");
		String[] directories =file.list();
		
		String path="/media/"+directories[0];
		return path;
		
	}

}

