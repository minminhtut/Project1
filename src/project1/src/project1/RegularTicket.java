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
public class RegularTicket extends Ticket implements Serializable,
		Matchable<String> {
    private static final String MEMBER_STRING = "R";

    /**
     * 
     * @param type
     * @param customerID
     * @param creditCard
     * @param playDate
     * @param aPlay 
     */
    RegularTicket(int type, String customerID, List<Credit> creditCard, Calendar playDate, Play aPlay) {
        super(type, MEMBER_STRING, customerID, creditCard, playDate, aPlay);
    }
    /**
     * 
     * @param aPlay
     * @return the ticket price for a regular ticket
     */
    public static long ticketPrice (Play aPlay) {
        return aPlay.getTicketPrice();
    }
    
    public void print(){
    	
    }
    
}
