package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

import Gym.Constants;
import Gym.Constants.Locations;
import Gym.DataBase;
import Gym.Equipment;

public class NewEquipmentForm extends Frame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField equipmentIDField, nameField;
    private Choice locationChoice;
    private Label titleLabel, errorLabel;
    private Button submitButton, cancelButton;

    public NewEquipmentForm() {
        super("New Equipment Purchase");
        setSize(400, 350);
        setLayout(new BorderLayout());
        setMenuBar(new Menus());

        Panel centerPanel = new Panel(new GridBagLayout());
        add(centerPanel, BorderLayout.CENTER);

        titleLabel = new Label("New Equipment Entry", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(titleLabel, gbc);

        Label fillerLabel = new Label("");
        gbc.gridy = 1;
        centerPanel.add(fillerLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        centerPanel.add(new Label("Equipment ID:"), gbc);
        gbc.gridx = 1;
        equipmentIDField = new TextField(20);
        centerPanel.add(equipmentIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(new Label("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new TextField(20);
        centerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(new Label("Location:"), gbc);
        gbc.gridx = 1;
        locationChoice = new Choice();
        for (Locations location : Constants.Locations.values()) {
            locationChoice.add(location.name());
        }
        centerPanel.add(locationChoice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        errorLabel = new Label("");
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(Color.RED);
        centerPanel.add(errorLabel, gbc);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String equipmentID = equipmentIDField.getText();
                String name = nameField.getText();
                String location = locationChoice.getSelectedItem();

                try {
                    Equipment newEquipment = new Equipment(equipmentID, name, true, new GregorianCalendar(), location);
                    DataBase.getEquipment().add(newEquipment);
                    new Notification("Equipment " + nameField.getText() + " was purchased and installed in " + location);
                    new ManagementPage();
                    dispose();
                } catch (IllegalArgumentException ex) {
                    errorLabel.setText("Invalid input: " + ex.getMessage());
                    revalidate(); 
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagementPage();
                dispose();
            }
        });
        setLocationRelativeTo(null); 
        setVisible(true);
        
        WindowManager.applyDefaultCloseAction(this);
    }
}
