
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
    AdvanceTicket(int type, String customerID, List<Credit> creditCard, Calendar playDate) {
        super(type, MEMBER_STRING, customerID, creditCard, playDate);
    }

    
}
