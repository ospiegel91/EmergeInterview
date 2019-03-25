package models;

import java.util.Date;

public class SoundFactory {
	public void makeSound(NodeBean incomingSound) {
		
		String type = incomingSound.getSound();
		String senderIP = incomingSound.getSenderIP();

		Date date = new Date();
		Long startTime = date.getTime();
		
		
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
			
			newSound.checkIfContinuousIsConsequtive();
			
			if (newSound.mIsConsequtiveEvent) {
				System.out.println("---- IS CONSEQUTIVE !!!!! ");
			} else {
				System.out.println(" it WAS continuos at the factory! ");
				newSound.setEndTime(startTime);
				newSound.insertDocToContinuousCollection();

			}
		}
	}

}
