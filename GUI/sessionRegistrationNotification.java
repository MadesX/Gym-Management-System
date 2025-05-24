package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.DataBase;
import Gym.Member;
import Gym.RegistrationSystem;
import Gym.TrainingSession;

public class sessionRegistrationNotification extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label messageLabel, dateTimeLabel, locationLabel;
    private Button registerButton;

    public sessionRegistrationNotification(Member member, String sessionID) {
        super("Session Registration");
        TrainingSession session = findSession(sessionID);
        
        setLayout(new BorderLayout());
        Panel contentPanel = new Panel(new BorderLayout(10, 10));
        
        messageLabel = new Label("You have chosen Training session " + session.getName() + ", do you want to register ?", Label.CENTER);
        contentPanel.add(messageLabel, BorderLayout.NORTH);
        
        Panel labelsPanel = new Panel(new GridLayout(2, 1, 0, 5));
        dateTimeLabel = new Label("Start on : " + session.getDateTime().getTime(), Label.CENTER);
        locationLabel = new Label("At : " + session.getLocation(), Label.CENTER);
        labelsPanel.add(dateTimeLabel);
        labelsPanel.add(locationLabel);
        contentPanel.add(labelsPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new Button("Register");
        buttonPanel.add(registerButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Notification(RegistrationSystem.register(member, session));
                dispose();
            }
        });
        registerButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });

        add(new Label(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
        
        FontMetrics fm = getFontMetrics(messageLabel.getFont());
        int messageWidth = fm.stringWidth(messageLabel.getText());
        int windowWidth = messageWidth + 50; // Add margin to the width
        setSize(Math.max(windowWidth, 280), 200);
        setResizable(false);      
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private TrainingSession findSession(String sessionID) {
    	for (TrainingSession session : DataBase.getSessions())
        	if (session.getSessionID().equals(sessionID))
        		return session;
    	return null;
    }
}

