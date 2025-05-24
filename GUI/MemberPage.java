package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

import Gym.DataBase;
import Gym.Member;

public class MemberPage extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label welcomeLabel, alertLabel;
    private Button viewDetailsButton, gymScheduleButton, viewScheduleButton, registerSessionButton, purchaseMembershipButton, updateDetailsButton, logoutButton;
    private Member member;

    @SuppressWarnings("deprecation")
	public MemberPage(Member member) {
        super("Member Dashboard");
        this.member = member;
        setSize(500, 600);      
        setBackground(Color.LIGHT_GRAY);
        setMenuBar(new Menus());
        setLayout(new BorderLayout());

        alertLabel = new Label("You do not have an active membership", Label.CENTER);
        alertLabel.setFont(new Font("Arial", Font.BOLD, 14));
        alertLabel.setForeground(Color.WHITE);
        alertLabel.setBackground(Color.RED);
        alertLabel.setVisible(false);
        add(alertLabel, BorderLayout.NORTH);     

        Panel mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(8, 1, 10, 10));

        welcomeLabel = new Label("Welcome, Dear Member", Label.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 21));
        welcomeLabel.setForeground(Color.BLUE);
        mainPanel.add(welcomeLabel);

        Panel membershipPanel = new Panel();
        membershipPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewDetailsButton = new Button("View My Details");
        viewDetailsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        viewDetailsButton.setPreferredSize(new Dimension(200, 50));
        membershipPanel.add(viewDetailsButton);
        mainPanel.add(membershipPanel);
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMemberDetails();
            }
        });

        Panel schedulePanel = new Panel();
        schedulePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gymScheduleButton = new Button("View Weekly Gym Schedule");
        gymScheduleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        gymScheduleButton.setPreferredSize(new Dimension(200, 50));
        schedulePanel.add(gymScheduleButton);
        mainPanel.add(schedulePanel);
        gymScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GymSchedule(member);
                dispose();
            }
        });

        Panel viewSchedulePanel = new Panel();
        viewSchedulePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewScheduleButton = new Button("View My Schedule");
        viewScheduleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        viewScheduleButton.setPreferredSize(new Dimension(200, 50));
        viewSchedulePanel.add(viewScheduleButton);
        mainPanel.add(viewSchedulePanel);
        viewScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TrainingSessionDisplay(member, member.getSessions(), false);
                dispose();
            }
        });

        Panel trainingPanel = new Panel();
        trainingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        registerSessionButton = new Button("Register for Training");
        registerSessionButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registerSessionButton.setPreferredSize(new Dimension(200, 50));
        trainingPanel.add(registerSessionButton);
        mainPanel.add(trainingPanel);
        registerSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainingSessionDisplay(member, DataBase.getSessions(), true);
                dispose();
            }
        });

        Panel purchaseMembershipPanel = new Panel(); // Panel for Purchase Membership button
        purchaseMembershipPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        purchaseMembershipButton = new Button("Purchase Membership");
        purchaseMembershipButton.setFont(new Font("Arial", Font.PLAIN, 14));
        purchaseMembershipButton.setPreferredSize(new Dimension(200, 50));
        purchaseMembershipPanel.add(purchaseMembershipButton);
        mainPanel.add(purchaseMembershipPanel);
        purchaseMembershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MembershipForm(member);
                dispose();
            }
        });

        Panel updateDetailsPanel = new Panel();
        updateDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        updateDetailsButton = new Button("Update Your Details");
        updateDetailsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateDetailsButton.setPreferredSize(new Dimension(200, 50));
        updateDetailsPanel.add(updateDetailsButton);
        mainPanel.add(updateDetailsPanel);
        updateDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateForm(member);
                dispose();
            }
        });

        Panel logoutPanel = new Panel();
        logoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoutButton = new Button("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logoutButton.setPreferredSize(new Dimension(200, 50));
        logoutPanel.add(logoutButton);
        mainPanel.add(logoutPanel);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DataBase.saveDataToFile();
            	WindowManager.resetSystem();
            }
        });
        add(mainPanel, BorderLayout.CENTER);    
        setLocationRelativeTo(null);

        // Check membership validity
        if (member.getMembership() == null || !member.getMembership().getEndDate().after(new GregorianCalendar())) {
            alertLabel.setVisible(true);
            registerSessionButton.disable();
        }
        else
        	purchaseMembershipButton.disable();
        	
        setVisible(true);
        WindowManager.applyDefaultCloseAction(this);
    }

    private void showMemberDetails() {
        Dialog detailsDialog = new Dialog(this, "Member Details");
        detailsDialog.setSize(400, 300);
        detailsDialog.setLocationRelativeTo(this);

        Panel detailsPanel = new Panel();
        detailsPanel.setLayout(new GridLayout(7, 2, 10, 10));

        detailsPanel.add(new Label("ID:"));
        detailsPanel.add(new Label(member.getPersonID()));

        detailsPanel.add(new Label("Name:"));
        detailsPanel.add(new Label(member.getFirstName() + " " + member.getLastName()));

        detailsPanel.add(new Label("Email:"));
        detailsPanel.add(new Label(member.getEmail()));

        detailsPanel.add(new Label("Phone:"));
        detailsPanel.add(new Label(member.getPhone()));

        detailsPanel.add(new Label("Join Date:"));
        detailsPanel.add(new Label(member.getJoiningDate().getTime().toString()));

        detailsPanel.add(new Label("Active Membership:"));
        detailsPanel.add(new Label(member.getMembership() == null || !member.getMembership().getEndDate().after(new GregorianCalendar()) ? "No" : "Yes"));

        detailsDialog.add(detailsPanel, BorderLayout.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setPreferredSize(new Dimension(200, 50));
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
