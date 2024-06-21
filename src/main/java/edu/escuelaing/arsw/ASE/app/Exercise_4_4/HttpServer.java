package edu.escuelaing.arsw.ASE.app.Exercise_4_4;

import java.net.*;
import java.io.*;

/**
 * The HttpServer class represents a simple HTTP server that responds with a basic HTML page to client requests.
 * It listens for incoming connections on a specified port, accepts client connections, and sends an HTML response.
 */
public class HttpServer {
    /**
     * Main method to execute the HttpServer.
     * 
     * @param args the command line arguments
     * @throws IOException if an I/O error occurs
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
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine="", outputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
        outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Title of the document</title>\n" + "</head>"
                    + "<body>"
                    + "My Web Site"
                    + "<br></br>"
                    + "<img src=https://static.abc.es/media/peliculas/000/034/797/the-amazing-spider-man-2-2.jpg>"
                    + "</body>"
                    + "</html>";
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

