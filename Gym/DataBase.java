package Gym;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.Vector;

public class DataBase 
{
	private static final String FILE_NAME = "Gym Data.txt";
	private static Vector<Person> persons = new Vector<Person>();
	private static Vector<TrainingSession> sessions = new Vector<TrainingSession>();
	private static Vector<Equipment> equipment = new Vector<Equipment>();

	/**returns vector of all Persons in the database */
	public static Vector<Person> getPersons() {
		return persons;
	}
	
	/**returns vector of all Members in the database */
	public static Vector<Member> getMembers() {
		Vector<Member> members = new Vector<Member>();
		for (Person person : persons)
            if (person instanceof Member)
            	members.add((Member)person);
		return members;
	}
	
	/**returns vector of all Trainers in the database */
	public static Vector<Trainer> getTrainers() {
		Vector<Trainer> trainers = new Vector<Trainer>();
		for (Person person : persons)
            if (person instanceof Trainer)
            	trainers.add((Trainer)person);
		return trainers;
	}
	
	/**returns vector of all TrainingSessions in the database */
	public static Vector<TrainingSession> getSessions() {
		return sessions;
	}
	
	/**returns vector of all Equipment in the database */
	public static Vector<Equipment> getEquipment() {
		return equipment;
	}
	
	/** Save all the persons and sessions to an external text file */
    public static void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            writer.write("Persons:");
            writer.newLine();
            for (Person person : persons) {
                writer.write(person.toString());
                writer.newLine();
            }

            writer.write("Sessions:");
            writer.newLine();
            for (TrainingSession session : sessions) {
            	writer.write(session.toString());
        		writer.newLine();
            }
            
