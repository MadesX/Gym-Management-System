package Gym;

import java.util.GregorianCalendar;
import java.util.Vector;

public class GroupTrainingSession extends TrainingSession 
{
    private int maxParticipants;
    private Vector<Member> members = new Vector<Member>();
    private Vector<Trainer> trainers;

    public GroupTrainingSession(String sessionID, String name, GregorianCalendar dateTime, int duration, String location, int maxParticipants, Vector<Trainer> trainers) {
        super(sessionID, name, dateTime, duration, location);
        setMaxParticipants(maxParticipants);
        setTrainers(trainers);
    }
    
    public GroupTrainingSession() {
        super();
        this.maxParticipants = 0;
        this.trainers = new Vector<Trainer>();
    }
    
    public GroupTrainingSession(String sessionID, String name, int duration, String location, int maxParticipants, Vector<Trainer> trainers) {
        super(sessionID, name, duration, location);
        setMaxParticipants(maxParticipants);
        setTrainers(trainers);
    }
    
    public int getMaxParticipants() { 
    	return maxParticipants; 
    }
    public void setMaxParticipants(int maxParticipants) { 
    	Tests.checkInt(maxParticipants);
    	this.maxParticipants = maxParticipants; 
    }
    
    public Vector<Member> getMembers() {
        return members;
    }
    public void setMembers(Vector<Member> members) {
        this.members = members;
    }
    
    public Vector<Trainer> getTrainers() {
        return trainers;
    }
    public void setTrainers(Vector<Trainer> trainers) {
        this.trainers = trainers;
        if (trainers != null) {
        	for (Trainer trainer : trainers) {
            	for (Person t : DataBase.getPersons())
            		if (t.getPersonID().equals(trainer.getPersonID())) {
            			t.getSessions().add(this);
            			break;
            		}
            }
        }
    }
    
    /** adds a member to the session */
    public boolean addMember(Member member) { 
    	if (members == null)
    		 members = new Vector<Member>();
    	if(members.size() <= maxParticipants) {
    		members.addElement(member);
    		return true;
    	}
    	return false;
    }
    
    /** checks if the session is open (requires at least 5 members) */
    public boolean isSessionOpen() { 
    	if(members.size() >= 5)
  		  return true;
    	return false;
    }
    
    /** checks for the session if the registration is available (amount of registered members has not reached maxParticipants) */
    public boolean isRegistrationAvailable() { 
    	if(members == null || members.size() < maxParticipants)
  		  return true;
    	return false;
    }
    
    @Override
    public String toString() {
    	String str;
    	String pstr = super.toString();
    	pstr = pstr.substring(16);
    	pstr = pstr.substring(0, pstr.length() - 1);
    	Vector<String> trainerIDs = new Vector<>();
    	if (trainers != null) {
    		for (Trainer trainer : trainers)
        		if (trainer != null)
                	trainerIDs.add("trainer=" + trainer.getPersonID());
    	}
    	Vector<String> membersIDs = new Vector<>();
    	if (members != null) {
    		for (Member member : members)
        		if (member != null)
        			membersIDs.add("member=" + member.getPersonID());
    	}
        str = "GroupTrainingSession{" +
                pstr +
                ", maxParticipants=" + maxParticipants +
                ", trainers=" + trainerIDs + 
                ", members=" + membersIDs +
                '}';
        return str;
    }
}