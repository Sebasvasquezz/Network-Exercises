package edu.escuelaing.arsw.ASE.app.Exercise_4_3_2;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.io.*;

/**
 * The EchoServerFunc class implements a server that listens on a specified port,
 * accepts client connections, and performs mathematical operations based on client input.
 */
public class EchoServerFunc {
    /**
     * The main method is the entry point of the application. It sets up the server socket,
     * waits for client connections, and handles client input to perform mathematical operations.
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

        String currentOperation = "cos";

        Map<String, Function<Double, Double>> operations = new HashMap<>();
        operations.put("cos", Math::cos);
        operations.put("sin", Math::sin);
        operations.put("tan", Math::tan);

        while ((inputLine = in.readLine()) != null) {
            if (inputLine.startsWith("fun:")) {
                String newOperation = inputLine.substring(4); 
                if (operations.containsKey(newOperation)) {
                    currentOperation = newOperation; 
                    out.println("Operación cambiada a: " + currentOperation);
                } else {
                    out.println("Operación no reconocida: " + newOperation);
                }
            } else {
                try {
                    double number = Double.parseDouble(inputLine);
                    double result = operations.get(currentOperation).apply(number);
                    out.println("Resultado: " + result);
                } catch (NumberFormatException e) {
                    out.println("Entrada no válida. Por favor, ingrese un número o una operación válida.");
                }
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

