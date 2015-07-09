package project1;

/**
 * This file contains Client Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * the Class Play contains data for a single play
 *
 */
public class Play implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private Calendar start;
    private Calendar end;
    private int startDate;
    private int endDate;
    private int ticketPrice;
    
    public Play() {
    	
    }

    /**
     * create a play with given name, starting date, and ending date
     *
     * @param newName
     * @param newStart
     * @param newEnd
     */
    public Play(String newName, Calendar newStart, Calendar newEnd, int price) {
        this.name = newName;
        this.start = newStart;
        this.end = newEnd;
        this.ticketPrice = price;
        setDateRange (this.start, this.end);
    }

    /**
     * a getter method to get the name of a play
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * a setter method to set the name of a play
     *
     * @param newName
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * a getter method to get the starting date
     *
     * @return start
     */
    public Calendar getStart() {
        return start;
    }

    /**
     * a setter method to set the starting date
     *
     * @param start
     */
    public void setStart(Calendar newStart) {
        start = newStart;
    }

    /**
     * a getter method to get the ending date
     *
     * @return end
     */
    public Calendar getEnd() {
        return end;
    }
    
    /**
     * a setter method to set the ending date
     *
     * @param end
     */
    public void setEnd(Calendar newEnd) {
        end = newEnd;
    }
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    public String getEndString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(end.getTime());
    }
    
    /**
     * a getter method to get the ending date String
     *
     * @return start
     */
    public String getStartString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(start.getTime());
    }
    /**
     * a getter method to get the ending date int
     *
     * @return end
     */
    public int getEndDate() {

        return this.endDate;
    }
    
    /**
     * a getter method to get the ending date int
     *
     * @return startDate
     */
    public int getStartDate() {
        return this.startDate;
    }
    /**
     * a getter method to get the ending date int
     *
     * @return endDate
     */
    public int getTicketPrice() {
        return ticketPrice;
    }
    
    /**
     * check current date against play starting date
     * @return false if start date is in the future
     */
    public boolean checkTime() {
    	Calendar current = Calendar.getInstance();
    	if(current.getTimeInMillis() < start.getTimeInMillis())
    		return false;
    	else
    		return true;
    }
    
    public void setDateRange (Calendar startd, Calendar endd) {
        int year = startd.get(Calendar.YEAR);
        int month = startd.get(Calendar.MONTH);
        int day = startd.get(Calendar.DATE);
        this.startDate= (year*10000) + ((month+1)*100) + day;  
        year = endd.get(Calendar.YEAR);
        month = endd.get(Calendar.MONTH);
        day = endd.get(Calendar.DATE);
        this.endDate = (year*10000) + ((month+1)*100) + day;       
    }
    
    /**
     * a method to print a play
     */
    public void printPlay() {
        System.out.println("Name: " + getName() + " Start: " + getStartString() + " End: " + getEndString());
    }
}
