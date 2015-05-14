package com.cmpe273.iot.scheduler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cmpe273.iot.DAO.UploadFiles;
import com.cmpe273.iot.dto.FilesDTO;
import com.cmpe273.iot.service.GetFilesFromDROPBoxservice;
import com.cmpe273.iot.service.GetFilesFromPD;
import com.cmpe273.iot.service.UploadFileService;
import com.cmpe273.iot.util.CheckPD;
import com.cmpe273.iot.util.GetDROPBOXConnection;

/**
 * 
 * @author arpit
 *
 */
@Component
public class Scheduler {
	
	@Scheduled(fixedRate=1000)
	public void synch() throws IOException{
		
		CheckPD checkPd=new CheckPD();
		if(checkPd.checkPD()){
			String path=checkPd.getPath();
			UploadFiles upload=new UploadFiles();
			GetFilesFromPD getPDFiles=new GetFilesFromPD();
			GetFilesFromDROPBoxservice getDBFiles=new GetFilesFromDROPBoxservice();
			HashMap<File,String> getFilesFromPD=getPDFiles.getFilesFromPD(path);
			UploadFileService uploadFiles=new UploadFileService();
            HashMap<String,FilesDTO>filesFromDropBox=getDBFiles.getFilesFromDropBox();
            
            if(filesFromDropBox.size()>0 && getFilesFromPD.size()>0){
			uploadFiles.uploadFileService(getFilesFromPD, filesFromDropBox);
			/*Iterator iterate=getFilesFromPD.entrySet().iterator();
			
			while(iterate.hasNext()){
				
				Map.Entry pair=(Map.Entry) iterate.next();
				
				File f=(File)pair.getKey();
				String abspath=f.getAbsolutePath().toString();
				
				String pathForDrop="";
				
				String[]pathTotrime=abspath.split("/");
				
				for(int i=4;i<pathTotrime.length;i++){
					pathForDrop=pathForDrop+"/"+pathTotrime[i];
				}
				
				System.out.println("Absolute path is "+pathForDrop+" cannonical path is "+f.getCanonicalPath());
			}
			*/
			//upload.uploadFiles(path);
			
			
			
			System.out.println("Pd Detected");
            }else{
            	System.out.println("No files in PD to upload");
            }
		}else{
		
		System.out.println("No pd Found ");
		}
	}

}


