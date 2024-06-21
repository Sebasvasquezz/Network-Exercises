package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The EchoServerImpl class implements the EchoServerRMI interface and provides
 * functionality for an RMI (Remote Method Invocation) echo server.
 */
public class EchoServerImpl implements EchoServerRMI {

    /**
     * Constructor to create an instance of EchoServerImpl and register it with the RMI registry.
     *
     * @param ipRMIregistry The IP address of the RMI registry.
     * @param puertoMRIregistry The port number of the RMI registry.
     * @param nombreDePublicacion The name under which the server object will be published.
     */
    public EchoServerImpl(String ipRMIregistry, int puertoMRIregistry, String nombreDePublicacion) {
        if (System.getSecurityManager() == null) { 
            System.setSecurityManager(new SecurityManager());
        }
        try {
            System.out.println("Exportando objeto...");
            EchoServerRMI echoServer = (EchoServerRMI) UnicastRemoteObject.exportObject(this, 0);
            System.out.println("Localizando registro RMI...");
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoMRIregistry);
            System.out.println("Rebinding al registro...");
            registry.rebind(nombreDePublicacion, echoServer);
            System.out.println("Echo server ready...");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }


    /**
     * Echo method that returns a string prefixed with "desde el servidor: ".
     *
     * @param cadena The input string from the client.
     * @return The response string from the server.
     * @throws RemoteException If a remote communication error occurs.
     */
    public String echo(String cadena) throws RemoteException {
        return "desde el servidor: " + cadena;
    }

    /**
     * The main method is the entry point of the application.
     * It creates an instance of EchoServerImpl and starts the server.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Iniciando EchoServer...");
        EchoServerImpl ec = new EchoServerImpl("127.0.0.1", 23000, "echoServer");
    }
}


