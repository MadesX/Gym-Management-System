package Gym;

import java.util.Vector;

public class EquipmentManagementSystem 
{
	private static Vector<Equipment> equipment = DataBase.getEquipment();
	private static Management manager = new Management();
	
	/** start a new thread EquipmentMaintenanceTask an insert it into the vector*/
	public static void startEquipmentMaintenance(Vector<Thread> threads) {
		if (equipment.size() > 0) {
        	Thread thread = new Thread(new EquipmentMaintenanceTask(), "Thread-" + (threads.size() + 1));
        	thread.start();
        	threads.add(thread);
        } 
	}
	
	/** purchase new equipment through the manager */
    public static void purchaseEquipment() {
        manager.purchaseEquipment();
    }
	
}
