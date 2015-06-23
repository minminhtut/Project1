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

    /**
     * a constructor without parameters
     */
    protected Play() {
    }

    /**
     * create a play with given name, starting date, and ending date
     *
     * @param newName
     * @param newStart
     * @param newEnd
     */
    protected Play(String newName, Calendar newStart, Calendar newEnd) {
        this.name = new String(newName);
        this.start = newStart;
        this.end = newEnd;
    }

    /**
     * a getter method to get the name of a play
     *
     * @return
     */
    protected String getName() {
        return this.name;
    }

    /**
     * a setter method to set the name of a play
     *
     * @param newName
     */
    protected void setName(String newName) {
        this.name = newName;
    }

    /**
     * a getter method to get the starting date
     *
     * @return start
     */
    protected Calendar getStart() {
        return this.start;
    }

    /**
     * a setter method to set the starting date
     *
     * @param start
     */
    protected void setStart(Calendar newStart) {
        this.start = newStart;
    }

    /**
     * a getter method to get the ending date
     *
     * @return end
     */
    protected Calendar getEnd() {
        return this.end;
    }
    
    /**
     * a setter method to set the ending date
     *
     * @param end
     */
    protected void setEnd(Calendar newEnd) {
        this.end = newEnd;
    }
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    protected String getEndString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(this.end.getTime());
    }
    
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    protected String getStartString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(this.start.getTime());
    }
    /**
     * a method to print a play
     */
    protected void printPlay() {
        System.out.println("Name: " + this.getName() + " Start: " + this.getStartString() + " End: " + this.getEndString());
    }
}
