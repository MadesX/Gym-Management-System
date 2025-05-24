package Gym;

import java.util.GregorianCalendar;

public class Trainer extends Person 
{
    private String specialization;
    private boolean isPersonalTrainer;

    public Trainer(String personID, String firstName, String lastName, GregorianCalendar birthDate, String email, String phone, String password, String specialization, boolean isPersonalTrainer) {
    	super(personID, firstName, lastName, birthDate, email, phone, password);
    	setSpecialization(specialization);
    	setIsPersonalTrainer(isPersonalTrainer);
    }
    
    public String getSpecialization() { 
    	return specialization; 
    }
    public void setSpecialization(String specialization) {
    	Tests.checkNotEmpty(specialization);
    	this.specialization = specialization; 
    }
    
    public boolean isPersonalTrainer() { 
        return isPersonalTrainer;
    }
    public void setIsPersonalTrainer(boolean isPersonalTrainer) { 
        this.isPersonalTrainer = isPersonalTrainer; 
    }
    
    @Override
    public String toString() {
    	String str;
    	String pstr = super.toString();
    	pstr = pstr.substring(7);
    	pstr = pstr.substring(0, pstr.length() - 1);
        str = "Trainer{" +
               pstr +
               ", specialization=" + specialization +
               ", isPersonalTrainer=" + isPersonalTrainer + "}";
        return str;
    }
}