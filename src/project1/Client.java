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
 * the class Client
 *
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Client client;
    private List<ClientData> clientList = new LinkedList<ClientData>();

    private Client() {
    }

    /**
     * Support the singleton pattern
     *
     * @return the singleton object
     */
    protected static Client instance() {
        if (client == null) {
            return (client = new Client());
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
    protected ClientData searchClientID(String id) {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().equals(id)) {
                return aClient;
            }
        }
        return null;
    }

    /**
     * A method to add a client
     *
     * @param newClient
     * @return false if customer dosen't exist
     */
    protected boolean addClient(ClientData newClient) {
        if (searchClientID(newClient.getId()) == null) {
            this.clientList.add(newClient);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A method to remove a client
     *
     * @param id, ClientData Object which contains the client to be removed.
     * @return true if the client was removed
     */
    protected boolean removeClient(ClientData removeClient) {
        if (removeClient != null) {
            this.clientList.remove(removeClient);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A method to add a play to the client
     *
     * @param client, ClientData Object which contains the client.
     * @param play, Play Object which contains a play to be added to the client.
     * @return true if the play is added
     */
    //protected boolean addPlay(String id, String name, Date start, Date end) {
    protected boolean addPlay(ClientData client, Play play) {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            if (aClient.getId().equals(client.getId())) {
                Play result = aClient.searchPlaytName(play.getName());
                if (result == null) {
                    aClient.addPlay(play);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A method to list all clients
     */
    protected void listAllClients() {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData aClient = iterator.next();
            aClient.printClient();
        }

    }

    /**
     * a method to list all shows
     */
    protected void ListAllPlays() {
        for (Iterator<ClientData> iterator = this.clientList.iterator(); iterator.hasNext();) {
            ClientData cliendata = iterator.next();
            cliendata.ListAllPlays();
        }

    }
    
    /**
     * a method to check is client list is empty
     */
    public boolean noClients() {
        return this.clientList.isEmpty();
    }


}
