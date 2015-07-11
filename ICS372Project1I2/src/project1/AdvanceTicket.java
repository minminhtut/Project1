package project1;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This file contains Regular Ticket Object for Project 1.
 * @author William
 */

public class AdvanceTicket extends Ticket implements Serializable {
	private static final int REGULAR_TICKET = 1;
	private static final int ADVANCE_TICKET = 2;
	private static final int ADVANCE_STUDENT = 3;
	
	private static final String TICKET_STRING = "T";
	
	public AdvanceTicket(Calendar newDate, long number, double newPrice) {
		super(ADVANCE_TICKET, newDate, number, newPrice);
	}
}