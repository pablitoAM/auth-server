/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 7 Mar 2016 11:37:05 Author: Pablo
 */

package com.pabloam.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Pablo
 *
 */
@Configuration
public class DatabaseConfiguration {

	// ===============================
	// Properties
	// ===============================

	@Value("mongodb.database: oauth2_test")
	private String database;

	@Value("mongodb.uri: mongodb://username:pwd@localhost/?authMechanism=SCRAM-SHA-1")
	private String uri;

	// ===============================
	// Beans
	// ===============================

	@Bean
	MongoClient getMongoClient() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
		return mongoClient;
	}

	@Bean
	DB getMongoBD(MongoClient mongoClient) {
		return mongoClient.getDB(database);
	}

}
