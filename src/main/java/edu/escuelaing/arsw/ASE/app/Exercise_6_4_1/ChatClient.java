package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * ChatClient class implements the Chat interface and acts as a chat client in the RMI-based chat application.
 * This class connects to the chat server, sends messages, and receives messages.
 */
public class ChatClient extends UnicastRemoteObject implements Chat {
    /**
     * Constructor for ChatClient.
     * 
     * @throws RemoteException if a remote communication error occurs
     */
    protected ChatClient() throws RemoteException {
        super();
    }

    /**
     * Method to send a message. Not implemented for the client.
     * 
     * @param message the message to be sent
     * @param sender the client sending the message
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void sendMessage(String message, Chat sender) throws RemoteException {
        // Not implemented in the client
    }

    /**
     * Receives a message from the chat server.
     * 
     * @param message the message received
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    /**
     * Method to register a client. Not implemented for the client.
     * 
     * @param client the client to be registered
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void registerClient(Chat client) throws RemoteException {
        // Not implemented in the client
    }

    /**
     * Main method to start the ChatClient.
     * Connects to the chat server and starts sending/receiving messages.
     * 
     * @param args the command line arguments (server IP and server port)
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ChatClient <server_ip> <server_port>");
            return;
        }

        String serverIp = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try {
            Registry registry = LocateRegistry.getRegistry(serverIp, serverPort);
            Chat chat = (Chat) registry.lookup("ChatService");

            ChatClient client = new ChatClient();
            chat.registerClient(client);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your messages: ");
            while (true) {
                String message = scanner.nextLine();
                chat.sendMessage(message, client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

