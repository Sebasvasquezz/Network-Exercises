package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ChatServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            ChatImpl chat = new ChatImpl();
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind("ChatService", chat);
            System.out.println("Chat server is running on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

