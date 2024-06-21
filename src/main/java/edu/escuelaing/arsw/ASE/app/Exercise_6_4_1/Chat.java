package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote {
    void sendMessage(String message, Chat sender) throws RemoteException;
    void registerClient(Chat client) throws RemoteException;
    void receiveMessage(String message) throws RemoteException;
}


