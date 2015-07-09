package project1;

/**
 * This file contains Ticket Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticket implements Serializable {
	private static final int REGULAR_TICKET = 1;
	private static final int ADVANCE_TICKET = 2;
	private static final int ADVANCE_STUDENT = 3;
	
	private static final String TICKET_STRING = "T";
	
	private int type;
	private Calendar date;
	private String id;
	private long creditCard;
	private String extraMessage;
	private double price;
	private boolean paid = false;
	
	public Ticket() {
	}
	
	public Ticket(int newType, Calendar newDate, long number) {
		type = newType;
		date = newDate;
		creditCard = number;
		id = new String(TICKET_STRING + (TicketIdServer.instance()).getId());
		
		switch(type) {
			case REGULAR_TICKET:
				extraMessage = new String("");
				price = 10;
				break;
			case ADVANCE_TICKET:
				extraMessage = new String("");
				price = 10 * 0.70;
				break;
			case ADVANCE_STUDENT:
				extraMessage = new String("Must show valid student id.");
				price = 10 * 0.50;
				break;
			default:
				break;
		}
	}
	
	/**
	 * a getter method to get the date
	 * @return String formated to mm/dd/yy
	 */
	public String getDate() {
        SimpleDateFormat sDate = new SimpleDateFormat("MM/dd/yyyy");
        return sDate.format(date.getTime());
    }
	
	/**
	 * a getter method to get a price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * a method to print the ticket
	 */
	public void printTicket() {
		System.out.println("ID: " + id + " Date: " + getDate() + " Credit Card: " + creditCard);
		if(type == ADVANCE_STUDENT)
			System.out.println(extraMessage);
	}
}