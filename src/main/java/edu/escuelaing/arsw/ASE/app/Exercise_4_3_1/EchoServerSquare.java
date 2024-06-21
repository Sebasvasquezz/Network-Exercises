package edu.escuelaing.arsw.ASE.app.Exercise_4_3_1;

import java.net.*;
import java.io.*;

/**
 * The EchoServerSquare class implements a simple server that listens on a specified port,
 * accepts client connections, reads input from the client, calculates the square of the input number,
 * and sends the result back to the client.
 */
public class EchoServerSquare {
    
   /**
     * The main method is the entry point of the application. It sets up the server socket,
     * waits for client connections, and handles client input to calculate and return the square of a number.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs when opening the socket or accepting a connection.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            try {
                System.out.println("Mensaje: " + inputLine);
                int number = Integer.parseInt(inputLine);
                int square = number * number;
                out.println("El cuadrado de " + number + " es " + square);
            } catch (NumberFormatException e) {
                out.println("Por favor, ingrese un número válido.");
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

