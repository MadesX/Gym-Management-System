package Gym;

import java.util.GregorianCalendar;

public class Tests 
{
	/** method to check if a persons ID is valid */
	public static void checkPersonID(String id)
	{
        if (id.length() <= 0 || id.length() != 9) 
            throw new IllegalArgumentException("Error, invalid Id length");
        for (char c : id.toCharArray()) 
            if (c < '0' || c > '9') 
                throw new IllegalArgumentException("Error! Invalid Id, can only contain numbers");
    }
	
	/** method to validate an objects ID */
	public static void checkID(String id)
	{
        if (id.length() <= 0) 
            throw new IllegalArgumentException("Error, invalid Id length");
        
        for (char c : id.toCharArray()) 
            if (c < '0' || (c > '9' && c < 'A') || c > 'Z') 
                throw new IllegalArgumentException("Error! Invalid Id, can only contain numbers and capital letters");
    }
	
	/** method to validate a name (non-empty and alphabetic) */
	public static void checkName(String name)
	{
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Name, cannot be empty");

        for (char c : name.toCharArray()) {
            if (c < 'A' || (c > 'Z' && c < 'a') || c > 'z' && (c < 0x0590 || c > 0x05FF))
                throw new IllegalArgumentException("Error! Invalid Name, can only contain Hebrew or English characters");
        }
    }
	
	/** method to validate a phone number */
	public static void checkPhone(String phone)
	{
        if (phone.length() != 10) 
            throw new IllegalArgumentException("Error, invalid phone number length");
        
        for (char c : phone.toCharArray())
            if (c < '0' || c > '9') 
                throw new IllegalArgumentException("Error! Invalid phone number, can only contain numbers");
        
        if (!phone.startsWith("0"))
        	throw new IllegalArgumentException("Error, phone number must start with '0'");
    }
	
	/** method to validate an email address */
	public static void checkEmail(String email) {
        if (email.isEmpty())
        	throw new IllegalArgumentException("Error, invalid email address length");
        
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";    
        if (!email.matches(emailRegex))
        	throw new IllegalArgumentException("Error! Invalid email address");
    }
	
	/** method to validate a string */
	public static void checkNotEmpty(String str) {
        if (str == null || str.isEmpty())
        	throw new IllegalArgumentException("Error, cannot be empty");
    }
	
	/** method to validate an object */
	public static void checkNotNull(Object obj) {
        if (obj == null)
        	throw new IllegalArgumentException("Error, cannot be null");
    }
	
	/** method to validate an integer as non-negative */
	public static void checkInt(int num) {
        if (num < 0)
        	throw new IllegalArgumentException("Error, cannot be negative");
    }
	
	/** method to validate a double as non-negative */
	public static void checkDouble(double num) {
        if (num < 0)
        	throw new IllegalArgumentException("Error, cannot be negative");
    }
	
	/** method to validate password */
	public static void checkPassword(String name) {
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Password, cannot be empty");
		
		if (name.length() < 3)
            throw new IllegalArgumentException("Error! Invalid Password, must be at least 3 characters");

        for (char c : name.toCharArray()) {
        	if (c == ' ')
                throw new IllegalArgumentException("Error! Invalid Password, cannot contain spaces");
        }
    }
	
	/** method to validate a day of the week */
	public static void checkDay(String day) 
    {
		String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Unknown"};
    	boolean flag = false;
        for (String d : dayNames) {
            if (d.equalsIgnoreCase(day)) {
                flag = true;
                break;
            }
        }
        if (!flag)
        	throw new IllegalArgumentException("Error! Invalid day value entered.");
    }
	
	/** checks if the credit card number is valid (16 digits) */            
    public static void checkCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) 
            throw new IllegalArgumentException("Card number must be 16 digits");
        for (char c : cardNumber.toCharArray())
            if (c < '0' || c > '9') 
                throw new IllegalArgumentException("Error! Invalid card number, can only contain numbers");
    }
    
    /** checks if the credit cards CVV is valid (3 digits) */            
    public static void checkCVV(String cvv) {
        checkNotEmpty(cvv);
        if (cvv.length() != 3)
        	throw new IllegalArgumentException("CVV must be 3 digits"); 
    }
    
    /**method to validate a GregorianCalendar date (non-null and not in the past)*/
    public static void checkDate(GregorianCalendar date) {
        if (date == null) 
            throw new IllegalArgumentException("Error! Date cannot be null.");
        GregorianCalendar today = new GregorianCalendar();
        if (date.before(today))
            throw new IllegalArgumentException("Error! The date cannot be in the past.");
    }
}