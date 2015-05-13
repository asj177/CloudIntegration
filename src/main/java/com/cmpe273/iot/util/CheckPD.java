package com.cmpe273.iot.util;

import java.io.File;

/**
 * Get the list of files from the pd and checks the pd
 * and return true if the pd is attached
 * @author Mangesh
 *
 */
public class CheckPD {
	
	
	public boolean checkPD(){
		
		boolean isUSB=false;
		File file = new File("/media/arpit");
		String[] directories =file.list();
		
		
		
		if(directories.length>0){
			isUSB=true;
		}
		
		return isUSB;
	}
	
	public String getPath(){
		File file = new File("/media/arpit");
		String[] directories =file.list();
		
		String path="/media/arpit/"+directories[0];
		return path;
		
	}
	
	

}
