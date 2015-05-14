package com.cmpe273.iot.util;

import java.io.IOException;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * 
 * @author Deepak
 *
 */
public class GetMongoConnection {



	private static MongoClient connection;
	private static DB dbConnection;

	private GetMongoConnection() {

	}

	public static DB getConnection() throws IOException {

		if (dbConnection == null) {
			
			MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@ds061360.mongolab.com:61360/mongo");
			connection = new MongoClient(uri);
			dbConnection = connection.getDB("mongo");
		}
		return dbConnection;

	}

	public static void closeConnection() {
		dbConnection.close();
	}

}
