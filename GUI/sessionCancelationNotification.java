package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.DataBase;
import Gym.Management;
import Gym.TrainingSession;

public class sessionCancelationNotification extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label messageLabel, dateTimeLabel, locationLabel;
    private Button cancelButton;

    public sessionCancelationNotification(String sessionID) {
        super("Session Registration");
        TrainingSession session = findSession(sessionID);
        
        setLayout(new BorderLayout());
        Panel contentPanel = new Panel(new BorderLayout(10, 10));
        
        messageLabel = new Label("You have chosen Training session " + session.getName() + ", do you want to cancel ?", Label.CENTER);
        contentPanel.add(messageLabel, BorderLayout.NORTH);
        
        Panel labelsPanel = new Panel(new GridLayout(2, 1, 0, 5));
        dateTimeLabel = new Label("Start on : " + session.getDateTime().getTime(), Label.CENTER);
        locationLabel = new Label("At : " + session.getLocation(), Label.CENTER);
        labelsPanel.add(dateTimeLabel);
        labelsPanel.add(locationLabel);
        contentPanel.add(labelsPanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        cancelButton = new Button("Yes");
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Management.cancelSession(sessionID);
                dispose();
            }
        });
        cancelButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	Management.cancelSession(sessionID);
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