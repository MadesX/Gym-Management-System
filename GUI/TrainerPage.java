package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.DataBase;
import Gym.Trainer;

public class TrainerPage extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label welcomeLabel;
    private Button viewDetailsButton, gymScheduleButton, myScheduleButton, updateDetailsButton, logoutButton;
	private Trainer trainer;

    public TrainerPage(Trainer trainer) {
        super("Trainer Dashboard");
        this.trainer = trainer;
        setSize(500, 500);
        setLocationRelativeTo(null);
        setBackground(Color.LIGHT_GRAY);
        setMenuBar(new Menus());
        setLayout(new GridLayout(6, 1, 10, 10));
        
        welcomeLabel = new Label("Welcome, Dear Trainer", Label.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 21));
        welcomeLabel.setForeground(Color.BLUE);
        add(welcomeLabel);

        Panel viewTrainerPanel = new Panel();
        viewTrainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewDetailsButton = new Button("View My Details");
        viewDetailsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        viewDetailsButton.setPreferredSize(new Dimension(200, 50));
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTrainerDetails();
            }
        });
        viewTrainerPanel.add(viewDetailsButton);
        add(viewTrainerPanel);

        Panel schedulePanel = new Panel();
        schedulePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gymScheduleButton = new Button("View Weekly Gym Schedule");
        gymScheduleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gymScheduleButton.setPreferredSize(new Dimension(200, 50));
        gymScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GymSchedule(trainer);
                dispose();
            }
        });
        schedulePanel.add(gymScheduleButton);
        add(schedulePanel);

        Panel trainingSchedulePanel = new Panel();
        trainingSchedulePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        myScheduleButton = new Button("View My Training Schedule");
        myScheduleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        myScheduleButton.setPreferredSize(new Dimension(200, 50));
        myScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TrainingSessionDisplay(trainer, trainer.getSessions(), false);
                dispose();
            }
        });
        trainingSchedulePanel.add(myScheduleButton);
        add(trainingSchedulePanel);

        Panel updateDetailsPanel = new Panel();
        updateDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        updateDetailsButton = new Button("Update My Details");
        updateDetailsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateDetailsButton.setPreferredSize(new Dimension(200, 50));
        updateDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateForm(trainer);
                dispose();
            }
        });
        updateDetailsPanel.add(updateDetailsButton);
        add(updateDetailsPanel);

        Panel logoutPanel = new Panel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoutButton = new Button("Log Out");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutButton.setPreferredSize(new Dimension(200, 50));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DataBase.saveDataToFile();
            	WindowManager.resetSystem();
            }
        });
        logoutPanel.add(logoutButton);
        add(logoutPanel);

        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }

    private void showTrainerDetails() {
        Dialog detailsDialog = new Dialog(this, "Trainer Details", true);
        detailsDialog.setSize(400, 300);
        detailsDialog.setLocationRelativeTo(this);

        Panel detailsPanel = new Panel(new GridLayout(6, 2, 10, 10));
        detailsPanel.add(new Label("ID:"));
        detailsPanel.add(new Label(trainer.getPersonID()));
        detailsPanel.add(new Label("Name:"));
        detailsPanel.add(new Label(trainer.getFirstName() + " " + trainer.getLastName()));
        detailsPanel.add(new Label("Email:"));
        detailsPanel.add(new Label(trainer.getEmail()));
        detailsPanel.add(new Label("Phone:"));
        detailsPanel.add(new Label(trainer.getPhone()));
        detailsPanel.add(new Label("Specialization:"));
        detailsPanel.add(new Label(trainer.getSpecialization()));
        detailsPanel.add(new Label("Personal Trainer:"));
        detailsPanel.add(new Label(trainer.isPersonalTrainer() ? "Yes" : "No"));

        detailsDialog.add(detailsPanel, BorderLayout.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsDialog.dispose();
            }
        });
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
