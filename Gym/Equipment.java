package Gym;

import java.util.GregorianCalendar;

public class Equipment {
    private String equipmentID;
    private String name;
    private boolean isWorking;
    private GregorianCalendar lastMaintenanceDate;
    private String location;

    public Equipment(String equipmentID, String name, boolean isWorking, GregorianCalendar lastMaintenanceDate, String location) {
        setEquipmentID(equipmentID);
        setName(name);
        setIsWorking(isWorking);
        setLastMaintenanceDate(lastMaintenanceDate);
        setLocation(location);
    }

    public String getEquipmentID() {
        return equipmentID;
    }
    public void setEquipmentID(String equipmentID) {
    	Tests.checkID(equipmentID);
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
    	Tests.checkNotEmpty(name);
        this.name = name;
    }

    public boolean getIsWorking() {
        return isWorking;
    }
    public void setIsWorking(boolean working) {
        isWorking = working;
    }

    public GregorianCalendar getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }
    public void setLastMaintenanceDate(GregorianCalendar lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
    
    public String getLocation() { 
    	return location; 
    }
    public void setLocation(String location) { 
    	Tests.checkNotEmpty(location);  	
    	try {
            this.location = Constants.Locations.valueOf(location.toUpperCase()).name();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentID=" + equipmentID +
                ", name=" + name +
                ", isWorking=" + isWorking +
                ", lastMaintenanceDate=" + lastMaintenanceDate.get(GregorianCalendar.DAY_OF_MONTH) + "," + lastMaintenanceDate.get(GregorianCalendar.MONTH) + "," + lastMaintenanceDate.get(GregorianCalendar.YEAR) + 
                ", location=" + location +
                '}';
    }
}