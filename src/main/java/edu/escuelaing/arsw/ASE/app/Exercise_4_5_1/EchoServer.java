package edu.escuelaing.arsw.ASE.app.Exercise_4_5_1;

import java.net.*;
import java.io.*;
import java.nio.file.*;

/**
 * The EchoServer class implements a simple HTTP server that listens on port 35000,
 * accepts client connections, and serves requested files from a specified webroot directory.
 */
public class EchoServer {

    /**
     * The main method is the entry point of the application. It sets up the server socket,
     * waits for client connections, and handles client requests to serve files.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs when opening the socket or accepting a connection.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Server is listening on port 35000");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            try (OutputStream clientOutput = clientSocket.getOutputStream();
                 PrintWriter out = new PrintWriter(clientOutput, true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String inputLine;
                String requestedFile = null;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if (inputLine.startsWith("GET")) {
                        requestedFile = inputLine.split(" ")[1];
                        if (requestedFile.equals("/")) {
                            requestedFile = "/index.html";  
                        }
                        break;
                    }
                    if (!in.ready()) {
                        break;
                    }
                }

                if (requestedFile != null) {
                    serveFile(clientOutput, out, requestedFile);
                }

            } catch (IOException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            }

            clientSocket.close();
        }
    }

     /**
     * Serves the requested file to the client.
     *
     * @param clientOutput The OutputStream to send the file content to the client.
     * @param out The PrintWriter to send HTTP headers to the client.
     * @param requestedFile The path of the requested file.
     */
    private static void serveFile(OutputStream clientOutput, PrintWriter out, String requestedFile) {
        Path filePath = Paths.get("src\\main\\java\\edu\\escuelaing\\arsw\\ASE\\app\\Exercise_4_5_1\\webroot", requestedFile);
        if (Files.exists(filePath)) {
            try {
                String contentType = Files.probeContentType(filePath);
                byte[] fileContent = Files.readAllBytes(filePath);

                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: " + contentType);
                out.println("Content-Length: " + fileContent.length);
                out.println();
                out.flush();

                clientOutput.write(fileContent);
                clientOutput.flush();

                System.out.println("Served file: " + filePath.toString());
            } catch (IOException e) {
                System.err.println("Error serving file: " + e.getMessage());
                send404(out);
            }
        } else {
            send404(out);
        }
    }

    /**
     * Sends a 404 Not Found error to the client.
     *
     * @param out The PrintWriter to send the 404 error message to the client.
     */
    private static void send404(PrintWriter out) {
        String errorMessage = "<h1>404 Not Found</h1>";
        out.println("HTTP/1.1 404 Not Found");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + errorMessage.length());
        out.println();
        out.println(errorMessage);
        out.flush();
    }
}
