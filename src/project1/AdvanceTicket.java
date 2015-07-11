
package project1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author William
 */
public class AdvanceTicket extends Ticket implements Serializable,
		Matchable<String> {
    private static final String MEMBER_STRING = "A";
    Calendar dateOfPlay;
    AdvanceTicket(int type, String customerID, List<Credit> creditCard, Calendar ticketDate, Play aPlay) {
        super(type, MEMBER_STRING, customerID, creditCard,ticketDate, aPlay);
    }

    public static int ticketPrice (Play aPlay) {
        return (int) (aPlay.getTicketPrice() * 0.7);
    }
}
