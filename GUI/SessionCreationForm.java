package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import javax.swing.*;
import java.util.Vector;

import Gym.*;
import Gym.Constants.Locations;

public class SessionCreationForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, idLabel, nameLabel, dateLabel, startTimeLabel, durationLabel, locationLabel, sessionTypeLabel, maxParticipantsLabel, errorLabel, trainerLabel;
    private TextField idField, nameField, durationField, maxParticipantsField;
    private Choice dayChoice, monthChoice, yearChoice, hourChoice, minuteChoice, locationChoice;
    private ButtonGroup sessionTypeGroup;
    private JCheckBox personalSessionCheckbox, groupSessionCheckbox;
    private Button submitButton;
    private TrainingSession session;
    private Vector<Trainer> trainers = DataBase.getTrainers();

    @SuppressWarnings("deprecation")
    public SessionCreationForm() {
        super("Session Registration Form");
        setSize(530, 500);
        setMenuBar(new Menus());
        setLayout(new BorderLayout(10, 10));

        Panel northPanel = new Panel(new BorderLayout());
        
        Button backButton = new Button("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagementPage();
                dispose();
            }
        });
        northPanel.add(backButton, BorderLayout.WEST);
        
        titleLabel = new Label("Session Registration Form", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        Panel contentPanel = new Panel(new GridLayout(9, 2, 5, 5));

        idLabel = new Label("Session ID:");
        idField = new TextField();
        contentPanel.add(idLabel);
        contentPanel.add(idField);

        nameLabel = new Label("Session Name:");
        nameField = new TextField();
        contentPanel.add(nameLabel);
        contentPanel.add(nameField);

        dateLabel = new Label("Date:");
        Panel datePanel = new Panel(new FlowLayout(FlowLayout.LEFT));

        dayChoice = new Choice();
        for (int i = 1; i <= 31; i++)
            dayChoice.add(String.format("%02d", i));
        monthChoice = new Choice();
        for (int i = 1; i <= 12; i++)
            monthChoice.add(String.format("%02d", i));
        yearChoice = new Choice();
        yearChoice.add("2025");

        datePanel.add(dayChoice);
        datePanel.add(monthChoice);
        datePanel.add(yearChoice);
        contentPanel.add(dateLabel);
        contentPanel.add(datePanel);

        startTimeLabel = new Label("Starting Time:");
        Panel startTimePanel = new Panel(new FlowLayout(FlowLayout.LEFT));

        hourChoice = new Choice();
        for (int i = 0; i < 24; i++)
            hourChoice.add(String.format("%02d", i));
        minuteChoice = new Choice();
        for (int i = 0; i < 60; i++)
            minuteChoice.add(String.format("%02d", i));

        startTimePanel.add(hourChoice);
        startTimePanel.add(new Label(":"));
        startTimePanel.add(minuteChoice);
        contentPanel.add(startTimeLabel);
        contentPanel.add(startTimePanel);

        durationLabel = new Label("Duration (in minutes):");
        durationField = new TextField();
        contentPanel.add(durationLabel);
        contentPanel.add(durationField);

        locationLabel = new Label("Location:");
        locationChoice = new Choice();
        for (Locations location : Constants.Locations.values()) {
        	locationChoice.add(location.name());
        }
        contentPanel.add(locationLabel);
        contentPanel.add(locationChoice);

        sessionTypeLabel = new Label("Session Type:");
        Panel sessionTypePanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        sessionTypeGroup = new ButtonGroup();
        personalSessionCheckbox = new JCheckBox("Personal Session");
        groupSessionCheckbox = new JCheckBox("Group Session");
        sessionTypeGroup.add(personalSessionCheckbox);
        sessionTypeGroup.add(groupSessionCheckbox);
        personalSessionCheckbox.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                if (((JCheckBox) e.getSource()).isSelected())
                    maxParticipantsField.disable();
            }
        });
        groupSessionCheckbox.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                if (((JCheckBox) e.getSource()).isSelected())
                    maxParticipantsField.enable();
            }
        });
        sessionTypePanel.add(personalSessionCheckbox);
        sessionTypePanel.add(groupSessionCheckbox);
        contentPanel.add(sessionTypeLabel);
        contentPanel.add(sessionTypePanel);

        maxParticipantsLabel = new Label("Maximum Participants:");
        maxParticipantsField = new TextField();
        maxParticipantsField.disable();
        contentPanel.add(maxParticipantsLabel);
        contentPanel.add(maxParticipantsField);

        trainerLabel = new Label("Select Trainers:");
        ScrollPane trainerScrollPane = new ScrollPane();
        Panel trainerPanel = new Panel(new GridLayout(0, 1));
        for (Trainer trainer : trainers) {
            Checkbox trainerCheckbox = new Checkbox(trainer.getFirstName() + " " + trainer.getLastName());
            trainerPanel.add(trainerCheckbox);
        }
        trainerScrollPane.add(trainerPanel);
        contentPanel.add(trainerLabel);
        contentPanel.add(trainerScrollPane);
        add(contentPanel, BorderLayout.CENTER);
        
        Panel bottomPanel = new Panel(new BorderLayout(5, 5));
        submitButton = new Button("Submit");
        submitButton.setPreferredSize(new Dimension(360, 30));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	checkIfIDAvailable(idField.getText());
                    GregorianCalendar date = new GregorianCalendar(Integer.parseInt(yearChoice.getSelectedItem()), Integer.parseInt(monthChoice.getSelectedItem()) - 1, Integer.parseInt(dayChoice.getSelectedItem()), 
                    		Integer.parseInt(hourChoice.getSelectedItem()), Integer.parseInt(minuteChoice.getSelectedItem()));
                    checkTime(date, Integer.parseInt(durationField.getText()));
                    
                    Vector<Trainer> selectedTrainers = new Vector<>();
                    for (Component component : trainerPanel.getComponents()) {
                        if (component instanceof Checkbox) {
                        	Checkbox checkBox = (Checkbox) component;
                            if (checkBox.getState())
                                for (Trainer trainer : trainers) {
                                    if ((trainer.getFirstName() + " " + trainer.getLastName()).equals(checkBox.getLabel())) {
                                        selectedTrainers.add(trainer);
                                        break;
                                    }
                                }
                        }
                    }
                    if (personalSessionCheckbox.isSelected()) {
                    	if (selectedTrainers.size() > 1)
                    		throw new IllegalArgumentException("Personal training session can only have one trainer");
                    	session = new PersonalTrainingSession(idField.getText(), nameField.getText(), date, Integer.parseInt(durationField.getText()), locationChoice.getSelectedItem(), selectedTrainers.firstElement());
                    }
                    else
                        session = new GroupTrainingSession(idField.getText(), nameField.getText(), date, Integer.parseInt(durationField.getText()), locationChoice.getSelectedItem(), Integer.parseInt(maxParticipantsField.getText()), selectedTrainers);
                    dispose();
                    DataBase.getSessions().add(session);
                    new ManagementPage();
                    new Notification("Training session " + nameField.getText() + " was successfully added to the system");
                } catch (Exception e2) {
                    errorLabel.setText("Error: " + e2.getMessage());
                }
            }
        });
        bottomPanel.add(submitButton, BorderLayout.NORTH);

        errorLabel = new Label("", Label.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);
        bottomPanel.add(errorLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }

    public TrainingSession getSession() {
        return session;
    }
    
    /**check if there is already a TrainingSession with the same ID*/
    private void checkIfIDAvailable(String id) {
    	for (TrainingSession session : DataBase.getSessions()) {
    		if (session.getSessionID().equals(id))
    			throw new IllegalArgumentException("There already is a training session with the same ID");
    	}
    }
    
    /**check if time chosen + duration is within opening time*/
    private void checkTime(GregorianCalendar date, int duration) {
    	GregorianCalendar newDate = (GregorianCalendar) date.clone();
        newDate.add(GregorianCalendar.MINUTE, duration);
        if (date.get(GregorianCalendar.HOUR_OF_DAY) < Constants.OPENING_HOUR || newDate.get(GregorianCalendar.HOUR_OF_DAY) > Constants.CLOSING_HOUR)
        	throw new IllegalArgumentException(String.format("The time for the session does not align with opening time (%02d:%02d) - (%02d:%02d)", Constants.OPENING_HOUR, 0, Constants.CLOSING_HOUR, 0));
    }
}
