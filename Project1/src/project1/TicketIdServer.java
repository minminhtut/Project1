package project1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class TicketIdServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idCounter;
    private static TicketIdServer ticketServer;
    /*
     * Private constructor for singleton pattern
     * 
     */

    private TicketIdServer() {
        idCounter = 1;
    }

    /**
     * Supports the singleton pattern
     *
     * @return the singleton object
     */
    public static TicketIdServer instance() {
        if (ticketServer == null) {
            return (ticketServer = new TicketIdServer());
        } else {
            return ticketServer;
        }
    }

    /**
     * Getter for id
     *
     * @return id of the member
     */
    public int getId() {
        return idCounter++;
    }

    /**
     * String form of the collection
     *
     */
    @Override
    public String toString() {
        return ("TicketServer" + idCounter);
    }

    /**
     * Retrieves the server object
     *
     * @param input inputstream for deserialization
     */
    public static void retrieve(ObjectInputStream input) {
        try {
        	ticketServer = (TicketIdServer) input.readObject();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }
    /*
     * Supports serialization
     * @param output the stream to be written to
     */

    public void writeObject(java.io.ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(ticketServer);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /*
     * Supports serialization
     * @param input the stream to be read from
     */

    public void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            input.defaultReadObject();
            if (ticketServer == null) {
            	ticketServer = (TicketIdServer) input.readObject();
            }
            else {
                input.readObject();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
