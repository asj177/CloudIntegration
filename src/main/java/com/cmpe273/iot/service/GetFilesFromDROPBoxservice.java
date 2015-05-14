package com.cmpe273.iot.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import com.cmpe273.iot.dto.FilesDTO;
import com.cmpe273.iot.util.GetDROPBOXConnection;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
/**
 * 
 * @author Milind
 *
 */
public class GetFilesFromDROPBoxservice {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	HashMap<String,FilesDTO> filesInDropBox=new HashMap<String,FilesDTO>();
	
	public HashMap<String,FilesDTO>getFilesFromDropBox(){
		HashMap<String,FilesDTO>listOfFiles=new HashMap<String,FilesDTO>();
		Date date = new Date();
		/*DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.US);*/
		try{
			GetDROPBOXConnection getDropBoxConnection = GetDROPBOXConnection
					.getInstance();
			
			if(GetDROPBOXConnection.client!=null && getDropBoxConnection!=null ){
			DbxClient client = getDropBoxConnection.client;
			DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
			for (DbxEntry child : listing.children)
			{
				if(child.isFolder()){
		        	printSubfolders("/"+child.name,client);
		        }
		        /*JSONObject j=new JSONObject(child.toStringMultiline());
		        System.out.println(j);*/
		        
		        if(child.isFile()){
		        	FilesDTO fileDTO=new FilesDTO();
					fileDTO.setModifiedTime(dateFormat.format(child.asFile().lastModified));
					fileDTO.setName(child.name);
					fileDTO.setPath(child.path);
					filesInDropBox.put(child.path, fileDTO);
		        }
				
			}

			}
		}catch (Exception e) {

		}
		
		return filesInDropBox;
		
	}
	
	
	public  void printSubfolders(String path,DbxClient client){
		Date date = new Date();
		DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.US);
		try {
			DbxEntry.WithChildren listing = client.getMetadataWithChildren(path);
			
			for(DbxEntry child :listing.children){
				if(child.isFolder()){
					printSubfolders(path+"/"+child.name,client);
				}
				if(child.isFile()){
					FilesDTO fileDTO=new FilesDTO();
					fileDTO.setModifiedTime(dateFormat.format(child.asFile().lastModified));
					fileDTO.setName(child.name);
					fileDTO.setPath(child.path);
					filesInDropBox.put(child.path, fileDTO);
				}
			}
			
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}

