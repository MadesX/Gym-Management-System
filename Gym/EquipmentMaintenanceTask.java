package Gym;

import java.util.Random;
import GUI.EquipmentBreakNotification;
import GUI.Notification;

public class EquipmentMaintenanceTask implements Runnable
{
    @Override
    public void run() {
    	String brokenID = "";
        new Notification("Starting maintenance");
        for (Equipment equip : DataBase.getEquipment()) {
            checkEquipmentStatus(equip);
            if (!equip.getIsWorking()) {      	
                brokenID = equip.getEquipmentID();
                break;
            }
        }
        try {
        	Thread.sleep(30000);	// 30 secs
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        if (brokenID.length() != 0)
        	new EquipmentBreakNotification(brokenID);
        else
        	new Notification("Maintenance task completed and every Equipment is functioning properly");
    }
    
    /** randomly brake Equipment with 20% chance using Random */
    private void checkEquipmentStatus(Equipment equipment) {
        Random random = new Random();
        int chance = random.nextInt(10) + 1;   
        if (chance <= 2)	// 20% chance failure
        	equipment.setIsWorking(false);
    }
}