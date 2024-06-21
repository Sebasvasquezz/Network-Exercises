package edu.escuelaing.arsw.ASE.app.Exercise_5_2_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DatagramTimeServer class implements a server that listens on a specified port,
 * receives UDP datagram requests from clients, and responds with the current date and time.
 */
public class DatagramTimeServer {
    DatagramSocket socket;

    /**
     * Constructor to create a DatagramTimeServer instance and bind it to port 4445.
     */
    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The startServer method starts the server to listen for incoming requests.
     * Upon receiving a request, it sends the current date and time back to the client.
     */
    public void startServer() {
        byte[] buf = new byte[256];

        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String dString = new Date().toString();
            buf = dString.getBytes();
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            socket.close();
        }
    }

    /**
     * The main method is the entry point of the application.
     * It creates an instance of DatagramTimeServer and starts the server.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }
}
