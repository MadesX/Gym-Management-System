package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Comparator;
import java.util.GregorianCalendar;

import Gym.*;

public class TrainingSessionDisplay extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button backButton, refreshButton;
	private Label instructionLabel;

	public TrainingSessionDisplay(Object obj, Vector<TrainingSession> sessions, boolean registering) {
	    super("Training Sessions");
	    sessions.sort(Comparator.comparing(TrainingSession::getDateTime));

	    setSize(1000, 600);
	    setMenuBar(new Menus());

	    backButton = new Button("Back");
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (obj instanceof Member)
	                new MemberPage((Member) obj);
	            else if (obj instanceof Trainer)
	                new TrainerPage((Trainer) obj);
	            else if (obj instanceof Management)
	                new ManagementPage();
	            dispose();
	        }
	    });

	    Panel backPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
	    refreshButton = new Button("Refresh");
	    backPanel.add(backButton);
	    backPanel.add(refreshButton);
	    getContentPane().add(backPanel, BorderLayout.NORTH);

	    Panel mainPanel = new Panel();
	    mainPanel.setLayout(new BorderLayout());

	    if (registering) {
	        instructionLabel = new Label("Press on the Training session you want to register to");
	        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
	        mainPanel.add(instructionLabel, BorderLayout.NORTH);
	    }

	    // Create table with column names based on the boolean
	    String[] columnNames = (registering && obj instanceof Member)
	            ? new String[]{"Date and Time", "Session ID", "Session Name", "Location", "Registration Available", "Registered"}
	            : new String[]{"Date and Time", "Session ID", "Session Name", "Location"};

	    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Make the table non-editable
	        }
	    };
	    JTable table = loadTable(model, obj, sessions, registering);
	    table.setRowHeight(40);

	    // Limit the width of specific columns
	    table.getColumnModel().getColumn(1).setMaxWidth(100);
	    if (registering && obj instanceof Member) {
	        table.getColumnModel().getColumn(4).setPreferredWidth(130);
	        table.getColumnModel().getColumn(4).setMaxWidth(130);
	        table.getColumnModel().getColumn(5).setMaxWidth(100);
	    }

	    table.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
	        @Override
	        public void valueChanged(javax.swing.event.ListSelectionEvent event) {
	            if (!event.getValueIsAdjusting() && registering) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    if (obj instanceof Member) {
	                        if (model.getValueAt(selectedRow, 4).equals("Yes") && model.getValueAt(selectedRow, 5).equals("No"))
	                            new sessionRegistrationNotification((Member) obj, model.getValueAt(selectedRow, 1).toString());
	                    } else if (obj instanceof Management)
	                        new sessionCancelationNotification(model.getValueAt(selectedRow, 1).toString());
	                }
	            }
	        }
	    });
	    
	    refreshButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	@SuppressWarnings("unused")
				JTable table = loadTable(model, obj, sessions, registering);
	        }
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(table);    // Add table to scroll pane
	    mainPanel.add(scrollPane, BorderLayout.CENTER);

	    getContentPane().add(mainPanel, BorderLayout.CENTER);

	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    WindowManager.applyDefaultCloseAction(this);
	}
	
	private JTable loadTable(DefaultTableModel model, Object obj ,Vector<TrainingSession> sessions, boolean registering) {
		model.setRowCount(0);
		JTable table = new JTable(model);
	    table.setRowHeight(40);
	    for (int i = 0; i < sessions.size(); i++) {
	        TrainingSession session = sessions.get(i);
	        
	        if (!session.getDateTime().before(new GregorianCalendar())) {
	        	if (registering && obj instanceof Member) {
	        		String registrationAvailable;
	        		if (session instanceof PersonalTrainingSession)
	        			registrationAvailable = ((PersonalTrainingSession) session).getMember() == null ? "Yes" : "No";
	        		else
	        			registrationAvailable = ((GroupTrainingSession) session).isRegistrationAvailable() ? "Yes" : "No";
	        		String registered = ((Person) obj).checkRegisteredToSession(session.getSessionID()) ? "Yes" : "No";
	        		model.addRow(new Object[]{session.getDateTime().getTime().toString(), session.getSessionID(), session.getName(), session.getLocation(), registrationAvailable, registered});
	        	} else
	        		model.addRow(new Object[]{session.getDateTime().getTime().toString(), session.getSessionID(), session.getName(), session.getLocation()});
	        }
	    }
	    return table;
	}
}
