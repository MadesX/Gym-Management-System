package Gym;

import java.util.GregorianCalendar;

public class Member extends Person 
{
    private GregorianCalendar joiningDate;
    private Membership membership;
    
    public Member(String personID, String firstName, String lastName, GregorianCalendar birthDate, String email, String phone, String password, GregorianCalendar joiningDate) {
    	super(personID, firstName, lastName, birthDate, email, phone, password);
    	setJoiningDate(joiningDate);
    }
    
    public Member(String personID, String firstName, String lastName, GregorianCalendar birthDate, String email, String phone, String password) {
    	super(personID, firstName, lastName, birthDate, email, phone, password);
    	setJoiningDate(new GregorianCalendar());
    }

    public GregorianCalendar getJoiningDate() { 
    	return joiningDate; 
    }
    public void setJoiningDate(GregorianCalendar joiningDate) { 
    	this.joiningDate = joiningDate; 
    }
    
    public Membership getMembership() { 
    	return this.membership; 	
    }
    public void setMembership(Membership membership) { 
    	this.membership = membership; 
    }
    
    @Override
    public String toString() {
    	String str;
    	String pstr = super.toString();
    	pstr = pstr.substring(7);
    	pstr = pstr.substring(0, pstr.length() - 1);
        str = "Member{" +
               pstr +
               ", joiningDate=" + joiningDate.get(GregorianCalendar.DAY_OF_MONTH) + "," + joiningDate.get(GregorianCalendar.MONTH) + "," + joiningDate.get(GregorianCalendar.YEAR) +
               ", membership=" + membership + "}";
        return str;
    }
}