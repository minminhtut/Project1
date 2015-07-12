
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
     * @return ticket Price for play
     */
    public static BigDecimal ticketPrice (Play aPlay) {
        BigDecimal price = aPlay.getTicketPrice().multiply(new BigDecimal(0.7));
        return price.setScale(2, RoundingMode.HALF_UP);
    }
}
