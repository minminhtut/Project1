package project1;

/**
 * This file contains Client Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;
import java.text.SimpleDateFormat;

/**
 * the Class Play contains data for a single play
 *
 */
public class Play implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Calendar start;
    private Calendar end;
    private double price;
    private double totalSale;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    
    public Play() {
    	
    }

    /**
     * create a play with given name, starting date, and ending date
     *
     * @param newName
     * @param newStart
     * @param newEnd
     */
    public Play(String newName, Calendar newStart, Calendar newEnd, double newPrice) {
        name = new String(newName);
        start = newStart;
        end = newEnd;
        price = newPrice;
    }

    /**
     * a getter method to get the name of a play
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * a setter method to set the name of a play
     *
     * @param newName
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * a getter method to get the starting date
     *
     * @return start
     */
    public Calendar getStart() {
        return start;
    }

    /**
     * a setter method to set the starting date
     *
     * @param start
     */
    public void setStart(Calendar newStart) {
        start = newStart;
    }

    /**
     * a getter method to get the ending date
     *
     * @return end
     */
    public Calendar getEnd() {
        return end;
    }
    
    /**
     * a setter method to set the ending date
     *
     * @param end
     */
    public void setEnd(Calendar newEnd) {
        end = newEnd;
    }
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    public String getEndString() {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        return date.format(end.getTime());
    }
    
    /**
     * a getter method to get the ending date String
     *
     * @return start
     */
    public String getStartString() {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        return date.format(start.getTime());
    }
    
    /**
     * check current date against play starting date
     * @return false if start date is in the future
     */
    public boolean checkTime() {
    	Calendar current = Calendar.getInstance();
    	if(current.getTimeInMillis() < start.getTimeInMillis())
    		return false;
    	else
    		return true;
    }
    
    /**
     * a getter method for the tickets    
     * @return tickets
     */
    public ArrayList<Ticket> getTickets() {
		return tickets;
	}
    
    /**
     * a method to get price    
     * @return price
     */
    public double getPrice() {
    	return price;
    }
    
    /**
     * a method to set price
     * @param newPrice
     */
    public void setPrice(double newPrice) {
    	price = newPrice;
    }
    
    /**
     * a method to get a totalsale
     * @param newPrice
     */
    public double getTotalSale() {
    	return totalSale;
    }
    
    /**
     * a method to set a totalsale
     * @param pay
     */
    public void setTotalSale(double pay) {
    	totalSale = pay;
    }
    
    /**
     * a setter method for a single ticket
     * @param tickets
     */
	public void setTickets(Ticket ticket) {
		
		double clientIncome = ticket.getPrice() / 2;
		totalSale = clientIncome + totalSale;
		tickets.add(ticket);
	}
	
	public double updatePlayBalance(double payment) {
		if(payment >= totalSale) {
			totalSale = 0.0;
			return payment - totalSale;
		}
		else {
			totalSale = totalSale - payment;
			return 0.0;
		}
	}

	/**
     * a method to print a play
     */
    public void printPlay() {
        System.out.println("Name: " + getName() + " Start: " + getStartString() + " End: " + 
        					getEndString() + " Price: " + getPrice() + " Current Balance: " + totalSale);
        
        for (ListIterator<Ticket> iterator = tickets.listIterator(); iterator.hasNext();) {
        	Ticket aTicket = iterator.next();
        	aTicket.printTicket();
        }
    }
}
