package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.Member;
import Gym.Membership;
import Gym.PaymentSystem;
import Gym.Trainer;

public class MembershipForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label titleLabel, typeLabel, discountLabel, errorLabel;
    private Choice typeChoice, discountChoice;
    private Button sendButton, backButton;
    private Membership membership;

    public MembershipForm(Object obj) {
        super("Membership Registration Form");
        setSize(510, 380);
        setMenuBar(new Menus());
        setLayout(new BorderLayout(5, 5));

        Panel northPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        backButton = new Button("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (obj instanceof Member)
                    new MemberPage((Member) obj);
                else if (obj instanceof Trainer)
                    new TrainerPage((Trainer) obj);
                dispose();
            }
        });
        northPanel.add(backButton);
        add(northPanel, BorderLayout.NORTH);

        Panel mainPanel = new Panel(new GridLayout(7, 1, 5, 5));
        titleLabel = new Label("Membership Selection", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        typeLabel = new Label("Select Membership Duration:");
        discountLabel = new Label("Select Discount Type:");

        errorLabel = new Label("", Label.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);

        typeChoice = new Choice();
        typeChoice.add("Daily");
        typeChoice.add("Monthly");
        typeChoice.add("Yearly");

        discountChoice = new Choice();
        discountChoice.add("None");
        discountChoice.add("Elderly");
        discountChoice.add("Student");
        discountChoice.add("Soldier");
        discountChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (discountChoice.getSelectedItem() != "None")
                    errorLabel.setText("Please visit the front desk with the relevant documents for " + discountChoice.getSelectedItem() + " discount approval.");
                else
                    errorLabel.setText("");
            }
        });

        Panel typePanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        Panel discountPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(typeChoice);
        discountPanel.add(discountChoice);

        sendButton = new Button("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    membership = new Membership(typeChoice.getSelectedItem(), discountChoice.getSelectedItem());
                    dispose();
                    new PaymentSystem((Member)obj, membership);
                    PaymentSystem.makePayment(obj, membership);
                } catch (Exception e2) {
                    errorLabel.setText(e2.getMessage());
                }
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(typeLabel);
        mainPanel.add(typePanel);
        mainPanel.add(discountLabel);
        mainPanel.add(discountPanel);
        mainPanel.add(sendButton);
        mainPanel.add(errorLabel);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        WindowManager.applyDefaultCloseAction(this);
    }

    public Membership getMembership() {
        return membership;
    }
}
