package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Gym.Constants;
import Gym.DataBase;
import Gym.Member;
import Gym.Trainer;
import Gym.TrainingSession;

public class GymSchedule extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<TrainingSession> sessions;
    private int openingHour;
    private int closingHour;

    public GymSchedule(Object obj) {
        super("Gym Schedule");
        this.sessions = DataBase.getSessions();
        this.openingHour = Constants.OPENING_HOUR;
        this.closingHour = Constants.CLOSING_HOUR;
        
        setMenuBar(new Menus());
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Button backButton = new Button("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (obj instanceof Member)
                    new MemberPage((Member) obj);
                else if (obj instanceof Trainer)
                    new TrainerPage((Trainer) obj);
                else
                	new ManagementPage();
                dispose();
            }
        });
        northPanel.add(backButton);
        add(northPanel, BorderLayout.NORTH);

        // Get the current week number and year
        Calendar now = Calendar.getInstance();
        int currentWeek = now.get(Calendar.WEEK_OF_YEAR);
        int currentYear = now.get(Calendar.YEAR);

        // Filter sessions for the current week
        Vector<TrainingSession> currentWeekSessions = new Vector<>();
        for (TrainingSession session : sessions) {
            Calendar sessionDate = session.getDateTime();
            int sessionWeek = sessionDate.get(Calendar.WEEK_OF_YEAR);
            int sessionYear = sessionDate.get(Calendar.YEAR);

            if (sessionWeek == currentWeek && sessionYear == currentYear)
                currentWeekSessions.add(session);
        }
        add(createTablePanel(currentWeekSessions), BorderLayout.CENTER);

        setSize(800, 602);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JScrollPane createTablePanel(Vector<TrainingSession> sessions) {
        String[] columnNames = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        // Calculate the number of rows based on the opening and closing hours
        int numberOfRows = closingHour - openingHour + 1;
        Object[][] data = new Object[numberOfRows][8];
        for (int i = 0; i < numberOfRows; i++) {
            int hour = openingHour + i;
            data[i][0] = String.format("%02d:00", hour);
        }

        // Collect sessions by day and time
        Map<Integer, Map<Integer, StringBuilder>> daySessionsMap = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            daySessionsMap.put(i, new HashMap<>());
        }

        for (TrainingSession session : sessions) {
            int dayOfWeek = session.getDateTime().get(Calendar.DAY_OF_WEEK) - 1;
            int hourOfDay = session.getDateTime().get(Calendar.HOUR_OF_DAY);
            
            // Add session to correct time slot for the day
            StringBuilder sessionDetails = daySessionsMap.get(dayOfWeek).getOrDefault(hourOfDay, new StringBuilder());
            if (sessionDetails.length() != 0)
            	sessionDetails.append(" | ");
            sessionDetails.append(session.getSessionID() + " - " + session.getName());
            daySessionsMap.get(dayOfWeek).put(hourOfDay, sessionDetails);
        }

        // Populate the table with session details
        for (int day = 0; day < 7; day++) {
            for (int hour = openingHour; hour <= closingHour; hour++) {
                StringBuilder sessionDetails = daySessionsMap.get(day).get(hour);
                if (sessionDetails != null) {
                    data[hour - openingHour][day + 1] = sessionDetails.toString();
                }
            }
        }

        // Create the table with data
        @SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(30); // Adjust row height to fit hour slots
        table.getColumnModel().getColumn(0).setMaxWidth(60);
        
        table.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    int selectedCol = table.getSelectedColumn();
                    if (selectedRow != -1 && selectedCol != -1)
                    	if (model.getValueAt(selectedRow, selectedCol) != null)
                    		showSessionDetails(model.getValueAt(selectedRow, selectedCol).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
    
    private void showSessionDetails(String sessionSlot) {
    	String sessionID = sessionSlot.substring(0, sessionSlot.indexOf(" -"));
    	TrainingSession session = new TrainingSession();
    	for (TrainingSession s : DataBase.getSessions())
    		if (s.getSessionID().equals(sessionID))
    			session = s;
        Dialog detailsDialog = new Dialog(this, "Session Details");
        detailsDialog.setSize(400, 300);
        detailsDialog.setLocationRelativeTo(this);

        Panel detailsPanel = new Panel();
        detailsPanel.setLayout(new GridLayout(5, 2, 10, 10));

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        detailsPanel.add(new Label("Session ID:"));
        detailsPanel.add(new Label(session.getSessionID()));

        detailsPanel.add(new Label("Name:"));
        detailsPanel.add(new Label(session.getName()));

        detailsPanel.add(new Label("Date & Time:"));
        detailsPanel.add(new Label(dateTimeFormat.format(session.getDateTime().getTime())));

        detailsPanel.add(new Label("Duration (minutes):"));
        detailsPanel.add(new Label(String.valueOf(session.getDuration())));

        detailsPanel.add(new Label("Location:"));
        detailsPanel.add(new Label(session.getLocation()));

        detailsDialog.add(detailsPanel, BorderLayout.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setPreferredSize(new Dimension(200, 50));
        closeButton.addActionListener(e -> detailsDialog.dispose());
        detailsDialog.add(closeButton, BorderLayout.SOUTH);

        detailsDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                detailsDialog.dispose();
            }
        });

        detailsDialog.setVisible(true);
    }
}