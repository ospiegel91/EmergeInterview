package models;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class SporadicSound extends Sound {

	public SporadicSound() {

	}

	public void insertDocToSporadicCollection() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("sporadic");

		DBObject document = new BasicDBObject();
		document.put("type", type);
		document.put("startTime", startTime);
		document.put("senderIP", senderIP);
		mContinuousCollection.insert(document);

	}

	public Long getCountOfSporadicSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");

		mContinuousCollection = mDB.getCollection("sporadic");
		return mContinuousCollection.getCount(query);
	}

	public List<DBObject> getSporadicSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("sporadic");
		return mContinuousCollection.find(query).toArray();
	}

}
