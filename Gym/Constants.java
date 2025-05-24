package Gym;

public class Constants 
{
    public static final String GYM_NAME = "The Mandalore Warriors Gym";
    public static final int OPENING_HOUR = 6;
    public static final int CLOSING_HOUR = 22;
    public static final double PRICE = 100;
    
    public enum Locations { 
        CARDIO_AREA, 
        WEIGHT_ROOM, 
        YOGA_STUDIO, 
        SPINNING_ROOM, 
        COMBAT_TRAINING_AREA,  
        NINJA_TRAINING_AREA,  
        MARTIAL_ARTS_DOJO,    
        FITNESS_HALL         
    }
    public enum Types {YEARLY, MONTHLY, DAILY}
    public enum Discounts {
        NONE(0),
        SOLDIER(25),
        STUDENT(15),
        ELDERLY(20);

        private final int number;

        Discounts(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}
