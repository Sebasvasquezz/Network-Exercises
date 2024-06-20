package edu.escuelaing.arsw.ASE.app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author dnielben
 */
public class EchoClientRMI {

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

    public static void main(String[] args) {
        EchoClientRMI ec = new EchoClientRMI();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer");
    }
}

