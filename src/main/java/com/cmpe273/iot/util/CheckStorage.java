package com.cmpe273.iot.util;


/**
 * 
 * @author Arjun
 *
 */
public class CheckStorage {
  public long getDropboxSize() throws DbxException {
        long dbxSizeRemaining = 0;
        long sizeOfpendrive = 32; // value to be fetched from the code by Mangesh.
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

    public Date getTimeStampInfo(){
        Date dateOfFileFromDrive = new Date(); //need to get the time of file from Mangesh
        
    }

}
