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
    private int ticketPrice;
            
    public Ticket (int type, String ID, String serial, List<Credit> credit, Calendar calandar, Play aPlay) {
        this.customerID = ID;
        this.ticketType = type;
        this.serialNumber = serial+ (MemberIdServer.instance()).getId();
        this.creditCard = credit;
        this.dateOfPlay = calandar;
        this.ticketPlay = aPlay;
        this.ticketPrice = setTicketPrice(this.ticketType, this.ticketPlay);
    }
    
    @Override
    public boolean matches(String key) {
        return serialNumber.equals(key);
    }
    
    public String getSerialNumber () {
        return serialNumber;
    }
    public int getPrice () {
        return ticketPrice;
    }
    
    public void setType (int type) {
        ticketType = type;
    }
    
    public int getType () {
        return ticketType;
    }
    private  int setTicketPrice (int type, Play aPlay) {
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
    
}