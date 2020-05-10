package com.ktech;

import java.util.Objects;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MongoConnection {
	private static Datastore datastore;
	private static MongoClient mongoClient;

	public static synchronized void init() {
		Morphia morphia = new Morphia();
		// tell Morphia where to find your classes
		morphia.mapPackage("com.ktech.b2w.models");
		// client
		MongoClientOptions options = MongoClientOptions.builder() 
	              .connectionsPerHost(100)
	              .maxConnectionIdleTime(60000)
	              .build();
		String host = "localhost:27017";
		mongoClient = new MongoClient(host, options);
		// create the Datastore connecting to the default port on the local host
		datastore = morphia.createDatastore(mongoClient, "tatooineAleal");
		datastore.ensureIndexes();
	}

	public static synchronized Datastore getDatastore() {
		if (Objects.isNull(datastore)) {
			MongoConnection.init();
		}
		return datastore;
	}

	public static synchronized void close() {
		if (!Objects.isNull(mongoClient)) {
			mongoClient.close();
		}
	}
}
