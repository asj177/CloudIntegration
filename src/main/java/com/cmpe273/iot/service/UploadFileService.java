package com.cmpe273.iot.service;


/**
 * 
 * @author Arjun
 *
 */
package com.cmpe273.iot.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.cmpe273.iot.DAO.UploadFiles;
import com.cmpe273.iot.dto.FilesDTO;

public class UploadFileService {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public void uploadFileService(HashMap<File,String>filesFromPd,HashMap<String,FilesDTO>filesFromDropBox){
		
		
		Iterator pdfFiles=filesFromPd.entrySet().iterator();
		HashMap<File,String>filesForDropBox=new HashMap<File,String>();
		UploadFiles uploadFiles=new UploadFiles();
		while(pdfFiles.hasNext()){
			
			Map.Entry pair=(Map.Entry) pdfFiles.next();
			
			File file=(File)pair.getKey();
			
			
			String abspath=file.getAbsolutePath().toString();
			
			String pathForDrop="";
			
			String[]pathTotrime=abspath.split("/");
			
			for(int i=4;i<pathTotrime.length;i++){
				pathForDrop=pathForDrop+"/"+pathTotrime[i];
			}
			
			if(filesFromDropBox.containsKey(pathForDrop)){
				
				FilesDTO dropDTO=filesFromDropBox.get(pathForDrop);
				if(!comparedate((String)pair.getValue(),dropDTO.getModifiedTime())){
					filesForDropBox.put(file, pathForDrop);
				}
				
				
			}else{
				filesForDropBox.put(file, pathForDrop);
			}
			
			
			
			
		}
		
		uploadFiles.uploadFilesInDropBox(filesForDropBox);
		
	}
	
	
	
	private boolean comparedate(String todaysDate,String expiredDate){
		try{
		Date today=dateFormat.parse(todaysDate);
		Date expired=dateFormat.parse(expiredDate);
		//Date expired=dateFormat.parse(expiredDate);
		if(today.compareTo(expired)>0){
			return false;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	

}
