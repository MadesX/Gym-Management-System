package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.Membership;
import Gym.PaymentSystem;

public class PaymentSystemPage extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, payLabel;
    private Button creditCardButton, cashButton;
    
    public PaymentSystemPage(Object obj, double amount, Membership membership) {
    	super("Payment System");
        setSize(300, 270);
        setLayout(new BorderLayout());

        titleLabel = new Label("Select Payment Method", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        payLabel = new Label("You need to pay " + String.format("%.2f", amount) + " ILS", Label.CENTER);
        payLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        buttonPanel.add(payLabel);
        creditCardButton = new Button("Credit Card");
        creditCardButton.setPreferredSize(new Dimension(200, 40));
        creditCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PaymentSystem.handleCreditPayment(obj, membership);
                dispose();
            }
        });
        cashButton = new Button("Cash");
        cashButton.setPreferredSize(new Dimension(200, 40));
        cashButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PaymentSystem.handleCashPayment(obj, membership);
                dispose();
            }
        });
        buttonPanel.add(creditCardButton);
        buttonPanel.add(cashButton);
        add(buttonPanel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }
}
