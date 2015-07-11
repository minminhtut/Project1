
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
    /**
     * 
     * @param type
     * @param customerID
     * @param creditCard
     * @param ticketDate
     * @param aPlay 
     */
    AdvanceTicket(int type, String customerID, List<Credit> creditCard, Calendar ticketDate, Play aPlay) {
        super(type, MEMBER_STRING, customerID, creditCard,ticketDate, aPlay);
    }

    /**
     *
     * @param aPlay
     * @return ticket Price for aPlay argument
     */
    public static int ticketPrice (Play aPlay) {
        return (int) (aPlay.getTicketPrice() * 0.7);
    }
}
