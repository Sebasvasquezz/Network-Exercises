package edu.escuelaing.arsw.ASE.app;

import java.net.*;
import java.io.*;

public class EchoServerSquare {
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

