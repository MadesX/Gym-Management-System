package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.Constants;

public class HomePage extends Frame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label welcomeLabel;
	private Button loginButton, registrationButton;
	
    public HomePage() {
        super(Constants.GYM_NAME);
        setSize(500, 400);
        setBackground(Color.DARK_GRAY);
        setMenuBar(new Menus());
        
        setLayout(new GridLayout(3, 1, 10, 10));
        
        welcomeLabel = new Label("Welcome to " + Constants.GYM_NAME, Label.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 21));
        welcomeLabel.setForeground(Color.ORANGE);
        add(welcomeLabel);
        
        Panel loginPanel = new Panel();
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButton = new Button("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginPanel.add(loginButton);
        add(loginPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
            }
        });
        
        Panel registrationPanel = new Panel();
        registrationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        registrationButton = new Button("Registration");
        registrationButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registrationButton.setPreferredSize(new Dimension(200, 50));
        registrationPanel.add(registrationButton);
        add(registrationPanel);
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();               
                new RegistrationForm(null, false);
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);

        WindowManager.applyDefaultCloseAction(this);
    }
}
