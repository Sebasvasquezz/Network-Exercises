package edu.escuelaing.arsw.ASE.app.Exercise_6_4_1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The EchoClientRMI class represents a client for invoking remote methods using RMI (Remote Method Invocation).
 * It provides functionality to execute a service by looking up a specified RMI registry and invoking the
 * echo method on the server.
 */
public class EchoClientRMI {
     /**
     * Executes a service by looking up the specified RMI registry and invoking the echo method on the server.
     * 
     * @param ipRmiregistry      the IP address of the RMI registry
     * @param puertoRmiRegistry  the port number of the RMI registry
     * @param nombreServicio     the name of the RMI service to be invoked
     */
    public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry, String nombreServicio) {

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            EchoServerRMI echoServer = (EchoServerRMI) registry.lookup(nombreServicio);
            System.out.println(echoServer.echo("Hola como estas?"));
        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
        }
    }

    /**
     * The main method that creates an instance of EchoClientRMI and executes a service.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EchoClientRMI ec = new EchoClientRMI();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer");
    }
}

