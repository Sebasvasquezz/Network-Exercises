package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements Chat {
    protected ChatClient() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(String message, Chat sender) throws RemoteException {
        // No implementado en el cliente
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public void registerClient(Chat client) throws RemoteException {
        // No implementado en el cliente
    }

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

