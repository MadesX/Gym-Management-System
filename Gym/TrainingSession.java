package Gym;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class TrainingSession 
{
    private String sessionID;
    private String name;
    private GregorianCalendar dateTime;
    private int duration;
    private String location;
    
    public TrainingSession(String sessionID, String name, GregorianCalendar date, int duration, String location) {
        setSessionID(sessionID);
        setName(name);
        setDateTime(date);
        setDuration(duration);
        setLocation(location);
    }

    public TrainingSession() {
        this.sessionID = "";
        this.name = "";
        this.dateTime = new GregorianCalendar();
        this.duration = 0;
        this.location = "";
    }
    
    /**constructor with random dates and times for the database building*/
    public TrainingSession(String sessionID, String name, int duration, String location) {
        setSessionID(sessionID);
        setName(name);
        setDuration(duration);
        setLocation(location);
        Random random = new Random();
        do {
        	dateTime = new GregorianCalendar();
            int randomDays = random.nextInt(15);			// 0 - 14
            int randomHours = 6 + random.nextInt(15);		// 6 - 20
            int randomMinutesOption = random.nextInt(4);	// 0 - 3
            int randomMinutes = 15 * randomMinutesOption;
            dateTime.add(GregorianCalendar.DAY_OF_MONTH, randomDays);
            dateTime.set(GregorianCalendar.HOUR_OF_DAY, randomHours);
            dateTime.set(GregorianCalendar.MINUTE, randomMinutes);
		} while (checkRNDDate());
        setDateTime(dateTime);
    }
    
    private boolean checkRNDDate() {
    	if (dateTime.before(new GregorianCalendar()))
    		return true;
    	for (TrainingSession session : DataBase.getSessions()) 
    	{
    		if (session != null && session.getDateTime().compareTo(dateTime) == 0)
    			return true;
    		if (session.getDateTime().get(Calendar.YEAR) == dateTime.get(Calendar.YEAR) && session.getDateTime().get(Calendar.MONTH) == dateTime.get(Calendar.MONTH) && session.getDateTime().get(Calendar.DAY_OF_MONTH) == dateTime.get(Calendar.DAY_OF_MONTH))
    			if (Math.abs(session.getDateTime().getTimeInMillis() - dateTime.getTimeInMillis()) < 3600 * 1000)
    				return true;
    	}
    	return false;
    }
    
    public String getSessionID() { 
    	return sessionID; 
    }
    public void setSessionID(String sessionID) { 
    	Tests.checkID(sessionID);
    	this.sessionID = sessionID; 
    }

    public String getName() { 
    	return name; 
    }
    public void setName(String name) { 
    	Tests.checkNotEmpty(name);
    	this.name = name; 
    }

    public GregorianCalendar getDateTime() { 
    	return dateTime; 
    }
    public void setDateTime(GregorianCalendar dateTime) {
    	Tests.checkDate(dateTime);
    	this.dateTime = dateTime; 
    }

    public int getDuration() { 
    	return duration; 
    }
    public void setDuration(int duration) { 
    	Tests.checkInt(duration);
    	this.duration = duration; 
    }

    public String getLocation() { 
    	return location; 
    }
    public void setLocation(String location) { 
    	Tests.checkNotEmpty(location);  	
    	try {
            this.location = Constants.Locations.valueOf(location.toUpperCase()).name();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
    }

    @Override
    public String toString() {
    	String startTime = String.format("startTime=%02d:%02d", dateTime.get(GregorianCalendar.HOUR_OF_DAY), dateTime.get(GregorianCalendar.MINUTE));
        return "TrainingSession{" +
        		"sessionID=" + sessionID +
                ", name=" + name +
                ", date=" + dateTime.get(GregorianCalendar.DAY_OF_MONTH) + "," + dateTime.get(GregorianCalendar.MONTH) + "," + dateTime.get(GregorianCalendar.YEAR) + 
                ", " + startTime +
                ", duration=" + duration +
                ", location=" + location +
                '}';  
    }
}