package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClientEX {
    private static final int SERVER_PORT = 4445;
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int INTERVAL = 5000; // 5 seconds

    public static void main(String[] args) {
        byte[] buf = new byte[256];
        String currentTime = "No time received yet";

        while (true) {
            try (DatagramSocket socket = new DatagramSocket()) {
                InetAddress address = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, SERVER_PORT);
                socket.send(packet);

                socket.setSoTimeout(INTERVAL - 1000);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                currentTime = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Updated Time: " + currentTime);
            } catch (SocketException | UnknownHostException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println("Failed to receive the time, keeping the last known time: " + currentTime);
            }

            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

