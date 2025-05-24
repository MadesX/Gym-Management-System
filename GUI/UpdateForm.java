package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

import Gym.Person;
import Gym.Member;
import Gym.Trainer;

public class UpdateForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, firstNameLabel, lastNameLabel, emailLabel, phoneLabel, passwordLabel, specialtyLabel, joiningDateLabel, idLabel;
    private TextField firstNameField, lastNameField, emailField, phoneField, specialtyField, joiningDateField, idField;
    private Checkbox personalTrainerCheckbox;
    private Button updateButton, backButton;
    private Person userObject;
    private Label errorLabel;
    private TextField passwordField;

    public UpdateForm(Person user) {
        super(getTitleForUser(user));
        this.userObject = user;

        setSize(500, 530);
        setMenuBar(new Menus());
        setLayout(new BorderLayout()); 

        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        backButton = new Button("Back");
        Panel topPanel = new Panel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        topPanel.add(backButton); 

        add(topPanel, BorderLayout.NORTH);

        titleLabel = new Label(getTitleForUser(user), Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignment(Label.CENTER); 
        gbc.gridwidth = 2; 
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        idLabel = new Label("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(idLabel, gbc);

        idField = new TextField(user.getPersonID());
        idField.setEditable(false);  
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        firstNameLabel = new Label("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(firstNameLabel, gbc);

        firstNameField = new TextField();
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        lastNameLabel = new Label("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lastNameLabel, gbc);

        lastNameField = new TextField();
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        emailLabel = new Label("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(emailLabel, gbc);

        emailField = new TextField();
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        phoneLabel = new Label("Phone:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(phoneLabel, gbc);

        phoneField = new TextField();
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        passwordLabel = new Label("Password:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(passwordLabel, gbc);

        passwordField = new TextField();  
        passwordField.setEchoChar('*'); //  hide the input characters
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Only for trainers
        specialtyLabel = new Label("Specialty:");
        specialtyField = new TextField();
        if (isTrainer(user)) {
            gbc.gridx = 0;
            gbc.gridy = 7;
            formPanel.add(specialtyLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(specialtyField, gbc);
        }

        // Only for trainers 
        if (isTrainer(user)) {
        	setSize(500, 570);
            personalTrainerCheckbox = new Checkbox("Personal Trainer");
            personalTrainerCheckbox.setState(((Trainer) user).isPersonalTrainer());
            gbc.gridx = 0;
            gbc.gridy = 8;
            formPanel.add(personalTrainerCheckbox, gbc);
        }

        // Only for members 
        joiningDateLabel = new Label("Joining Date:");
        joiningDateField = new TextField();
        joiningDateField.setEditable(false);
        if (isMember(user)) {
            gbc.gridx = 0;
            gbc.gridy = 9; 
            formPanel.add(joiningDateLabel, gbc);

            gbc.gridx = 1;
            formPanel.add(joiningDateField, gbc);
        }

        initializeFields(user);

        errorLabel = new Label("");
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 15;
        formPanel.add(errorLabel, gbc);

        updateButton = new Button("Update");

        Panel buttonPanel = new Panel();
        buttonPanel.add(updateButton);  

        gbc.gridwidth = 2; 
        gbc.gridx = 0;
        gbc.gridy = 11;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (user instanceof Member)
                    new MemberPage((Member) user);
                else if (user instanceof Trainer)
                    new TrainerPage((Trainer) user);
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateUserDetails()) {
                	new Notification(user.getFirstName() + " " + user.getLastName() + " your details were updated successfully");
                	if (user instanceof Member)
                        new MemberPage((Member) user);
                    else if (user instanceof Trainer)
                        new TrainerPage((Trainer) user);
                    dispose();
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private static String getTitleForUser(Person user) {
        if (user instanceof Member)
            return "Update Member Information";
        else 
            return "Update Trainer Information";
    }

    private boolean isMember(Person user) {
        return user instanceof Member;
    }

    private boolean isTrainer(Person user) {
        return user instanceof Trainer;
    }

    private void initializeFields(Person user) {
        if (isMember(user)) {
            Member member = (Member) user;
            firstNameField.setText(member.getFirstName());
            lastNameField.setText(member.getLastName());
            emailField.setText(member.getEmail());
            phoneField.setText(member.getPhone());
            joiningDateField.setText(member.getJoiningDate().get(GregorianCalendar.DAY_OF_MONTH) + "/"
                    + member.getJoiningDate().get(GregorianCalendar.MONTH) + "/"
                    + member.getJoiningDate().get(GregorianCalendar.YEAR));
        } else if (isTrainer(user)) {
            Trainer trainer = (Trainer) user;
            firstNameField.setText(trainer.getFirstName());
            lastNameField.setText(trainer.getLastName());
            emailField.setText(trainer.getEmail());
            phoneField.setText(trainer.getPhone());
            specialtyField.setText(trainer.getSpecialization());
        }
    }

    private boolean updateUserDetails() {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = passwordField.getText();
            if (isMember(userObject)) {
                Member member = (Member) userObject;
                member.setFirstName(firstName);
                member.setLastName(lastName);
                member.setEmail(email);
                member.setPhone(phone);
                if (!password.isEmpty())
                    member.setPassword(password); 
            } else if (isTrainer(userObject)) {
                Trainer trainer = (Trainer) userObject;
                trainer.setFirstName(firstName);
                trainer.setLastName(lastName);
                trainer.setEmail(email);
                trainer.setPhone(phone);
                trainer.setSpecialization(specialtyField.getText());
                trainer.setIsPersonalTrainer(personalTrainerCheckbox.getState());
                if (!password.isEmpty()) 
                    trainer.setPassword(password);  
            }
            errorLabel.setText("");
            return true;
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
            revalidate();
            return false;
        }
    }
}
