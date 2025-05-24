package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.*;

public class LoginPage extends Frame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, idLabel, passwordLabel, errorLabel;
    private TextField idField, passwordField;
    private Button loginButton;

    public LoginPage() {
    	super("Login Page");
    	setSize(300, 200);
        setLayout(new BorderLayout(10, 10));

        titleLabel = new Label("Login Page", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        Panel contentPanel = new Panel(new GridLayout(2, 2, 5, 5)); 
        idLabel = new Label("ID:");
        idField = new TextField();
        contentPanel.add(idLabel);
        contentPanel.add(idField);

        passwordLabel = new Label("Password:");
        passwordField = new TextField();
        passwordField.setEchoChar('*');
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        add(contentPanel, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout(5, 5));
        loginButton = new Button("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	 action();
            }
        });
        bottomPanel.add(loginButton, BorderLayout.NORTH);

        errorLabel = new Label("", Label.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);
        bottomPanel.add(errorLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
 
        setLocationRelativeTo(null);
        setVisible(true); 
        
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {	
            @Override
            public void eventDispatched(AWTEvent event) {	// act on Enter Key pressed
                if (event instanceof KeyEvent) {
                    KeyEvent keyEvent = (KeyEvent) event;
                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED && keyEvent.getKeyCode() == KeyEvent.VK_ENTER && isVisible())
                        action();
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
        
        WindowManager.applyDefaultCloseAction(this);
    }

    private void action()
    {
    	try {
			Tests.checkNotEmpty(idField.getText());
			Tests.checkNotEmpty(passwordField.getText());
			
			if (idField.getText().equals("admin") && passwordField.getText().equals("admin")) {
				dispose();
				new ManagementPage();
				return;
			}
			else {
				for (Person person : DataBase.getPersons()) {
                    if (person.getPersonID().equals(idField.getText()) && person.confirmPassword(passwordField.getText())) {
                    	if (person instanceof Member) {
                			dispose();
                			new MemberPage((Member)person);
                			return;
                		}
                		else if (person instanceof Trainer) {
                			dispose();
                			new TrainerPage((Trainer)person);               		
                			return;
                		}
                    }
				}
			}
            errorLabel.setText("Incorrect ID or Password");
		} catch (Exception e2) {
			errorLabel.setText(e2.getMessage());
		}    
    }
}