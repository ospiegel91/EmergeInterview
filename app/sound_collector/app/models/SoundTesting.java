package models;
import java.util.Scanner;

public class SoundTesting {
	
	public static void main(String[] args) {
		
		
//		SoundFactory soundFactory = new SoundFactory();
//		
//		Sound theSound = null;
//		
//		Scanner userInput = new Scanner(System.in);
//		
//		
//		String isContinuos = "False";
//		String type = "fire";
//		Integer startTime = 123;
//		Integer endTime = 456;
//		String senderIP = "ffftt55667";
//			
//		theSound = soundFactory.makeSound(isContinuos, type, startTime, endTime, senderIP);
//		
//		if( theSound != null) {
//			doStuffSound(theSound);
//		}else {
//			System.out.println("warning");
//		}
		
	}
	
	public static void doStuffSound(Sound aSound) {
		
		aSound.playSound();
	}
}
