/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author William
 */
public class StudentAdvance extends Ticket implements Serializable,
		Matchable<String> {
    private static final String MEMBER_STRING = "S";
    private String studentID;
    /**
     * 
     * @param type
     * @param customerID
     * @param creditCard
     * @param playDate
     * @param aPlay
     * @param ID 
     */
    StudentAdvance(int type, String customerID, List<Credit> creditCard, Calendar playDate, Play aPlay, String ID) {
        super(type, MEMBER_STRING, customerID, creditCard, playDate, aPlay);
        this.studentID = ID;
    }   
    /**
     * 
     * @param aPlay
     * @return ticket price for Student Advance ticket
     */
    public static BigDecimal ticketPrice (Play aPlay) {
        BigDecimal price = aPlay.getTicketPrice().multiply(new BigDecimal(0.5));
        return price.setScale(2, RoundingMode.HALF_UP);
    }
    
    
}
