package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

import Gym.*;

public class RegistrationForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, idLabel, firstNameLabel, lastNameLabel, birthdateLabel, emailLabel, phoneLabel, passwordLabel, specializationLabel, personalTrainerLabel, errorLabel;
    private TextField idField, firstNameField, lastNameField, emailField, phoneField, passwordField, specializationField;
    private Choice dayChoice, monthChoice, yearChoice;
    private Button submitButton, backButton;
    private Checkbox personalTrainerCheckbox, noTrainerCheckbox;

    public RegistrationForm(Object obj, boolean isTrainer) {
        super("Registration Form");
        setSize(400, 450);
        setMenuBar(new Menus());
        setLayout(new BorderLayout(10, 10));

        Panel northPanel = new Panel(new BorderLayout());
        backButton = new Button("Back");
        backButton.setPreferredSize(new Dimension(60, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (obj instanceof Management)
                    new ManagementPage();
                else if (obj == null)
                    new HomePage();
                dispose();
            }
        });
        northPanel.add(backButton, BorderLayout.WEST);

        titleLabel = new Label("Registration Form", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        northPanel.add(titleLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        Panel contentPanel = new Panel(new GridLayout(9, 2, 5, 5));
        if (!isTrainer) {
        	contentPanel = new Panel(new GridLayout(7, 2, 5, 5));
        	setSize(400, 380);
        }
        idLabel = new Label("ID:");
        idField = new TextField();
        contentPanel.add(idLabel);
        contentPanel.add(idField);

        firstNameLabel = new Label("First Name:");
        firstNameField = new TextField();
        contentPanel.add(firstNameLabel);
        contentPanel.add(firstNameField);

        lastNameLabel = new Label("Last Name:");
        lastNameField = new TextField();
        contentPanel.add(lastNameLabel);
        contentPanel.add(lastNameField);

        birthdateLabel = new Label("Birthdate:");
        Panel birthdatePanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        dayChoice = new Choice();
        for (int i = 1; i <= 31; i++) {
            dayChoice.add(String.format("%02d", i));
        }
        monthChoice = new Choice();
        for (int i = 1; i <= 12; i++) {
            monthChoice.add(String.format("%02d", i));
        }
        yearChoice = new Choice();
        for (int i = 1950; i <= 2025; i++) {
            yearChoice.add(Integer.toString(i));
        }
        birthdatePanel.add(dayChoice);
        birthdatePanel.add(monthChoice);
        birthdatePanel.add(yearChoice);
        contentPanel.add(birthdateLabel);
        contentPanel.add(birthdatePanel);

        emailLabel = new Label("Email:");
        emailField = new TextField();
        contentPanel.add(emailLabel);
        contentPanel.add(emailField);

        phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        contentPanel.add(phoneLabel);
        contentPanel.add(phoneField);

        passwordLabel = new Label("Password:");
        passwordField = new TextField();
        passwordField.setEchoChar('*'); // Hide password input
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        if (isTrainer) {
            specializationLabel = new Label("Specialization:");
            specializationField = new TextField();
            contentPanel.add(specializationLabel);
            contentPanel.add(specializationField);

            personalTrainerLabel = new Label("Personal Trainer:");
            Panel trainerPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
            personalTrainerCheckbox = new Checkbox("Yes");
            noTrainerCheckbox = new Checkbox("No", true);
            CheckboxGroup trainerGroup = new CheckboxGroup();
            personalTrainerCheckbox.setCheckboxGroup(trainerGroup);
            noTrainerCheckbox.setCheckboxGroup(trainerGroup);
            trainerPanel.add(personalTrainerCheckbox);
            trainerPanel.add(noTrainerCheckbox);
            contentPanel.add(personalTrainerLabel);
            contentPanel.add(trainerPanel);
        }
        add(contentPanel, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout(5, 5));
        submitButton = new Button("Submit");
        submitButton.setPreferredSize(new Dimension(360, 30));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkIfIDAvailable(idField.getText());
                    GregorianCalendar birthDate = new GregorianCalendar(
                            Integer.parseInt(yearChoice.getSelectedItem()),
                            Integer.parseInt(monthChoice.getSelectedItem()) - 1,
                            Integer.parseInt(dayChoice.getSelectedItem()));
                    Person person;
                    if (!isTrainer)
                    	person = new Member(idField.getText(), firstNameField.getText(), lastNameField.getText(), birthDate, emailField.getText(), phoneField.getText(), passwordField.getText());
                    else
                    	person = new Trainer(idField.getText(), firstNameField.getText(), lastNameField.getText(), birthDate,
                    			emailField.getText(), phoneField.getText(), passwordField.getText(), specializationField.getText(), personalTrainerCheckbox.getState());
                    DataBase.getPersons().add(person);
                    dispose();
                    if (obj instanceof Management) {
                        new ManagementPage();
                        new Notification("Member registration for " + firstNameField.getText() + " " + lastNameField.getText() + " was completed successfully");
                    } else {
                        new LoginPage();
                        new Notification(firstNameField.getText() + " " + lastNameField.getText() + " your registration was successful");
                    }
                } catch (Exception e2) {
                    errorLabel.setText(e2.getMessage());
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

    /** Check if there is already a Person with the same ID */
    private void checkIfIDAvailable(String id) {
        for (Person person : DataBase.getPersons()) {
            if (person.getPersonID().equals(id))
                throw new IllegalArgumentException("There already is a user with the same ID");
        }
    }
}
