package com.cmpe273.iot.dto;


/**
 * 
 * @author Arjun
 *
 */
public class FilesDTO {
  public long getDropboxSize(DBxClient dbxClient, long sizeOfpendrive ) throws DbxException {
        long dbxSizeRemaining = 0;
        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo(); //The dbxClient is mentioned in the Demo.java file
        // in GB :)
        long shared = dbxAccountInfo.quota.shared / 1024 / 1024 / 1024;
        long normal = dbxAccountInfo.quota.normal / 1024 / 1024 / 1024;
        long total  = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
        dbxSizeRemaining = total - normal - shared;

        return dbxSizeRemaining;

        if(dbxSizeRemaining < sizeOfpendrive ) return 0;

        else return 1;
    }
}
