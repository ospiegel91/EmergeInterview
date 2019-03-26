package models;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class ContinuousSound extends Sound {

	private Long mEndTime;

	public void insertDocToContinuousCollection() {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");

		DBObject document = new BasicDBObject();
		document.put("type", type);
		document.put("startTime", startTime);
		document.put("endTime", mEndTime);
		document.put("senderIP", senderIP);
		mContinuousCollection.insert(document);

	}

	public Long getCountOfContinuousSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");
		return mContinuousCollection.getCount(query);
	}

	public List<DBObject> getContinuousSounds(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");
		return mContinuousCollection.find(query).toArray();
	}

	public Long getEndTime() {
		return mEndTime;
	}

	public void setEndTime(Long newTime) {
		mEndTime = newTime;
	}

	public boolean checkIfContinuousIsConsequtive(BasicDBObject query) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");
		mContinuousCollection = mDB.getCollection("continuous");

		if (mContinuousCollection.getCount(query) > 0) {
			return true;
		}

		return false;
	}

	public void updateContinuousSound(BasicDBObject query, BasicDBObject update) {
		mMongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		mDB = mMongoClient.getDB("sounds");

		mContinuousCollection = mDB.getCollection("continuous");
		mContinuousCollection.update(query, update);
	}

}
