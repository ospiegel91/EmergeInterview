package models;

public abstract class Sound {
	
	public String type;
	public Long startTime;
	public String senderIP;
	
	
	
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
	
	public void playSound() {
		
		System.out.println("Type:" + getType()+" is playing at"+getStartTime());
	}
	
}
