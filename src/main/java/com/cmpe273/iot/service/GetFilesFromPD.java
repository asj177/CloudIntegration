package com.cmpe273.iot.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * 
 * @author Kandarp
 *
 */
public class GetFilesFromPD {

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	HashMap<File,String>filesFromPd=new HashMap<File,String>();
	
	public HashMap<File,String>getFilesFromPD(String path){
		
		Date date = new Date();
		DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.US);
		
		
		
		File files=new File(path);
		File[]allFiles=files.listFiles();
		
		for(File file:allFiles){
			
			System.out.println(dateFormat.format(file.lastModified()));
			if(!file.isHidden() && file.canRead()){
				if(file.isDirectory()){
					addSubFolder(file);
				}else{
					if(file.isFile()){
						if(!file.getName().endsWith("~")){
			filesFromPd.put(file, dateFormat.format(file.lastModified()));
						}
					}
				}
			
			}
			
		}
		return filesFromPd;
		
	}
	
	public void addSubFolder(File file){
		
		if(file.isDirectory()){
			File[]files=file.listFiles();
			
			for(File f:files){
				
				if(f.isFile() && !file.isHidden() && file.canRead()){
					if(!f.getName().endsWith("~")){
					filesFromPd.put(f, dateFormat.format(f.lastModified()));
					}
				}else{
					addSubFolder(f);
				}
				
			}
			
		}
		
	}

}
