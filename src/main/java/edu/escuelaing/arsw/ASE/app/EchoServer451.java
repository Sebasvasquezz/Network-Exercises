package edu.escuelaing.arsw.ASE.app;

import java.net.*;
import java.io.*;
import java.nio.file.*;

public class EchoServer451 {
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
                            System.out.println(2);
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

    private static void serveFile(OutputStream clientOutput, PrintWriter out, String requestedFile) {
        Path filePath = Paths.get("E:\\ARSW\\5. Tarea3\\Network-Exercises\\src\\main\\java\\edu\\escuelaing\\arsw\\ASE\\app\\webroot", requestedFile);
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
