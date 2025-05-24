package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import Gym.DataBase;
import Gym.EquipmentManagementSystem;
import Gym.Management;

public class ManagementPage extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label welcomeLabel;
    private Button createNewSessionButton, cancelSessionButton, addTrainerButton, removeTrainerButton, addMemberButton, removeMemberButton;
    private Button cancelMembershipButton, maintenanceButton, purchaseEquipmentButton, gymScheduleButton, viewSessionsButton, logoutButton;
    private Vector<Thread> threads = new Vector<Thread>();
    
    public ManagementPage() {
    	super("Management Dashboard");
        setSize(500, 600);
        setMenuBar(new Menus());
        setBackground(Color.LIGHT_GRAY);

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(13, 1, 10, 10));

        welcomeLabel = new Label("Welcome, Dear Manager", Label.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 21));
        welcomeLabel.setForeground(Color.BLUE);
        mainPanel.add(welcomeLabel);

        Panel addClassesPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        createNewSessionButton = new Button("Create New Training Session");
        createNewSessionButton.setFont(new Font("Arial", Font.PLAIN, 14));
        createNewSessionButton.setPreferredSize(new Dimension(220, 50));
        addClassesPanel.add(createNewSessionButton);
        mainPanel.add(addClassesPanel);

        createNewSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Management.createSession();
            }
        });

        Panel removeClassesPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        cancelSessionButton = new Button("Cancel Training Session");
        cancelSessionButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelSessionButton.setPreferredSize(new Dimension(220, 50));
        removeClassesPanel.add(cancelSessionButton);
        mainPanel.add(removeClassesPanel);
        cancelSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainingSessionDisplay(new Management(), DataBase.getSessions(), true);
                dispose();
            }
        });

        Panel addTrainerPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        addTrainerButton = new Button("Create New Trainer");
        addTrainerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addTrainerButton.setPreferredSize(new Dimension(220, 50));
        addTrainerPanel.add(addTrainerButton);
        mainPanel.add(addTrainerPanel);
        addTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management.createTrainer();
                dispose();
            }
        });

        Panel removeTrainerPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        removeTrainerButton = new Button("Remove Trainer");
        removeTrainerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        removeTrainerButton.setPreferredSize(new Dimension(220, 50));
        removeTrainerPanel.add(removeTrainerButton);
        mainPanel.add(removeTrainerPanel);
        removeTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management.deleteTrainer();
            }
        });

        Panel addMemberPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        addMemberButton = new Button("Create New Member");
        addMemberButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addMemberButton.setPreferredSize(new Dimension(220, 50));
        addMemberPanel.add(addMemberButton);
        mainPanel.add(addMemberPanel);
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management.createMember();
                dispose();
            }
        });

        Panel removeMemberPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        removeMemberButton = new Button("Remove Member");
        removeMemberButton.setFont(new Font("Arial", Font.PLAIN, 14));
        removeMemberButton.setPreferredSize(new Dimension(220, 50));
        removeMemberPanel.add(removeMemberButton);
        mainPanel.add(removeMemberPanel);
        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management.deleteMember();
            }
        });

        Panel cancelMembershipPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        cancelMembershipButton = new Button("Cancel Member's Membership");
        cancelMembershipButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelMembershipButton.setPreferredSize(new Dimension(220, 50));
        cancelMembershipPanel.add(cancelMembershipButton);
        mainPanel.add(cancelMembershipPanel);
        cancelMembershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management.cancelMembership();
            }
        });

        Panel maintenancePanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        maintenanceButton = new Button("Start Equipment Maintenance");
        maintenanceButton.setFont(new Font("Arial", Font.PLAIN, 14));
        maintenanceButton.setPreferredSize(new Dimension(220, 50));
        maintenancePanel.add(maintenanceButton);
        mainPanel.add(maintenancePanel);
        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipmentManagementSystem.startEquipmentMaintenance(threads);
            }
        });

        Panel purchaseEquipmentPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        purchaseEquipmentButton = new Button("Purchase Equipment");
        purchaseEquipmentButton.setFont(new Font("Arial", Font.PLAIN, 14));
        purchaseEquipmentButton.setPreferredSize(new Dimension(220, 50));
        purchaseEquipmentPanel.add(purchaseEquipmentButton);
        mainPanel.add(purchaseEquipmentPanel);
        purchaseEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                EquipmentManagementSystem.purchaseEquipment();
            }
        });

        Panel showClassPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        gymScheduleButton = new Button("View Weekly Gym Schedule");
        gymScheduleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gymScheduleButton.setPreferredSize(new Dimension(220, 50));
        showClassPanel.add(gymScheduleButton);
        mainPanel.add(showClassPanel);
        gymScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GymSchedule(this);
                dispose();
            }
        });

        Panel viewSessionsPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        viewSessionsButton = new Button("View All Sessions");
        viewSessionsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        viewSessionsButton.setPreferredSize(new Dimension(220, 50));
        viewSessionsPanel.add(viewSessionsButton);
        mainPanel.add(viewSessionsPanel);
        viewSessionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TrainingSessionDisplay(new Management(), DataBase.getSessions(), false);
                dispose();
            }
        });

        Panel logoutPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        logoutButton = new Button("Log Out");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutButton.setPreferredSize(new Dimension(220, 50));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DataBase.saveDataToFile();
            	WindowManager.resetSystem();
            }
        });
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(mainPanel);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }
}
