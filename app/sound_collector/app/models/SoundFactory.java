package models;

import java.util.Date;

import com.mongodb.BasicDBObject;

public class SoundFactory {
	public void makeSound(NodeBean incomingSound) {
		
		String type = incomingSound.getSound();
		String senderIP = incomingSound.getSenderIP();

		Date date = new Date();
		Long startTime = date.getTime();
		Long minuteAgoTime = startTime - (60*10000);
		
		String isContinuous = incomingSound.getIs_continuous();

		if (isContinuous.equals("False")) {
			System.out.println(" it was  *sporadic at the factory ");
			SporadicSound newSound = null;
			newSound = new SporadicSound();
			newSound.setType(type);
			newSound.setStartTime(startTime);
			newSound.setSenderIP(senderIP);
			newSound.insertDocToSporadicCollection();

		} else {
			ContinuousSound newSound = null;
			newSound = new ContinuousSound();
			newSound.setType(type);
			newSound.setStartTime(startTime);
			newSound.setSenderIP(senderIP);
			
			BasicDBObject query = null;
			query = new BasicDBObject("endTime", new BasicDBObject("$gt", minuteAgoTime).append("$lte", startTime)).append("type", type);
			System.out.println(query);
			newSound.checkIfContinuousIsConsequtive(query);
			
			if (newSound.mIsConsequtiveEvent) {
				System.out.println("---- IS CONSEQUTIVE !!!!! ");
				BasicDBObject update = new BasicDBObject();
				update.append("$set", new BasicDBObject().append("endTime", startTime));
				newSound.updateContinuousSound(query, update);
			} else {
				System.out.println(" it WAS continuos at the factory! ");
				newSound.setEndTime(startTime);
				newSound.insertDocToContinuousCollection();

			}
		}
	}

}
