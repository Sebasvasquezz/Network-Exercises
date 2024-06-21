package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatImpl extends UnicastRemoteObject implements Chat {
    private List<Chat> clients = new ArrayList<>();

    protected ChatImpl() throws RemoteException {
        super();
    }

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

    @Override
    public synchronized void registerClient(Chat client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {

        throw new UnsupportedOperationException("Unimplemented method 'receiveMessage'");
    }
}