            writer.write("Equipment:");
            writer.newLine();
            for (Equipment equip : equipment) {
                writer.write(equip.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    /** Load all persons and sessions from an external text file*/
    public static void loadDataFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isReadingPersons = false;
                boolean isReadingSessions = false;
                boolean isReadingEquipment = false;

                while ((line = reader.readLine()) != null) {
                    if (line.equals("Persons:")) {
                        isReadingPersons = true;
                        isReadingSessions = false;
                        isReadingEquipment = false;
                    } else if (line.equals("Sessions:")) {
                        isReadingPersons = false;
                        isReadingSessions = true;
                        isReadingEquipment = false;
                    } else if (line.equals("Equipment:")) {
                        isReadingPersons = false;
                        isReadingSessions = false;
                        isReadingEquipment = true;
                    } else if (isReadingPersons) {
                        persons.add(parsePerson(line));
                    } else if (isReadingSessions) {
                        sessions.add(parseSession(line));
                    } else if (isReadingEquipment) {
                        equipment.add(parseEquipment(line));
                    }
                }
                setMembersSessions();
            }
        } catch (IOException e) {
            System.err.println("Error handling the file: " + e.getMessage());
        }
    }


    /** Parse a Person from a string based on the toString format*/
    private static Person parsePerson(String line) {
        line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
        String[] parts = line.split(", ");
        String personID = parts[0].split("=")[1];
        String firstName = parts[1].split("=")[1];
        String lastName = parts[2].split("=")[1];
        GregorianCalendar birthDate = parseDate(parts[3]);
        String email = parts[4].split("=")[1];
        String phone = parts[5].split("=")[1];
        String password = parts[6].split("=")[1];
        
        // Determine the type of person (Member or Trainer)
        if (line.contains("joiningDate")) {   
        	GregorianCalendar joiningDate = parseDate(line.substring(line.indexOf("joiningDate="), line.indexOf(", membership")));  
            Member member = new Member(personID, firstName, lastName, birthDate, email, phone, password, joiningDate);
            member.setMembership(parseMembership(line));
            return member;
        } else if (line.contains("specialization")) {
            String specialization = line.substring(line.indexOf("specialization=") + 15, line.lastIndexOf(","));
            boolean isPersonalTrainer = Boolean.parseBoolean(line.substring(line.indexOf("isPersonalTrainer=") + 18));
            Trainer trainer = new Trainer(personID, firstName, lastName, birthDate, email, phone, password, specialization, isPersonalTrainer);
            return trainer;
        }
        return null;
    }

    /** Parse a TrainingSession from a string based on the toString format*/
    private static TrainingSession parseSession(String line) 
    {
        line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
        String[] parts = line.split(", ");
        String sessionID = parts[0].split("=")[1];
        String name = parts[1].split("=")[1];   
        GregorianCalendar dateTime = parseDateTime(parts[2], parts[3]);     
        int duration = Integer.parseInt(parts[4].split("=")[1]);
        String location = parts[5].split("=")[1];

        // Determine the type of session (Group or Personal)
    	if (line.contains("maxParticipants")) {
    		int maxParticipants = Integer.parseInt(parts[6].split("=")[1]);
    		
    		String trainersStr = line.substring(line.indexOf("trainers=["), line.lastIndexOf("], members"));
    		GroupTrainingSession session = new GroupTrainingSession(sessionID, name, dateTime, duration, location, maxParticipants, parseTrainersInSession(trainersStr));
    		String membersStr = line.substring(line.indexOf("members=["), line.lastIndexOf("]"));
    		session.setMembers(parseMembersInSession(membersStr));
    		
    		return session;
    	} else if (line.contains("trainer")) {
    		String trainerStr = line.substring(line.indexOf("trainer="), line.lastIndexOf(", member"));
    		PersonalTrainingSession session = new PersonalTrainingSession(sessionID, name, dateTime, duration, location, parseTrainer(trainerStr));
    		String memberStr = line.substring(line.lastIndexOf(", ") + 2);
    		session.setMember(parseMember(memberStr));
    		
    		return session;
    	}
        return null;
    }
    
    /** Parse an Equipment from a string based on the toString format*/
    private static Equipment parseEquipment(String line) {
        line = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
        String[] parts = line.split(", ");
        String equipmentID = parts[0].split("=")[1];
        String name = parts[1].split("=")[1];
        String isWorking = parts[2].split("=")[1];
        GregorianCalendar maintenanceDate = parseDate(parts[3]);
        String location = parts[4].split("=")[1];
        return new Equipment(equipmentID, name, Boolean.parseBoolean(isWorking), maintenanceDate, location);
    }
    
    /** Parse a GregorianCalendar with time from a string based on the toString format*/
    private static GregorianCalendar parseDateTime(String date, String time) {
    	String[] dateParts = date.split("=")[1].split(",");
    	String[] timeParts = time.split("=")[1].split(":");
        return new GregorianCalendar(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]), Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));
    }
    
    /** Parse a GregorianCalendar from a string based on the toString format*/
    private static GregorianCalendar parseDate(String date) {
    	String[] dateParts = date.split("=")[1].split(",");
        return new GregorianCalendar(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
    }
    
    /** Parse a Membership from a string based on the toString format*/
    private static Membership parseMembership(String line) {
    	if (line.substring(line.indexOf("membership=") + 11).equals("null"))
    		return null;
    	String type = line.substring(line.indexOf("type=") + 5, line.lastIndexOf(","));
        String discount = line.substring(line.indexOf("discount=") + 9, line.lastIndexOf("}"));
        return new Membership(type, discount);
    }
    
    /** Parse all Trainers from Training Session*/
    private static Vector<Trainer> parseTrainersInSession(String line) {
    	if (line.length() < 12)
    		return null;
    	Vector<Trainer> trainers = new Vector<Trainer>();
    	String[] parts = line.split("trainer=");
    	for (int i = 1; i < parts.length; i++) {
    		if (parts[i].contains(", "))
    			parts[i] = parts[i].substring(0, parts[i].indexOf(","));
    		trainers.add(parseTrainer("trainer=" + parts[i]));
    	}
    	return trainers;
    }
    
    /** Parse Trainer from a string based on the toString format*/
    private static Trainer parseTrainer(String line) {
    	if (line.length() < 12)
    		return null;  	
    	String trainerID = line.substring(8);
    	for (Trainer trainer : getTrainers()) {
    		if (trainer.getPersonID().equals(trainerID))
    			return trainer;
    	}	
    	return null;
    }
    
    /** Parse all Members from Training Session*/
    private static Vector<Member> parseMembersInSession(String line) {
    	if (line.length() < 12)
    		return null;
    	Vector<Member> members = new Vector<Member>();
    	String[] parts = line.split("member=");
    	for (int i = 1; i < parts.length; i++) {
    		if (parts[i].contains(", "))
    			parts[i] = parts[i].substring(0, parts[i].indexOf(","));
    		members.add(parseMember("member=" + parts[i]));
    	}
    	return members;
    }
    
    /** Parse Member from a string based on the toString format*/
    private static Member parseMember(String line) {
    	if (line.length() < 12)
    		return null;  	
    	String memberID = line.substring(7);
    	for (Member member : getMembers()) {
    		if (member.getPersonID().equals(memberID))
    			return member;
    	}	
    	return null;
    }
    
    /**add Training session for all Persons in the system after reading*/
    private static void setMembersSessions() {
    	for (TrainingSession session : sessions) {
    		if(session instanceof GroupTrainingSession) {
    			if (((GroupTrainingSession)session).getMembers() != null) {
    				for (Member sMember : ((GroupTrainingSession)session).getMembers()) {
            			for (Member member : getMembers())
            				if (sMember.getPersonID().equals(member.getPersonID()))
            					member.getSessions().add(session);
            		}
    			}
    		}
    		else {
    			for (Member member : getMembers())
    				if (((PersonalTrainingSession)session).getMember() != null && ((PersonalTrainingSession)session).getMember().getPersonID().equals(member.getPersonID()))
    					member.getSessions().add(session);
    		}
    	}
    }
}