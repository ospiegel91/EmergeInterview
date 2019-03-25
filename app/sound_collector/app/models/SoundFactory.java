package models;

import java.util.Date;

public class SoundFactory {
	public Sound makeSound(NodeBean incomingSound) {
		String isContinuous = incomingSound.getIs_continuous();

		if (isContinuous.equals("False")) {
			System.out.println(" it was not continuos at the factory ");
			SporadicSound newSound = null;
			newSound = new SporadicSound();
			newSound.setType(type);
			newSound.setStartTime(startTime);
			newSound.setSenderIP(senderIP);
			return newSound;
		} else {
			Boolean isConsequtiveEvent = checkIfConsequtiveEvent(incomingSound);
			if (isConsequtiveEvent) {
				System.out.println(" it WAS continuos at the factory! ");
				ContinuousSound newSound = null;
				newSound = new ContinuousSound();
				newSound.setType(type);
				newSound.setStartTime(startTime);
				newSound.setEndTime(startTime);
				newSound.setSenderIP(senderIP);
				return newSound;
			} else {
				System.out.println(" it WAS continuos at the factory! ");
				ContinuousSound newSound = null;
				newSound = new ContinuousSound();
				newSound.setType(type);
				newSound.setStartTime(startTime);
				newSound.setEndTime(startTime);
				newSound.setSenderIP(senderIP);
				return newSound;
			}
		}
	}

	private Boolean checkIfConsequtiveEvent(NodeBean incomingSound) {
		// must edit once MongoDb is set up
		Boolean isConsequtive = false;
		return isConsequtive;
	}

}
