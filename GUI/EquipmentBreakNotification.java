package GUI;

import java.awt.*;
import java.awt.event.*;

import Gym.DataBase;
import Gym.Equipment;

public class EquipmentBreakNotification extends Frame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label messageLabel;
    private Button removeButton, repairButton;

    public EquipmentBreakNotification(String brokenID) {
        super("Equipment Notification");
        setLayout(new BorderLayout());
        Panel contentPanel = new Panel(new BorderLayout(10, 10));

        messageLabel = new Label("Equipment " + brokenID + " broke,  Should it be repaired or removed ?", Label.CENTER);
        contentPanel.add(messageLabel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        removeButton = new Button("Remove");
        repairButton = new Button("Repair");
        buttonPanel.add(removeButton);
        buttonPanel.add(repairButton);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (Equipment equip : DataBase.getEquipment())
            		if (equip.getEquipmentID().equals(brokenID)) {
            			DataBase.getEquipment().remove(equip);
            			break;
            		}
                dispose();
            }
        });

        repairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                repairEquipment(brokenID);
            }
        });

        add(new Label(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);

        FontMetrics fm = getFontMetrics(messageLabel.getFont());
        int messageWidth = fm.stringWidth("Equipment " + brokenID + " broke,  Should it be repaired or removed ?");
        int windowWidth = messageWidth + 100; 
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
    
    /**repair Equipment received by ID*/
    private void repairEquipment(String equipmentID) {
        try {
			Thread.sleep(10000);	 // Wait for 10 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        for (Equipment equipment : DataBase.getEquipment()) {
        	if (equipment.getEquipmentID().equals(equipmentID)) {
        		equipment.setIsWorking(true);
                new Notification("Equipment " + equipment.getEquipmentID() + " fixed and operational");
                break;
        	}
        }  
    }
}