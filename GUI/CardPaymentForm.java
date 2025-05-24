package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

import Gym.Member;
import Gym.Membership;
import Gym.PaymentSystem;
import Gym.Tests;
import Gym.Trainer;

public class CardPaymentForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, cardNumberLabel, expiryDateLabel, cvvLabel, errorLabel;
    private TextField cardNumberField, cvvField;
    private Choice monthChoice, yearChoice;
    private Button submitButton, backButton;
    private boolean isPaymentSuccessful = false;

    public CardPaymentForm(Object obj, Membership membership) {
    	super("Payment Form");
        setSize(380, 260);
        setLayout(new BorderLayout());

        backButton = new Button("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PaymentSystem.makePayment(obj, membership);
                dispose();
            }
        });
        Panel backPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);
        add(backPanel, BorderLayout.NORTH);

        Panel formPanel = new Panel(new GridLayout(4, 1, 10, 10));
        titleLabel = new Label("Credit Card Payment", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(titleLabel);

        Panel cardPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        cardNumberLabel = new Label("Card Number:");
        cardNumberField = new TextField(20);
        cardPanel.add(cardNumberLabel);
        cardPanel.add(cardNumberField);
        formPanel.add(cardPanel);

        Panel dateAndCvvPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        expiryDateLabel = new Label("Expiry Date:");
        monthChoice = new Choice();
        for (int i = 1; i <= 12; i++)
            monthChoice.add(i < 10 ? "0" + i : String.valueOf(i));
        yearChoice = new Choice();
        for (int i = 2025; i <= 2035; i++) {
            yearChoice.add(String.valueOf(i));
        }
        cvvLabel = new Label("CVV:");
        cvvField = new TextField(3);
        dateAndCvvPanel.add(expiryDateLabel);
        dateAndCvvPanel.add(monthChoice);
        dateAndCvvPanel.add(yearChoice);
        dateAndCvvPanel.add(cvvLabel);
        dateAndCvvPanel.add(cvvField);
        formPanel.add(dateAndCvvPanel);

        errorLabel = new Label();
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);
        formPanel.add(errorLabel);
        add(formPanel, BorderLayout.CENTER);

        submitButton = new Button("Submit Payment");
        submitButton.setPreferredSize(new Dimension(360, 30));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Tests.checkCardNumber(cardNumberField.getText());
                    new GregorianCalendar(Integer.parseInt(yearChoice.getSelectedItem()), Integer.parseInt(monthChoice.getSelectedItem()) - 1, 1);
                    Tests.checkCVV(cvvField.getText());
                    isPaymentSuccessful = true;
                    ((Member)obj).setMembership(membership);
                    if (obj instanceof Member)
                    	new MemberPage((Member) obj);
                    else if (obj instanceof Trainer)
                    	new TrainerPage((Trainer) obj);
                    dispose();
                } catch (Exception e2) {
                    errorLabel.setText(e2.getMessage());
                }
            }
        });
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        WindowManager.applyDefaultCloseAction(this);
    }

    public boolean getIsPaymentSuccessful() {
        return isPaymentSuccessful;
    }
}
