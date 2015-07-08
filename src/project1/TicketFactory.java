
package project1;

import java.util.Calendar;
import java.util.List;


/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 * 
 *            Redistribution and use with or without modification, are permitted
 *            provided that the following conditions are met:
 *
 *            - the use is for academic purpose only - Redistributions of source
 *            code must retain the above copyright notice, this list of
 *            conditions and the following disclaimer. - Neither the name of
 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
 *            products derived from this software without specific prior written
 *            permission.
 *
 *            The authors do not make any claims regarding the correctness of
 *            the code in this module and are not responsible for any loss or
 *            damage resulting from its use.
 */
/**
 * Creates different types of LoanableItem objects. When a new LoanableItem is
 * introduced, the constructor for that class must be invoked from here. This is
 * a singleton.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class TicketFactory {
	private static TicketFactory factory;

	/**
	 * Private for singleton
	 */
	private TicketFactory() {
	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the instance
	 */
	public static TicketFactory instance() {
		if (factory == null) {
			factory = new TicketFactory();
		}
		return factory;
	}

	/**
	 * Creates a LoanableItem object and returns it.
	 * 
	 * @param type
	 *            the type of the item
	 * @param title
	 *            the title of the item
	 * @param author
	 *            the author of the item (if it is a book)
	 * @param id
	 *            the id of the item
	 * @return the item that was created
	 */
    public Ticket CreateTicket(int type, String customerID, List<Credit> creditCard, Calendar playDate, String studentID) {


            switch (type) {
                case Theater.REGULARTICKET:
                    return new RegularTicket(type, customerID, creditCard, playDate);
                case Theater.ADVANCETICKET:
                    return new AdvanceTicket(type, customerID, creditCard, playDate);
                case Theater.STUDENTADVANCE:
                    return new StudentAdvance(type, customerID, creditCard, playDate, studentID);
                default:
                    return null;
            }
        }



}