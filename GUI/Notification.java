package GUI;

import java.awt.*;
import java.awt.event.*;

public class Notification extends Frame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label messageLabel;
	private Button okButton;
	
    public Notification(String message) {
        super("Notification");
        setLayout(new BorderLayout());
        Panel contentPanel = new Panel(new BorderLayout(10, 10));
        
        messageLabel = new Label(message, Label.CENTER);
        contentPanel.add(messageLabel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        okButton = new Button("OK");
        buttonPanel.add(okButton);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        okButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        okButton.addKeyListener(new KeyAdapter() {
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
        int messageWidth = fm.stringWidth(message);
        int windowWidth = messageWidth + 100; // Add margin to the width
        setSize(Math.max(windowWidth, 280), 150);
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
}
