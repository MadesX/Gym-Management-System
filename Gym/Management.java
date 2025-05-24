package Gym;

import java.util.Vector;
import javax.swing.JOptionPane;
import java.util.GregorianCalendar;

import GUI.RegistrationForm;
import GUI.NewEquipmentForm;
import GUI.Notification;
import GUI.SessionCreationForm;

public class Management 
{
    private static Vector<Member> members;
    private static Vector<TrainingSession> sessions;
    
    public Management() {
    	members = DataBase.getMembers();
        sessions = DataBase.getSessions();
    }

    /** call the GUI to create a new Member and add to the database */
    public static void createMember() {
        new RegistrationForm(new Management(), false);
    }
    
    /** delete Member from system*/
    public static void deleteMember() {
        String memberID = getMemberID();
        for (Person person : DataBase.getPersons())
        	if (person.getPersonID().equals(memberID)) {
        		DataBase.getPersons().remove(person);
        		new Notification("Member " + person.getFirstName() + " " + person.getLastName() + " was successfully deleted from system");
        		return;
        	}
        if (memberID != null)
        	new Notification("No Member by ID : " + memberID + " was found in the system");
    }
    
    /** call the GUI to create a new Trainer and add to the database */
    public static void createTrainer() {
        new RegistrationForm(new Management(), true);
    }
    
    /** delete Trainer from system*/
    public static void deleteTrainer() {
        String trainerID = getTrainerID();
        for (Person person : DataBase.getPersons())
        	if (person.getPersonID().equals(trainerID)) {
        		DataBase.getPersons().remove(person);
        		new Notification("Trainer " + person.getFirstName() + " " + person.getLastName() + " was successfully deleted from system");
        		return;
        	}
        if (trainerID != null)
        	new Notification("No Trainer by ID : " + trainerID + " was found in the system");
    }

    /** delete members who's membership ended more than a year ago from the system*/
    public static void deleteExpiredMembers() {
        GregorianCalendar currentDate = new GregorianCalendar();
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (shouldRemoveMember(member, currentDate)) {
                members.remove(i);
                i--; // Adjust index after removal
            }
        }
    }
    
    private static boolean shouldRemoveMember(Member member, GregorianCalendar currentDate) {
        Membership activeMembership = member.getMembership();
        if (activeMembership != null) {
            GregorianCalendar endDate = activeMembership.getEndDate();
            endDate.add(GregorianCalendar.YEAR, 1); // Check end date + 1 year
            return endDate.before(currentDate);
        }
        return false;
    }

    /** call the GUI to create a new Equipment and add to the database */
    public void purchaseEquipment() {
        new NewEquipmentForm();
    }
    
    /** call the GUI to create a new session and add to the database */
    public static void createSession() {
        new SessionCreationForm();
    }

    /**	remove a Session from the system, by it's id*/
    public static void cancelSession(String sessionID) {
        for (TrainingSession session : DataBase.getSessions()) {
            if (session.getSessionID().equals(sessionID)) {
            	DataBase.getSessions().remove(session);
                for (Person person : DataBase.getPersons()) {
                	if (person.getSessions() != null || !person.getSessions().isEmpty()) {
                		for (TrainingSession s : person.getSessions())
                			if (s.getSessionID().equals(sessionID)) {
                				person.getSessions().remove(session);
                				break;
                			}
                	}
                }
                new Notification("Training Session " + sessionID + " was successfully canceled");
                return; 
            }
        }
        new Notification("No Training Session by ID : " + sessionID + " was found in the system");
    }
    
    /** cancel members current membership*/
    public static void cancelMembership() {
        String memberID = getMemberID();
        for (Member member : DataBase.getMembers())
        	if (member.getPersonID().equals(memberID)) {
        		member.setMembership(null);
        		new Notification("Membership for " + member.getFirstName() + " " + member.getLastName() + " was successfully canceled");
        		return;
        	}
        if (memberID != null)
        	new Notification("No Member by ID : " + memberID + " was found in the system");
    }
    
    private static String getMemberID() {
        String memberID = JOptionPane.showInputDialog(null, 
                "Please enter Member ID:", 
                "Member ID Input", 
                JOptionPane.QUESTION_MESSAGE);
        return memberID;
    }
    
    private static String getTrainerID() {
        String trainerID = JOptionPane.showInputDialog(null, 
                "Please enter Trainer ID:", 
                "Trainer ID Input", 
                JOptionPane.QUESTION_MESSAGE);
        return trainerID;
    }
    
    public Vector<TrainingSession> getSessions() {
    	return sessions;
    }
}