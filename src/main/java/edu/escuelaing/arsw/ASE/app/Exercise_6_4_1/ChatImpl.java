package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * ChatImpl class implements the Chat interface and acts as the server-side implementation of the chat service.
 * Manages client registration and message broadcasting.
 */
public class ChatImpl extends UnicastRemoteObject implements Chat {
    private List<Chat> clients = new ArrayList<>();

    /**
     * Constructor for ChatImpl.
     * 
     * @throws RemoteException if a remote communication error occurs
     */
    protected ChatImpl() throws RemoteException {
        super();
    }

    /**
     * Sends a message to all registered clients.
     * 
     * @param message the message to be sent
     * @param sender the client sending the message
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public synchronized void sendMessage(String message, Chat sender) throws RemoteException {
        for (Chat client : clients) {
            if (client.equals(sender)) {
                client.receiveMessage("Message sent: " + message);
            } else {
                client.receiveMessage("Message received: " + message);
            }
        }
    }

    /**
     * Registers a new client with the chat server.
     * 
     * @param client the client to be registered
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public synchronized void registerClient(Chat client) throws RemoteException {
        clients.add(client);
    }

    /**
     * Receives a message from the chat server.
     * 
     * @param message the message received
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void receiveMessage(String message) throws RemoteException {
        // Not implemented in the server
    }
}


