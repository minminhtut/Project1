package project1;

import java.io.Serializable;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains variables for a play object.
 *
 * @author Legionaires
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
     * @param name
     * @param start
     * @param end
     */
    protected Play(String name, Calendar start, Calendar end) {
        this.name = new String(name);
        this.start = start;
        this.end = end;
    }

    /**
     * a getter method to get the name of a play
     *
     * @return
     */
    protected String getName() {
        return name;
    }

    /**
     * a setter method to set the name of a play
     *
     * @param name
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * a getter method to get the starting date
     *
     * @return start
     */
    protected Calendar getStart() {
        return start;
    }

    /**
     * a setter method to set the starting date
     *
     * @param start
     */
    protected void setStart(Calendar start) {
        this.start = start;
    }

    /**
     * a getter method to get the ending date
     *
     * @return end
     */
    protected Calendar getEnd() {
        return end;
    }
    
    /**
     * a setter method to set the ending date
     *
     * @param end
     */
    protected void setEnd(Calendar end) {
        this.end = end;
    }
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    protected String getEndString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(end.getTime());
    }
    
    /**
     * a getter method to get the ending date String
     *
     * @return end
     */
    protected String getStartString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(start.getTime());
    }
    /**
     * a method to print a play
     */
    protected void printPlay() {
        System.out.println("Name: " + this.getName() + " Start: " + this.getStartString() + " End: " + this.getEndString());
    }
}
