package models;

import java.util.Date;

import com.mongodb.BasicDBObject;

public class SoundFactory {
	public void makeSound(NodeBean incomingSound) {

		String type = incomingSound.getSound();
		String senderIP = incomingSound.getSenderIP();
		int mMilisecondsInMinute = 60 * 1000;

		Date date = new Date();
		Long startTime = date.getTime();
		Long minuteAgoTime = startTime - (mMilisecondsInMinute);

		if (incomingSound.getIsContinuous().equals("False")) {
			writeSporadicSoundToDB(type, startTime, senderIP);
			return;
		}

		writeContinuousSoundToDB(type, senderIP, startTime, minuteAgoTime);
	}

	public void writeSporadicSoundToDB(String type, long startTime, String senderIP) {
		System.out.println(" it was  *sporadic at the factory ");
		SporadicSound newSound = new SporadicSound();
		newSound.setType(type);
		newSound.setStartTime(startTime);
		newSound.setSenderIP(senderIP);
		newSound.insertDocToSporadicCollection();
	}

	public void writeContinuousSoundToDB(String type, String senderIP, long startTime, long minuteAgoTime) {
		ContinuousSound newSound = new ContinuousSound();
		newSound.setType(type);
		newSound.setStartTime(startTime);
		newSound.setSenderIP(senderIP);

		BasicDBObject query = new BasicDBObject("endTime",
				new BasicDBObject("$gt", minuteAgoTime).append("$lte", startTime)).append("type", type);
		System.out.println(query);

		if (newSound.checkIfContinuousIsConsequtive(query)) {
			System.out.println("---- IS CONSEQUTIVE !!!!! ");
			BasicDBObject update = new BasicDBObject();
			update.append("$set", new BasicDBObject().append("endTime", startTime));
			newSound.updateContinuousSound(query, update);
			return;
		}

		System.out.println(" it WAS continuos at the factory! ");
		newSound.setEndTime(startTime);
		newSound.insertDocToContinuousCollection();
	}

}
