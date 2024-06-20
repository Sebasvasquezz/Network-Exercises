package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServerImpl implements EchoServerRMI {
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

    public String echo(String cadena) throws RemoteException {
        return "desde el servidor: " + cadena;
    }

    public static void main(String[] args) {
        System.out.println("Iniciando EchoServer...");
        EchoServerImpl ec = new EchoServerImpl("127.0.0.1", 23000, "echoServer");
    }
}


