package project1;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class contains variables for a credit card object.
 * @author Legionaires
 *
 */
public class Credit implements Serializable {
	private static final long serialVersionUID = 1L;
	private long number;
	private Calendar expiration;
	
	/**
	 * a constructor without parameters
	 */
	protected Credit() {
	}
	
	/**
	 * create a credit card with a given number and an expiration date
	 * @param n, the number of a credit card
	 * @param e, an expiration date
	 */
	protected Credit(long n, Calendar e) {
		this.number = n;
		this.expiration = e;
	}

	/**
	 * a getter method for the number of a credit card
	 * @return number
	 */
	protected long getNumber() {
		return number;
	}

	/**
	 * a setter method for the number of a credit card
	 * @param n, the number of a credit card
	 */
	protected void setNumber(int number) {
		this.number = number;
	}

	/**
	 * a getter method for the expiration date of a credit card
	 * @return expiration
	 */
	protected Calendar getExpiration() {
		return expiration;
	}

	/**
	 * a setter method for the expiration date of a credit card
	 * @param e, an expiration date
	 */
	protected void setExpiration(Calendar expiration) {
		this.expiration = expiration;
	}
	
	/**
	 * a method to print a credit card
	 */
	protected void printCard() {
		System.out.println("Number: " + this.getNumber() + " " + "Expiration: " + this.getExpiration().toString());
	}

}
