package Gym;

import GUI.CashPaymentPage;
import GUI.CardPaymentForm;
import GUI.PaymentSystemPage;

public class PaymentSystem 
{
	private Member member;
	private Membership membership;
    private static double price; 
    
    public PaymentSystem(Member member, Membership membership) {
    	this.member = member;
    	this.membership = membership;
    	setPrice(membership.calculatePriceToPay());
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    public Member getMember() {
        return member;
    }
    
    public void setMembership(Membership membership) {
        this.membership = membership;
    }
    public Membership getMembership() {
        return membership;
    }
    
    public double getPrice() {
        return price;
    }
    private void setPrice(double price) { 
    	Tests.checkDouble(price);
    	PaymentSystem.price = price;
    }
    
    /**call GUI to choose payment option */
    public static void makePayment(Object obj, Membership membership) { 	
    	new PaymentSystemPage(obj, price, membership);
    }

    /**call GUI to pay with cash */
    public static void handleCashPayment(Object obj, Membership membership) {
    	new CashPaymentPage(obj, membership);  		
    }

    /** call GUI to pay with credit card */
    public static void handleCreditPayment(Object obj, Membership membership) {
        new CardPaymentForm(obj, membership);
    }
}