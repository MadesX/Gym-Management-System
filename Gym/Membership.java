package Gym;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Membership 
{
    private String type;
    private GregorianCalendar startDate = new GregorianCalendar();
    private GregorianCalendar endDate = new GregorianCalendar();
    private double price;
    private String discount;
    
    public Membership(String type, String discount) {
    	setType(type);
    	switch (type.toUpperCase()) {
        case "YEARLY":
        	endDate.add(GregorianCalendar.YEAR, 1);
            break;

        case "MONTHLY":
        	endDate.add(GregorianCalendar.MONTH, 1);
            break;
    	}
    	endDate.set(Calendar.HOUR_OF_DAY, 23);
    	endDate.set(Calendar.MINUTE, 59);
    	endDate.set(Calendar.SECOND, 59);
    	endDate.set(Calendar.MILLISECOND, 999);
    	
    	price = Constants.PRICE;
    	setDiscount(discount);
    }
    
    public Membership(String type, GregorianCalendar startDate, GregorianCalendar endDate, String discount) {
    	setType(type);
        setStartDate(startDate);
        setEndDate(endDate);
        setDiscount(discount);
    }
    
    public String getType() { 
    	return type; 
    }
    public void setType(String type) { 
    	Tests.checkNotEmpty(type);
    	try {
    		this.type = Constants.Types.valueOf(type.toUpperCase()).name();
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid type. Allowed values: YEARLY, MONTHLY, DAILY.");
	    }
    }

    public GregorianCalendar getStartDate() { 
    	return startDate; 
    }
    public void setStartDate(GregorianCalendar startDate) { 
    	Tests.checkNotNull(startDate);
    	this.startDate = startDate; 
    }

    public GregorianCalendar getEndDate() { 
    	return endDate; 
    }
    public void setEndDate(GregorianCalendar endDate) { 
    	Tests.checkNotNull(endDate);
    	this.endDate = endDate; 
    }

    public double getPrice() { 
    	return price; 
    }
    public void setPrice(double price) { 
    	Tests.checkDouble(price);
    	this.price = price; 
    }

    public String getDiscount() { 
    	return discount; 
    }
    public void setDiscount(String discount) { 
    	Tests.checkNotEmpty(discount);
    	try {
    		this.discount = Constants.Discounts.valueOf(discount.toUpperCase()).name();
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid type. Allowed values: REGULAR, SOLDIER, STUDENT, ELDERLY, SPECIAL.");
	    }
    }

    /** check if membership is active */
    public boolean isActive() { 
    	GregorianCalendar currDate = new GregorianCalendar();
    	return (currDate.compareTo(startDate) >= 0 && currDate.compareTo(endDate) <= 0 );
    }
    
    /** return the price calculated after discount */
    public double calculateFinalPrice() { 
        return price - (price * (Constants.Discounts.valueOf(discount).getNumber() / 100.0));
    }
    
    /** return the price to pay, calculated after discount and membership type*/
    public double calculatePriceToPay() { 
    	switch (type.toUpperCase()) {
        case "YEARLY":
        	return 0.08 * (365.2 * calculateFinalPrice());
        case "MONTHLY":
        	return 0.2 * (30.4 * calculateFinalPrice());
    	}
    	return calculateFinalPrice();
    }
    
    @Override
    public String toString() {
        return "Membership{" +
        		"type=" + type + 
        		", discount=" + discount + "}";
    }
}