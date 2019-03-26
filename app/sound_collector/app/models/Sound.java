package models;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public abstract class Sound {

	public String type;
	public Long startTime;
	public String senderIP;
	public MongoClient mMongoClient;
	public DB mDB;
	public DBCollection mContinuousCollection;

	public String getType() {
		return type;
	}

	public void setType(String newType) {
		type = newType;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long newTime) {
		startTime = newTime;
	}

	public String getSenderIP() {
		return senderIP;
	}

	public void setSenderIP(String newIP) {
		senderIP = newIP;
	}
}
