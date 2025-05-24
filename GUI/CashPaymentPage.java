package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.Member;
import Gym.Membership;
import Gym.PaymentSystem;
import Gym.Trainer;

public class CashPaymentPage extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label infoLabel, goToFrontLabel;
    private Button payButton, backButton;
    private boolean isPaymentSuccessful = false;

    public CashPaymentPage(Object obj, Membership membership) {
    	super("Cash Payment");
        setSize(400, 250);
        setLayout(new BorderLayout());

        backButton = new Button("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PaymentSystem.makePayment(obj, membership);
                dispose();
            }
        });
        Panel backPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);
        add(backPanel, BorderLayout.NORTH);

        Panel centerPanel = new Panel(new GridLayout(3, 1, 10, 10));
        infoLabel = new Label("Cash Payment", Label.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(infoLabel);

        goToFrontLabel = new Label("Visit front desk to complete your payment", Label.CENTER);
        goToFrontLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        centerPanel.add(goToFrontLabel);

        payButton = new Button("Pay");
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isPaymentSuccessful = true;
                ((Member)obj).setMembership(membership);
                if (obj instanceof Member)
                    new MemberPage((Member) obj);
                else if (obj instanceof Trainer)
                    new TrainerPage((Trainer) obj);
                dispose();
            }
        });
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(payButton);
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }

    public boolean getIsPaymentSuccessful() {
        return isPaymentSuccessful;
    }
}
