package project1;

/**
 * This file contains Client Object for Project 1.
 * @author Legionaires
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * the class ClientList
 *
 */
public class ClientList extends ListHelper<Client, String> {

    private static final long serialVersionUID = 1L;
    private static ClientList client;

    public ClientList() {
    }

    /**
     * Support the singleton pattern
     *
     * @return the singleton object
     */
    public static ClientList instance() {
        if (client == null) {
            return (client = new ClientList());
        }
        else {
            return client;
        }
    }

    /**
     * Checks whether a client with given id exist or not.
     *
     * @param id
     * @return the customer if exist
     */
    public Client searchClientID(String id) {
        return super.search(id);
    }

    /**
     * A method to add a client
     *
     * @param newClient
     * @return false if customer dosen't exist
     */
    public boolean addClient(Client newClient) {
        return super.add(newClient);
    }

    /**
     * A method to remove a client
     *
     * @param id, Client Object which contains the client to be removed.
     * @return true if the client was removed
     */
    public boolean removeClient(Client removeClient) {
        return super.remove(removeClient);
    }

    /**
     * A method to add a play to the client
     *
     * @param client, Client Object which contains the client.
     * @param play, Play Object which contains a play to be added to the client.
     * @return true if the play is added
     */
    public boolean addPlay(Client client, Play play) {
        boolean goodDate = true;
        for (Iterator<Client> iterator = super.iterator(); iterator.hasNext() && goodDate;) {
            Client aClient = iterator.next();
            goodDate = aClient.searchPlayDate(play.getStartDate(), play.getEndDate());

        }
        if (goodDate) {
            for (Iterator<Client> iterator = super.iterator(); iterator.hasNext();) {
                Client aClient = iterator.next();
                if (aClient.getId().equals(client.getId())) {
                    Play result = aClient.searchPlaytName((play.getName()));
                    if (result == null) {
                        aClient.addPlay(play);
                        return true;
                    } else {
                       System.out.println("Play name already exists for this client."); 
                    }
                }
            }
        } else {
            System.out.println("Date range conficts with other plays.");
        }

        return false;
    }

    /**
     * A method to list all clients
     */
    public void listAllClients() {
        for (Iterator<Client> iterator = super.iterator(); iterator.hasNext();) {
            Client aClient = iterator.next();
            aClient.printClient();
        }

    }

    /**
     * a method to list all shows
     */
    public void ListAllPlays() {
        for (Iterator<Client> iterator = super.iterator(); iterator.hasNext();) {
            Client cliendata = iterator.next();
            cliendata.ListAllPlays();
        }

    }
    
    /**
     * a method to check is client list is empty
     *  @return true if no clients
     */
    public boolean noClients() {
        return super.isEmptyList();
    }
    
    /**
     * 
     * @param ticketDate
     * @return Play object if ticketDate falls within its date range
     */
    public Play searchTicketDate(int ticketDate) {
        for (Iterator<Client> iterator = super.iterator(); iterator.hasNext();) {
            Client aClient = iterator.next();
            Play result = aClient.searchPlayTicket(ticketDate);
            if (result != null) {
                return result;
            }

        }
        System.out.println("No Play found for given date."); 
        return null;
    }


}
