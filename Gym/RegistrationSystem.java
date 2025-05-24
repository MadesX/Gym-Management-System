package Gym;

import java.util.Vector;

public class RegistrationSystem 
{	
	private static Vector<TrainingSession> sessions = DataBase.getSessions();
	private static Vector<Member> members = DataBase.getMembers();
	
	/** retrieves details of a training session based on its ID */
	public String getSessionDetails(String sessionID) {
		for (int i=0; i < sessions.size(); i++) 
			if (sessionID == sessions.elementAt(i).getSessionID())
				return sessions.elementAt(i).toString();
		return "No Session with this ID";
	}

	/** registers a member to a training session */
	public static String register(Member member, TrainingSession session) {
		String registrationStatus = "";
		
		for (Member m : members) {
            if (m.equals(member)) {
            	for (TrainingSession s : sessions) {
                    if (s.equals(session)) {
						if (session instanceof GroupTrainingSession) {
							if (checkAlreadyRegistered(m, session))
								return m.getFirstName() + " " + m.getLastName() + " is already registered to Training session " + session.getName();
							if (!((GroupTrainingSession) s).addMember(member))
								return "Group Training session " + session.getName() + " is already full";
						}
			            else 
			            	((PersonalTrainingSession) s).setMember(member);		            	
						m.getSessions().addElement(session);
						registrationStatus += "Registration complete for " + m.getFirstName() + " " + m.getLastName() + " to " + session.getName();
						
						if(session instanceof GroupTrainingSession && !((GroupTrainingSession) s).isSessionOpen())
							registrationStatus += "  | warning: Session will open only if there is a minimum of 5 members.\n";
						return registrationStatus;
					}
				}
			}
		}
		return "No such session or member";
	}
	
	/** find member in system and check if he's already registered to session*/
	public static boolean checkAlreadyRegistered(Member member, TrainingSession session) {
        for (Member m : members)
            if (m.equals(member))
                return m.getSessions().contains(session);
        return false;
    }
}