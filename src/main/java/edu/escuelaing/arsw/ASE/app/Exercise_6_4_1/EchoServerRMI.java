package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The EchoServerRMI interface defines the methods that can be invoked remotely
 * by clients in an RMI (Remote Method Invocation) echo server.
 */
public interface EchoServerRMI extends Remote {
     /**
     * Echo method that returns a string prefixed with "desde el servidor: ".
     * This method can be invoked remotely by clients.
     *
     * @param cadena The input string from the client.
     * @return The response string from the server.
     * @throws RemoteException If a remote communication error occurs.
     */
    public String echo(String cadena) throws RemoteException;
}
