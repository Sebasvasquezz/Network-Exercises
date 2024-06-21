package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Chat interface for RMI-based chat application.
 * Defines methods for sending and receiving messages and registering clients.
 */
public interface Chat extends Remote {
    /**
     * Sends a message to the chat server.
     * 
     * @param message the message to be sent
     * @param sender the client sending the message
     * @throws RemoteException if a remote communication error occurs
     */
    void sendMessage(String message, Chat sender) throws RemoteException;

     /**
     * Registers a new client with the chat server.
     * 
     * @param client the client to be registered
     * @throws RemoteException if a remote communication error occurs
     */
    void registerClient(Chat client) throws RemoteException;

    /**
     * Receives a message from the chat server.
     * 
     * @param message the message received
     * @throws RemoteException if a remote communication error occurs
     */
    void receiveMessage(String message) throws RemoteException;
}


