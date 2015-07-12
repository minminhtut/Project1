/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author William
 */
public class Ticket implements Matchable<String>, Serializable {
    private String customerID;
    private String serialNumber;
    private List<Credit> creditCard;
    private Calendar dateOfPlay;
    private int ticketType;
    protected Play ticketPlay;
    private long ticketPrice;
            
    public Ticket (int type, String ID, String serial, List<Credit> credit, Calendar calandar, Play aPlay) {
        this.customerID = ID;
        this.ticketType = type;
        this.serialNumber = serial+ (MemberIdServer.instance()).getId();
        this.creditCard = credit;
        this.dateOfPlay = calandar;
        this.ticketPlay = aPlay;
        this.ticketPrice = setTicketPrice(this.ticketType, this.ticketPlay);
    }
    /**
     * 
     * @param key
     * @return true if Key matches serial number
     */
    @Override
    public boolean matches(String key) {
        return serialNumber.equals(key);
    }
    /**
     * 
     * @return serial number for this ticket
     */
    public String getSerialNumber () {
        return serialNumber;
    }
    /**
     * 
     * @return price of this ticket
     */
    public long getPrice () {
        return ticketPrice;
    }
    
    /**
     * 
     * @param type 
     */
    public void setType (int type) {
        ticketType = type;
    }
    
    /**
     * 
     * @return ticket type
     */
    public int getType () {
        return ticketType;
    }
    
    /**
     * 
     * @param type
     * @param aPlay
     * @return the ticket price depending on ticket type
     */
    private  long setTicketPrice (int type, Play aPlay) {
            switch (type) {
                case Theater.REGULARTICKET:
                    return  RegularTicket.ticketPrice(aPlay);
                case Theater.ADVANCETICKET:
                    return AdvanceTicket.ticketPrice(aPlay);
                case Theater.STUDENTADVANCE:
                    return StudentAdvance.ticketPrice(aPlay);   
                default:
                    return RegularTicket.ticketPrice(aPlay);    
            }
    }
	@Override
	public String toString() {
		String typeString = "";
		if(ticketType==1){
			typeString= "Regular";
		}
		if(ticketType==2){
			typeString= "Advance";
		}
		if(ticketType==3){
			typeString= "Student Advance";
		}
		return "Ticket Customer ID= " + customerID + ", Serial Number="
				+ serialNumber + ", Credit Card Number= " + creditCard + ", dateOfPlay= "
				+ dateOfPlay + ", Ticket Type= " + typeString + 
				", Ticket Play= " + ticketPlay + ", Ticket Price= " + ticketPrice;
	}
    
    
    
}
