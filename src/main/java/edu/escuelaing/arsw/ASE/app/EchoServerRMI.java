package edu.escuelaing.arsw.ASE.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoServerRMI extends Remote {
    public String echo(String cadena) throws RemoteException;
}
