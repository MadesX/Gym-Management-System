package Gym;

import java.util.GregorianCalendar;

public class PersonalTrainingSession extends TrainingSession 
{
    private Trainer trainer;
    private Member member;
    
    public PersonalTrainingSession(String sessionID, String name, GregorianCalendar dateTime, int duration, String location, Trainer trainer) {
        super(sessionID, name, dateTime, duration, location);
        setTrainer(trainer);
    }
    
    public PersonalTrainingSession(String sessionID, String name, int duration, String location, Trainer trainer) {
        super(sessionID, name, duration, location);
        setTrainer(trainer);
    }

    public Trainer getTrainer() { 
    	return trainer; 
    }
    public void setTrainer(Trainer trainer) { 
    	this.trainer = trainer; 
    	for (Person t : DataBase.getPersons())
    		if (t.getPersonID().equals(trainer.getPersonID())) {
    			t.getSessions().add(this);
    			break;
    		}
    }
    
    public Member getMember() { 
    	return member; 
    }
    public void setMember(Member member) { 
    	this.member = member; 
    }
    
    @Override
    public String toString() {
    	String str;
    	String pstr = super.toString();
    	pstr = pstr.substring(16);
    	pstr = pstr.substring(0, pstr.length() - 1);
        str = "PersonalTrainingSession{" +
                pstr +
                ", trainer=" + trainer.getPersonID();
        if (member == null) 
        	str += ", member=" + null + '}';
        else
        	str += ", member=" + member.getPersonID() + '}';
        return str;
    }
}