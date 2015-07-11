package project1;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This file contains Regular Ticket Object for Project 1.
 * @author William
 */

public class AdvanceStudent extends Ticket implements Serializable {
	private static final int REGULAR_TICKET = 1;
	private static final int ADVANCE_TICKET = 2;
	private static final int ADVANCE_STUDENT = 3;
	
	public AdvanceStudent(Calendar newDate, long number, double newPrice) {
		super(ADVANCE_STUDENT, newDate, number, newPrice);
	}
}
