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
            
    public Ticket (int type, String ID, String serial, List<Credit> credit, Calendar calandar) {
        this.customerID = ID;
        this.ticketType = type;
        this.serialNumber = serial+ (MemberIdServer.instance()).getId();
        this.creditCard = credit;
        this.dateOfPlay = calandar;
    }
    
    @Override
    public boolean matches(String key) {
        return serialNumber.equals(key);
    }
    
    public String getSerialNumber () {
        return serialNumber;
    }
    
    public void setType (int type) {
        ticketType = type;
    }
    
    public int getType () {
        return ticketType;
    }
    
    
}
