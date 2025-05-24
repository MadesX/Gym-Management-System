package Gym;

import java.util.GregorianCalendar;
import java.util.Vector;

public class Person 
{
    private String personID;
    private String firstName;
    private String lastName;
    private GregorianCalendar birthDate;
    private String email;
    private String phone;
    private String password;
    private Vector<TrainingSession> sessions = new Vector<TrainingSession>();

    public Person(String personID, String firstName, String lastName, GregorianCalendar birthDate, String email, String phone, String password) {
        setPersonID(personID);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
    }
    
    public Person() {
        this.personID = "";
        this.firstName = "";
        this.lastName = "";
        this.birthDate = new GregorianCalendar();
        this.email = "";
        this.phone = "";
        this.password = "";
    }

    public Person(Person other) {
        this.personID = other.personID;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.birthDate = other.birthDate;
        this.email = other.email;
        this.phone = other.phone;
        this.password = other.password;
        this.sessions = other.sessions;
    }

    public String getPersonID() { 
    	return personID; 
    }
    public void setPersonID(String personID) { 
    	Tests.checkPersonID(personID);
    	this.personID = personID; 
    }

    public String getFirstName() { 
    	return firstName; 
    }
    public void setFirstName(String firstName) { 
    	Tests.checkName(firstName);
    	this.firstName = firstName; 
    }

    public String getLastName() { 
    	return lastName; 
    }
    public void setLastName(String lastName) { 
    	Tests.checkName(lastName);
    	this.lastName = lastName; 
    }

    public GregorianCalendar getBirthDate() { 
    	return birthDate; 
    }
    public void setBirthDate(GregorianCalendar birthDate) { 
    	Tests.checkNotNull(birthDate);
    	this.birthDate = birthDate; 
    }

    public String getEmail() { 
    	return email; 
    }
    public void setEmail(String email) { 
    	Tests.checkEmail(email);
    	this.email = email; 
    }

    public String getPhone() { 
    	return phone; 
    }
    public void setPhone(String phone) { 
    	Tests.checkPhone(phone);
    	this.phone = phone; 
    }
    
    public Vector<TrainingSession> getSessions() {
        return sessions;
    }
    public void setSessions(Vector<TrainingSession> sessions) {
        this.sessions = sessions;
    }
    
    public void setPassword(String password) {
    	Tests.checkPassword(password);
        this.password = password;
    }
    
    /** confirm if given password is the same as the one saved */
    public boolean confirmPassword(String password) {
    	return this.password.equals(password);
    }
    
    /** check if Person is registered to session */
    public boolean checkRegisteredToSession(String sessionid) {
    	for (TrainingSession session : sessions) {
            if (session.getSessionID().equals(sessionid))
                return true;
        }
    	return false;
    }
    
    @Override
    public String toString() {
        return "Person{" +
               "personID=" + personID +
               ", firstName=" + firstName +
               ", lastName=" + lastName + 
               ", birthDate=" + birthDate.get(GregorianCalendar.DAY_OF_MONTH) + "," + birthDate.get(GregorianCalendar.MONTH) + "," + birthDate.get(GregorianCalendar.YEAR) + 
               ", email=" + email +
               ", phone=" + phone + 
               ", password=" + password +
               ", sessions= " + sessions +
               '}';
    }
}